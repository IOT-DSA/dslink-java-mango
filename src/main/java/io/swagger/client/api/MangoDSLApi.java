package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Pair;
import io.swagger.client.TypeRef;


import java.util.*;

import io.swagger.client.model.ResponseEntityListDataPointModel;
import io.swagger.client.model.ResponseEntityDataPointModel;
import io.swagger.client.model.DataPointModel;
import io.swagger.client.model.ListAbstractDataSourceModelobject;
import io.swagger.client.model.ResponseEntityAbstractDataSourceModelobject;
import io.swagger.client.model.AbstractDataSourceModelobject;
import io.swagger.client.model.ResponseEntityPointHierarchyModel;
import io.swagger.client.model.ResponseEntityListstring;
import io.swagger.client.model.ResponseEntityUserModel;
import io.swagger.client.model.ResponseEntityJsonArrayStream;
import java.util.Date;
import io.swagger.client.model.PointValueTimeModel;
import io.swagger.client.model.ResponseEntityPointValueTimeModel;
import io.swagger.client.model.ResponseEntityListPointValueTimeModel;
import io.swagger.client.model.ResponseEntityStatisticsStream;
import io.swagger.client.model.ResponseEntityListRealTimeModel;
import io.swagger.client.model.ResponseEntityRealTimeModel;
import io.swagger.client.model.ResponseEntityListThreadModel;
import io.swagger.client.model.ResponseEntityListUserModel;
import io.swagger.client.model.UserModel;

import java.util.Map;

import javax.ws.rs.core.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-08-31T13:09:15.165-07:00")
public class MangoDSLApi {

