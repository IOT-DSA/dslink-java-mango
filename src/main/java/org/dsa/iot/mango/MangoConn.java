package org.dsa.iot.mango;

import java.util.UUID;

import javax.ws.rs.core.Cookie;

import org.dsa.iot.dslink.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.MangoDSLApi;
import io.swagger.client.api.MangoDSLApi.AuthenticationType;
import io.swagger.client.auth.ApiKeyAuth;

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
        api = new MangoDSLApi(client, AuthenticationType.valueOf(node.getAttribute("authType").getString()));
        client.setBuilder(this);
    }

    //access point to start the link connection
    public void start() {
        init();
        setLogin();
    }


    //log into the server and establish a connection, saving the cookie for session access
    private void setLogin() {
        try {
            String token = UUID.randomUUID().toString();
            client.addCookie(new Cookie("XSRF-TOKEN", token));
            client.setXsrfToken(token);
            
            //Only for Basic Auth
            switch(AuthenticationType.valueOf(node.getAttribute("authType").getString())) {
                case BASIC:
                default:
                    client.setUsername(node.getAttribute("username").getString());
                    client.setPassword(String.valueOf(node.getPassword()));
                    //Ensure creds are ok
                    api.getCurrentUser();
                break;
                case USER:
                    String password = String.valueOf(node.getPassword());
                    api.login(node.getAttribute("username").getString(), password, false);
                    break;
                case TOKEN:
                    ApiKeyAuth auth = (ApiKeyAuth)client.getAuthentication("token");
                    auth.setApiKeyPrefix("Bearer");
                    String apiKey = node.getAttribute("apiKey").toString();
                    auth.setApiKey(apiKey);
                    api.getCurrentUser();
                    break;
            }
            LOGGER.info("{} logged in", node.getAttribute("username"));
            MangoFolder mf = new MangoFolder(node, api, this);
            mf.init();
        } catch (ApiException e) {
            LOGGER.error("setLogin\n\tcode: {}\n\tmessage: {}\n\theader: {}\n\tbody: {}\n{}",
                    e.getCode(), e.getMessage(), e.getResponseHeaders(), e.getResponseBody(), e);
            Node parent = node.getParent();
            parent.removeChild(node);
        } catch (Exception e) {
            LOGGER.error("{}", e);
            Node parent = node.getParent();
            parent.removeChild(node);
        }
    }

    //log out the user and cleanup the tree
    protected void logout() {
        try {
            api.logoutPost(node.getAttribute("username").getString());
            client.clearCookies();
            LOGGER.info("{} logged out", node.getAttribute("username"));
        } catch (ApiException e) {
            LOGGER.error("logout\n\tcode: {}\n\tmessage: {}\n\theader: {}\n\tbody: {}\n{}",
                    e.getCode(), e.getMessage(), e.getResponseHeaders(), e.getResponseBody(), e);
        }
        Node parent = node.getParent();
        parent.removeChild(node);
    }

}
