package org.dsa.iot.mango;

import io.swagger.client.ApiException;
import io.swagger.client.api.MangoDSLApi;
import io.swagger.client.model.*;

import org.dsa.iot.dslink.node.Node;
import org.dsa.iot.dslink.node.Permission;
import org.dsa.iot.dslink.node.actions.Action;
import org.dsa.iot.dslink.node.actions.ActionResult;
import org.dsa.iot.dslink.node.actions.Parameter;
import org.dsa.iot.dslink.node.value.Value;
import org.dsa.iot.dslink.node.value.ValueType;
import org.dsa.iot.dslink.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dsa.iot.dslink.util.handler.Handler;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Peter Weise on 9/3/15.
 */
public class MangoFolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MangoFolder.class);

    private Node node;
    private MangoConn conn;
    private MangoDSLApi api;
    private boolean loggedIn = false;
    private final Set<Node> nodesToUpdate = ConcurrentHashMap.newKeySet();
    private ScheduledFuture<?> updateHandle = null;
    private Runnable update = null;
    ScheduledThreadPoolExecutor stpe = Objects.getDaemonThreadPool();

    public MangoFolder(Node child, MangoDSLApi api, MangoConn conn) {
        this.node = child;
        this.conn = conn;
        this.api = api;
    }

    public Set<Node> getNodesToUpdate() {
        return nodesToUpdate;
    }

    //initialize user node settings and create actions for the server level node
    public void init() {
        conn.setClientNode(node);
        conn.setClientFolder(this);

        loggedIn = true;

        setActions();

        getHierarchy();

        startPointUpdates();
    }

    //set actions for the server level node
    private void setActions() {
        Action editAct = editAction();
        node.createChild("Edit", false).setAction(editAct).setSerializable(false).build();

        Action outAct = logoutAction();
        node.createChild("Delete", false).setAction(outAct).setSerializable(false).build();

        Action refreshAct = refreshAction();
        node.createChild("Refresh", false).setAction(refreshAct).setSerializable(false).build();
    }

    //create action object for editing node attributes
    private Action editAction() {
        Action act = new Action(Permission.READ, new EditHandler());
        act.addParameter(new Parameter("Name", ValueType.STRING, new Value(node.getName())));
        act.addParameter(new Parameter("URL", ValueType.STRING, new Value(node.getAttribute("url").toString())));
        act.addParameter(new Parameter("Default Polling Interval", ValueType.NUMBER, new Value(5)));
        return act;
    }

    //action handler for the edit action
    private class EditHandler implements Handler<ActionResult> {
        @Override
        public void handle(ActionResult event) {
            String name = event.getParameter("Name").toString();
            if (!name.equals(node.getName())) {
                node.setDisplayName(name);
            }

            String url = event.getParameter("URL").getString();
            if (!url.equals(node.getAttribute("url").getString())) {
                node.setAttribute("url", new Value(url));
                conn.setClientUrl(url);
            }

            int delay = event.getParameter("Default Polling Interval").getNumber().intValue();
            if (delay != conn.getUpdateRate()) {
                node.setAttribute("updateRate", new Value(delay));
                conn.setUpdateRate(delay);
                updateHandle.cancel(true);
                ScheduledThreadPoolExecutor stpe = Objects.getDaemonThreadPool();
                updateHandle = stpe.scheduleAtFixedRate(update, conn.getUpdateRate(), conn.getUpdateRate(),
                        TimeUnit.SECONDS);
            }
        }
    }

    //create action object for logging out
    private Action logoutAction() {
        return new Action(Permission.READ, new LogoutHandler());
    }

    //action handler for the logout action
    private class LogoutHandler implements Handler<ActionResult> {
        @Override
        public void handle(ActionResult event) {
            loggedIn = false;
            nodesToUpdate.clear();
            conn.logout();
        }
    }

    //create action object for refreshing the point hierarchy
    private Action refreshAction() {
        return new Action(Permission.READ, new RefreshHandler());
    }

    //action handler for the refresh action
    private class RefreshHandler implements Handler<ActionResult> {
        @Override
        public void handle(ActionResult event) {
            getHierarchy();
        }
    }

    //build the node hierarchy from the user node, down the folder nodes, to the data point
    //using the API method.  This method includes setting attributes for children
    //and setting the initial value for for each point
    protected void getHierarchy() {
        try {
            ResponseEntityPointHierarchyModel ph =  api.getPointHierarchy();
            LOGGER.info("Point Hierarchy completed");
        } catch (ApiException e) {
            LOGGER.info("getHierarchy\n\tcode: {}\n\tmessage: {}\n\theader: {}\n\tbody: {}\n{}",
                    e.getCode(), e.getMessage(), e.getResponseHeaders(), e.getResponseBody(), e);
        }
    }

    //scheduled method to update data points that are expected to change value periodically
    //the update rate is settable
    private void startPointUpdates() {
        update = new Runnable() {
            @Override
            public void run() {
                if (!loggedIn) {
                    return;
                }
                synchronized (nodesToUpdate) {
                    for (Node no : nodesToUpdate) {
                        try {
                            Node original = conn.getClientNode();
                            conn.setClientNode(no);
                            String xid = no.getAttribute("xid").getString();
                            ResponseEntityRealTimeModel rtm = api.get(xid);
                            String b = rtm.getBody().toString();
                            int beginIndex = b.indexOf("type: ");
                            int endIndex = b.indexOf("\n", beginIndex);
                            String type = b.substring(beginIndex + 6, endIndex);
                            beginIndex = b.indexOf("value: ");
                            endIndex = b.indexOf("\n", beginIndex);
                            String value = b.substring(beginIndex + 7, endIndex);
                            Value val = null;
                            //Don't set nulls
                            if(value !=null && !"null".equals(value)) {
                                switch (type) {
                                    case "Numeric":
                                        Float num = Float.valueOf(value);
                                        val = new Value(num);
                                        break;
                                    case "Binary":
                                        Boolean bool = Boolean.getBoolean("value");
                                        val = new Value(bool);
                                        break;
                                    case "Multistate":
                                    	Integer myInt = Integer.parseInt(value);
                                    	val = new Value(myInt);
                                        break;
                                    case "Image":
                                        LOGGER.warn("mangoFolder:RealTimeModel - unimplemented data type: IMAGE");
                                        break;
                                    case "Alphanumeric":
                                        String s = String.valueOf(value);
                                        val = new Value(s);
                                        break;
                                }
                                no.setValue(val);
                            }
                            conn.setClientNode(original);
                        } catch (ApiException e) {
                            LOGGER.error(e.getMessage(), e);
                            LOGGER.error("Data point has no data - {}", no.getDisplayName());
                            no.setValue(null);
                        } catch (NumberFormatException e) {
                            LOGGER.error(e.getMessage(), e);
                            LOGGER.error("Point Update error - {}", e);
                            no.setValue(null);
                        }
                    }
                }
            }
        };
        updateHandle = stpe.scheduleAtFixedRate(update, conn.getUpdateRate(), conn.getUpdateRate(),
                TimeUnit.SECONDS);
    }

    //create action object for deleting the data point
    protected Action deleteAction(Node node) {
        return new Action(Permission.READ, new DeleteHandler(node));
    }

    //handler to delete the data point
    private class DeleteHandler implements Handler<ActionResult> {
        Node node;
        public DeleteHandler(Node node) {
            this.node = node;
        }
        @Override
        public void handle(ActionResult event) {
            String name = node.getName();
            try {
                api.delete(node.getAttribute("xid").getString()); //produces an exception
                updateHandle.cancel(true);
                nodesToUpdate.remove(node);
                updateHandle = stpe.scheduleAtFixedRate(update, conn.getUpdateRate(), conn.getUpdateRate(),
                        TimeUnit.SECONDS);
                Node parent = node.getParent();
                parent.removeChild(node, false);
                getHierarchy();
                LOGGER.info("Data point deleted - {}", name);
            } catch (ApiException e) {
                LOGGER.error("deleteAction error - {}\n{}", node.getAttribute("xid"), e);
            } catch (RuntimeException e) {
                //the api call above returns an invalid response error
                //the point actually gets deleted, but the rest of the method is skipped because of the thrown exception
                //the code below ensures that the rest of the method performs its duties
                updateHandle.cancel(true);
                nodesToUpdate.remove(node);
                updateHandle = stpe.scheduleAtFixedRate(update, conn.getUpdateRate(), conn.getUpdateRate(),
                        TimeUnit.SECONDS);
                Node parent = node.getParent();
                parent.removeChild(node, false);
                LOGGER.info("Data point deleted - {}", name);
            }
        }
    }
}
