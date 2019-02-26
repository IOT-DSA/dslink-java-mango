package org.dsa.iot.mango;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.MangoDSLApi;
import io.swagger.client.model.ResponseEntityUserModel;
import org.dsa.iot.dslink.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Timer utilities to initialize the DSLink connections
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Peter Weise on 9/1/15.
 */
public class MangoConn {

    private static final Logger LOGGER = LoggerFactory.getLogger(MangoConn.class);

    private MangoLink link;
    private Node node;
    private ApiClient client;
    private MangoDSLApi api;

    public MangoDSLApi getApi() {
        return api;
    }

    public void setClientNode(Node node) {
        client.setNode(node);
    }

    public Node getClientNode() {
        return client.getNode();
    }

    public void setClientFolder(MangoFolder mf) {
        client.setFolder(mf);
    }

    public void setClientUrl(String url) {
        client.setBasePath(url);
    }

    public void setUpdateRate(int rate) {
        link.setUpdateRate(rate);
    }

    public int getUpdateRate() {
        return link.getUpdateRate();
    }

    //initialize global variables
    public MangoConn(MangoLink link, Node child) {
        this.link = link;
        this.node = child;
    }

    //set up the API client and the Mango for Java DSL API framework
    private void init() {
        ApiClient apiClient = new ApiClient();
        String path = node.getAttribute("url").getString();
        client = apiClient.setBasePath(path);
        api = new MangoDSLApi(client);
        client.setBuilder(this);
    }

    //access point to start the link connection
    public void start() {
		init();
        setLogin();
    }

    //log into the server and establish a connection, saving the cookie for session access
    private void setLogin() {
		initMangoConn iConn = new initMangoConn();
		iConn.start();
    }

    //log out the user and cleanup the tree
    protected void logout() {
        try {
            api.logoutPost(node.getAttribute("username").getString());
            client.setCookie("");
            LOGGER.info("{} logged out", node.getAttribute("username"));
        } catch (ApiException e) {
            LOGGER.error("logout\n\tcode: {}\n\tmessage: {}\n\theader: {}\n\tbody: {}\n{}",
                    e.getCode(), e.getMessage(), e.getResponseHeaders(), e.getResponseBody(), e);
        }
        Node parent = node.getParent();
        parent.removeChild(node, false);
    }

	protected void initMangoFolder() {
		MangoFolder mf = new MangoFolder(node, api, this);
		mf.init();
	}

    private class initMangoConn extends TimerTask {
		private Timer timer;

		public initMangoConn() {}

		public void start() {
			timer = new Timer();
			timer.schedule(this, 100, 5000);
		}

		@Override
        public void run() {
			LOGGER.info("Trying to connect...");
			try {
				String password = String.valueOf(node.getPassword());

				ResponseEntityUserModel user = api.login(node.getAttribute("username").getString(), password, false);
				if (client.getCookie().equals("")) {
					String cookie = user.getHeaders().getCookie().split(";")[0].replaceAll("\\[", "");
					client.setCookie(cookie);
					LOGGER.info("Setting Cookie: " + cookie);
				}

				String xsrfToken = "";
				for(String cookie : client.getResponseHeaders().get("Set-Cookie")){
					LOGGER.info("Got cookie: " + cookie);
					if(cookie.contains("XSRF-TOKEN")){
						String xsrfCookie = cookie.split(";")[0].replaceAll("\\[", "");
						String token = xsrfCookie.split("=")[1];
						xsrfToken = token;
						LOGGER.info("Setting XSRF Token: " + token);
						client.addDefaultHeader("X-XSRF-TOKEN", token);
					}

					if(cookie.contains("MANGO")) {
						String xsrfCookie = cookie.split(";")[0].replaceAll("\\[", "");

						String key = xsrfCookie.split("=")[0];
						String value = xsrfCookie.split("=")[1];
						client.setCookie(key + "=" + value + ";" + "XSRF-TOKEN=" + xsrfToken);
					}
				}

				LOGGER.info("{} logged in", node.getAttribute("username"));
				initMangoFolder();
				this.cancel();
			} catch (ApiException e) {
				LOGGER.error("setLogin\n\tcode: {}\n\tmessage: {}\n\theader: {}\n\tbody: {}\n{}",
						e.getCode(), e.getMessage(), e.getResponseHeaders(), e.getResponseBody(), e);
				LOGGER.error("Connection rejected. Waiting...");
				//Node parent = node.getParent();
				//parent.removeChild(node, false);
			} catch (Exception e) {
				LOGGER.error("{}", e);
				LOGGER.error("Connection not found. Waiting...");
				//Node parent = node.getParent();
				//parent.removeChild(node, false);
			}
		}
	}

}