    public enum AuthenticationType {
        BASIC,
        USER,
        TOKEN
    }
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MangoDSLApi.class);
    private static final String API_VERSION = "/v1";
    private ApiClient apiClient;
    private AuthenticationType authType;

    public MangoDSLApi(ApiClient apiClient, AuthenticationType authType) {
        this.apiClient = apiClient;
        this.authType = authType;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
  
    private String[] getAuthNames() {
        switch(authType) {
            case BASIC:
            return new String[] {"basic"};
            case USER:
                return new String[] { };
            case TOKEN:
                return new String[] {"token"};
            default:
                return new String[] {};
        }
    }
    /**
     * Get all data points
     * Only returns points available to logged in user
     * @param limit limit
     * @return ResponseEntityListDataPointModel
     */
    public ResponseEntityListDataPointModel getAllDataPoints (Integer limit) throws ApiException {
          Object postBody = null;
          byte[] postBinaryBody = null;

          // create path and map variables
          String path = API_VERSION + "/data-points";

          // query params
          List<Pair> queryParams = new ArrayList<Pair>();
          Map<String, String> headerParams = new HashMap<String, String>();
          Map<String, Object> formParams = new HashMap<String, Object>();

          queryParams.addAll(apiClient.parameterToPairs("", "limit", limit));

          final String[] accepts = { "application/json" };

          final String accept = apiClient.selectHeaderAccept(accepts);

          final String[] contentTypes = { };

          final String contentType = apiClient.selectHeaderContentType(contentTypes);

          String[] authNames = getAuthNames();

          TypeRef returnType = new TypeRef<ResponseEntityListDataPointModel>() { };

          return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getDataPoint
     * getDataPoint
     * @param xid Valid Data Point XIDs
     * @return ResponseEntityDataPointModel
     */
    public ResponseEntityDataPointModel getDataPoint (String xid) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling getDataPoint");
         }

        // create path and map variables
        String path = API_VERSION + "/data-points/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityDataPointModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * updateDataPoint
     * updateDataPoint
     * @param xid xid
     * @param model model
     * @return ResponseEntityDataPointModel
     */
    public ResponseEntityDataPointModel updateDataPoint (String xid, DataPointModel model) throws ApiException {
        Object postBody = model;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling updateDataPoint");
         }

        // create path and map variables
        String path = API_VERSION + "/data-points/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityDataPointModel>() { };
        return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
      }
  
    /**
     * delete
     * delete
     * @param xid xid
     * @return ResponseEntityDataPointModel
     */
    public ResponseEntityDataPointModel delete (String xid) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling delete");
         }

        // create path and map variables
        String path = API_VERSION + "/data-points/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityDataPointModel>() { };

        return apiClient.invokeAPI(path, "DELETE", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getAllDataSources
     * getAllDataSources
     * @return ListAbstractDataSourceModelobject
     */
    public ListAbstractDataSourceModelobject getAllDataSources () throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // create path and map variables
        String path = API_VERSION + "/data-sources";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ListAbstractDataSourceModelobject>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getDataSource
     * getDataSource
     * @param xid xid
     * @return ResponseEntityAbstractDataSourceModelobject
     */
    public ResponseEntityAbstractDataSourceModelobject getDataSource (String xid) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling getDataSource");
         }

        // create path and map variables
        String path = API_VERSION + "/data-sources/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityAbstractDataSourceModelobject>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * updateDataPoint
     * updateDataPoint
     * @param xid xid
     * @param model model
     * @return ResponseEntityAbstractDataSourceModelobject
     */
    public ResponseEntityAbstractDataSourceModelobject updateDataPoint_1 (String xid, AbstractDataSourceModelobject model) throws ApiException {
        Object postBody = model;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling updateDataPoint_1");
         }

        // create path and map variables
        String path = API_VERSION + "/data-sources/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityAbstractDataSourceModelobject>() { };

        return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getPointHierarchy
     * getPointHierarchy
     * @return ResponseEntityPointHierarchyModel
     */
    public ResponseEntityPointHierarchyModel getPointHierarchy () throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // create path and map variables
        String path = API_VERSION + "/hierarchy/full";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityPointHierarchyModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getFolder
     * getFolder
     * @param folderId folderId
     * @return ResponseEntityPointHierarchyModel
     */
    public ResponseEntityPointHierarchyModel getFolderById (Integer folderId) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'folderId' is set
         if (folderId == null) {
              throw new ApiException(400, "Missing the required parameter 'folderId' when calling getFolderById");
         }

        // create path and map variables
        String path = API_VERSION + "/hierarchy/by-id/{folderId}".replaceAll("\\{" + "folderId" + "\\}", apiClient.escapeString(folderId.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityPointHierarchyModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getFolder
     * getFolder
     * @param folderName folderName
     * @return ResponseEntityPointHierarchyModel
     */
    public ResponseEntityPointHierarchyModel getFolderByName (String folderName) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'folderName' is set
         if (folderName == null) {
              throw new ApiException(400, "Missing the required parameter 'folderName' when calling getFolderByName");
         }

        // create path and map variables
        String path = API_VERSION + "/hierarchy/by-name/{folderName}".replaceAll("\\{" + "folderName" + "\\}", apiClient.escapeString(folderName.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityPointHierarchyModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
      }
  
    /**
     * getPath
     * getPath
     * @param xid xid
     * @return ResponseEntityListstring
     */
    public ResponseEntityListstring getPath (String xid) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling getPath");
         }

        // create path and map variables
        String path = API_VERSION + "/hierarchy/path/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityListstring>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    
    /**
     * login
     * login
     * @param username username
     * @param password password
     * @param logout logout
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel login (String username, String password, Boolean logout) throws ApiException {
        HashMap<String, Object> postBody = new HashMap<>();
        postBody.put("username", username);
        postBody.put("password", password);
        byte[] postBinaryBody = null;

         // verify the required parameter 'username' is set
         if (username == null) {
              throw new ApiException(400, "Missing the required parameter 'username' when calling login");
         }

        // create path and map variables
        String path = "/v2/login";
        
        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();
        
        if (logout != null)
            headerParams.put("logout", apiClient.parameterToString(logout));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { "application/json" };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * loginPut
     * loginPut
     * @param username username
     * @param password password
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel loginPut (String username, String password) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'username' is set
         if (username == null) {
              throw new ApiException(400, "Missing the required parameter 'username' when calling loginPut");
         }

         // verify the required parameter 'password' is set
         if (password == null) {
              throw new ApiException(400, "Missing the required parameter 'password' when calling loginPut");
         }

        // create path and map variables
        String path = API_VERSION + "/login/{username}".replaceAll("\\{" + "username" + "\\}", apiClient.escapeString(username.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        queryParams.addAll(apiClient.parameterToPairs("", "password", password));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * logout
     * logout
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel logout () throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // create path and map variables
        String path = API_VERSION + "/logout";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * logoutPut
     * logoutPut
     * @param username username
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel logoutPut (String username) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'username' is set
         if (username == null) {
              throw new ApiException(400, "Missing the required parameter 'username' when calling logoutPut");
         }

        // create path and map variables
        String path = API_VERSION + "/logout/{username}".replaceAll("\\{" + "username" + "\\}", apiClient.escapeString(username.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * logoutPost
     * logoutPost
     * @param username username
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel logoutPost (String username) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'username' is set
         if (username == null) {
              throw new ApiException(400, "Missing the required parameter 'username' when calling logoutPost");
         }

        // create path and map variables
        String path = API_VERSION + "/logout/{username}".replaceAll("\\{" + "username" + "\\}", apiClient.escapeString(username.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Query Time Range
     * From time inclusive, To time exclusive
     * @param xid Point xid
     * @param from From time
     * @param to To time
     * @param rollup Rollup type
     * @param timePeriodType Time Period Type
     * @param timePeriods Time Periods
     * @return ResponseEntityJsonArrayStream
     */
    public ResponseEntityJsonArrayStream[] getPointValues (String xid, Date from, Date to, String rollup, String timePeriodType, Integer timePeriods) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // verify the required parameter 'xid' is set
        if (xid == null) {
            throw new ApiException(400, "Missing the required parameter 'xid' when calling getPointValues");
        }

        // create path and map variables
        String path = API_VERSION + "/point-values/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        queryParams.addAll(apiClient.parameterToPairs("", "from", from));
        queryParams.addAll(apiClient.parameterToPairs("", "to", to));
        queryParams.addAll(apiClient.parameterToPairs("", "rollup", rollup));
        queryParams.addAll(apiClient.parameterToPairs("", "timePeriodType", timePeriodType));
        queryParams.addAll(apiClient.parameterToPairs("", "timePeriods", timePeriods));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityJsonArrayStream[]>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }

    /**
     * Updatae an existing data point's value
     * Data point must exist and be enabled
     * @param xid xid
     * @param model model
     * @return ResponseEntityPointValueTimeModel
     */
    public ResponseEntityPointValueTimeModel putPointValue (String xid, PointValueTimeModel model) throws ApiException {
        Object postBody = model;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling putPointValue");
         }

        // create path and map variables
        String path = API_VERSION + "/point-values/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityPointValueTimeModel>() { };

        return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * First and last point values
     * Retrieves the first and last point values within a time range, used to read accumulators
     * @param xid Point xid
     * @param from From time
     * @param to To time
     * @return ResponseEntityListPointValueTimeModel
     */
    public ResponseEntityListPointValueTimeModel firstAndLastPointValues (String xid, Date from, Date to) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling firstAndLastPointValues");
         }

        // create path and map variables
        String path = API_VERSION + "/point-values/{xid}/first-last".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        queryParams.addAll(apiClient.parameterToPairs("", "from", from));
        queryParams.addAll(apiClient.parameterToPairs("", "to", to));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityListPointValueTimeModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Get Latest Point Values
     * Default 100, time descending order
     * @param xid Point xid
     * @param limit Limit results
     * @return ResponseEntityListPointValueTimeModel
     */
    public ResponseEntityListPointValueTimeModel getLatestPointValues (String xid, Integer limit) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling getLatestPointValues");
         }

         // verify the required parameter 'limit' is set
         if (limit == null) {
              throw new ApiException(400, "Missing the required parameter 'limit' when calling getLatestPointValues");
         }

        // create path and map variables
        String path = API_VERSION + "/point-values/{xid}/latest".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        queryParams.addAll(apiClient.parameterToPairs("", "limit", limit));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityListPointValueTimeModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Get Point Statistics
     * From time inclusive, To time exclusive
     * @param xid Point xid
     * @param from From time
     * @param to To time
     * @return ResponseEntityStatisticsStream
     */
    public ResponseEntityStatisticsStream getPointStatistics (String xid, Date from, Date to) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // verify the required parameter 'xid' is set
        if (xid == null) {
            throw new ApiException(400, "Missing the required parameter 'xid' when calling getPointStatistics");
        }

        // create path and map variables
        String path = API_VERSION + "/point-values/{xid}/statistics".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        queryParams.addAll(apiClient.parameterToPairs("", "from", from));
        queryParams.addAll(apiClient.parameterToPairs("", "to", to));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityStatisticsStream>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getAll
     * getAll
     * @param limit limit
     * @return ResponseEntityListRealTimeModel
     */
    public ResponseEntityListRealTimeModel getAll (Integer limit) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // create path and map variables
        String path = "/v1/realtime";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        queryParams.addAll(apiClient.parameterToPairs("", "limit", limit));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityListRealTimeModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * get
     * get
     * @param xid xid
     * @return ResponseEntityRealTimeModel
     */
    public ResponseEntityRealTimeModel get (String xid) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'xid' is set
         if (xid == null) {
              throw new ApiException(400, "Missing the required parameter 'xid' when calling get");
         }

        // create path and map variables
        String path = API_VERSION + "/realtime/by-xid/{xid}".replaceAll("\\{" + "xid" + "\\}", apiClient.escapeString(xid.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityRealTimeModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * getThreads
     * getThreads
     * @param stackDepth Limit size of stack trace
     * @param asFile Return as file
     * @param orderBy Order by this member
     * @return ResponseEntityListThreadModel
     */
    public ResponseEntityListThreadModel getThreads (Integer stackDepth, Boolean asFile, String orderBy) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'stackDepth' is set
         if (stackDepth == null) {
              throw new ApiException(400, "Missing the required parameter 'stackDepth' when calling getThreads");
         }

         // verify the required parameter 'asFile' is set
         if (asFile == null) {
              throw new ApiException(400, "Missing the required parameter 'asFile' when calling getThreads");
         }

        // create path and map variables
        String path = API_VERSION + "/threads";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        queryParams.addAll(apiClient.parameterToPairs("", "stackDepth", stackDepth));
        queryParams.addAll(apiClient.parameterToPairs("", "asFile", asFile));
        queryParams.addAll(apiClient.parameterToPairs("", "orderBy", orderBy));

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityListThreadModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Get all users
     * Returns a list of all users
     * @return ResponseEntityListUserModel
     */
    public ResponseEntityListUserModel getAllUsers () throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // create path and map variables
        String path = API_VERSION + "/users";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityListUserModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Create New User
     * Cannot save existing user
     * @param model User to save
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel createNewUser (UserModel model) throws ApiException {
        Object postBody = model;
        byte[] postBinaryBody = null;

         // verify the required parameter 'model' is set
         if (model == null) {
              throw new ApiException(400, "Missing the required parameter 'model' when calling createNewUser");
         }

        // create path and map variables
        String path = API_VERSION + "/users";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "POST", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Get current user
     * Returns the logged in user
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel getCurrentUser () throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

        // create path and map variables
        String path = API_VERSION + "/users/current";

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Get user by name
     * Returns the user specified by the given username
     * @param username Valid username
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel getUser (String username) throws ApiException {
        Object postBody = null;
        byte[] postBinaryBody = null;

         // verify the required parameter 'username' is set
         if (username == null) {
              throw new ApiException(400, "Missing the required parameter 'username' when calling getUser");
         }

        // create path and map variables
        String path = API_VERSION + "/users/{username}".replaceAll("\\{" + "username" + "\\}", apiClient.escapeString(username.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
    /**
     * Updates a user
     * updateUser
     * @param username username
     * @param model model
     * @return ResponseEntityUserModel
     */
    public ResponseEntityUserModel updateUser (String username, UserModel model) throws ApiException {
        Object postBody = model;
        byte[] postBinaryBody = null;

         // verify the required parameter 'username' is set
         if (username == null) {
              throw new ApiException(400, "Missing the required parameter 'username' when calling updateUser");
         }

        // create path and map variables
        String path = API_VERSION + "/users/{username}".replaceAll("\\{" + "username" + "\\}", apiClient.escapeString(username.toString()));

        // query params
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();
        Map<String, Object> formParams = new HashMap<String, Object>();

        final String[] accepts = { "application/json" };

        final String accept = apiClient.selectHeaderAccept(accepts);

        final String[] contentTypes = { };

        final String contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = getAuthNames();

        TypeRef returnType = new TypeRef<ResponseEntityUserModel>() { };

        return apiClient.invokeAPI(path, "PUT", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
  
}
