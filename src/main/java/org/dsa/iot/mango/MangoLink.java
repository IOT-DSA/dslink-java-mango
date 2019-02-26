package org.dsa.iot.mango;

import org.dsa.iot.dslink.node.Node;
import org.dsa.iot.dslink.node.NodeBuilder;
import org.dsa.iot.dslink.node.Permission;
import org.dsa.iot.dslink.node.actions.Action;
import org.dsa.iot.dslink.node.actions.ActionResult;
import org.dsa.iot.dslink.node.actions.EditorType;
import org.dsa.iot.dslink.node.actions.Parameter;
import org.dsa.iot.dslink.node.value.Value;
import org.dsa.iot.dslink.node.value.ValueType;
import org.dsa.iot.dslink.util.handler.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MangoLink {

    private static final Logger LOGGER = LoggerFactory.getLogger(MangoLink.class);

    private Node node;
    private int updateRate = 5;

    protected int getUpdateRate() {
        return updateRate;
    }

    protected void setUpdateRate(int rate) {
        updateRate = rate;
    }

    public MangoLink (Node node) {
        this.node = node;
    }

    //start global variables and start the link
    public static void start(Node parent) {
        MangoLink mango = new MangoLink(parent);
        mango.init();
    }

    //initialize the link session and add the connection action
    private void init() {
        restoreLastSession();
        Action act = setPathAction();
        node.createChild("Add Connection", false).setAction(act).setSerializable(false).build();
    }

    //reload the nodes and objects used during the last application execution
    //from the Mango master level
    private void restoreLastSession() {
        if (node.getChildren() == null) return;
        for (Node child: node.getChildren().values()) {
            if (child.getAttribute("url") != null) {
				updateRate = child.getAttribute("updateRate").getNumber().intValue();
                MangoConn conn = new MangoConn(MangoLink.this, child);
                conn.start();
            } else if (!"defs".equals(child.getName())
                        && child.getAction() == null) {
                node.removeChild(child, false);
            }
        }
    }

    //set up action for setting the connection information
    private Action setPathAction() {
        Action act = new Action(Permission.READ, new CreateConnHandler());
        act.addParameter(new Parameter("Name", ValueType.STRING).setPlaceHolder("Local Host"));
        act.addParameter(new Parameter("URL", ValueType.STRING).setPlaceHolder("http://localhost:8000/rest"));
        act.addParameter(new Parameter("Username", ValueType.STRING).setPlaceHolder("admin"));
        act.addParameter(new Parameter("Password", ValueType.STRING).setPlaceHolder("admin")
                .setEditorType(EditorType.PASSWORD));
        act.addParameter(new Parameter("Default Polling Interval", ValueType.NUMBER, new Value(updateRate)));
        return act;
    }

    //receive URL path and label information as well as username and password,
    //then build the host level node
    private class CreateConnHandler implements Handler<ActionResult> {
        @Override
        public void handle(ActionResult event) {
            String url = event.getParameter("URL", ValueType.STRING).getString();
            String name = event.getParameter("Name", ValueType.STRING).getString();
            String username = event.getParameter("Username", ValueType.STRING).getString();
            String pw = event.getParameter("Password", ValueType.STRING).getString();
            updateRate = event.getParameter("Default Polling Interval", ValueType.NUMBER).getNumber().intValue();
            NodeBuilder b = node.createChild(name, false);
            b.setAttribute("url", new Value(url));
            b.setAttribute("username", new Value(username));
            b.setPassword(pw.toCharArray());
            b.setAttribute("updateRate", new Value(updateRate));
            Node child = b.build();
            LOGGER.info("Base URL set - {}", child.getAttribute("url"));
            MangoConn conn = new MangoConn(MangoLink.this, child);
            conn.start();
        }
    }

}
