package com.dk.gis.client.api;

import com.dk.gis.client.ApiClient;
import com.dk.gis.client.ApiException;
import com.dk.gis.client.Configuration;
import com.dk.gis.client.Pair;
import com.dk.gis.client.model.BeaconGroupInput;
import com.dk.gis.client.model.BeaconInput;
import com.dk.gis.client.model.GISLocation;
import com.sun.jersey.api.client.GenericType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-09T01:15:50.884+08:00")
public class IBeaconApi {
  private ApiClient apiClient;

  public IBeaconApi() {
    this(Configuration.getDefaultApiClient());
  }

  public IBeaconApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * \u63D0\u5230iBeacon\u4FE1\u53F7\u6570\u636E\u548C\u57FA\u7AD9\u4F4D\u7F6E,\u76F4\u63A5\u8FD4\u56DE\u8DDD\u79BB
   * \u8BE5\u63A5\u53E3\u4EC5\u9002\u5408\u5C0F\u89C4\u6A21\u4E1A\u52A1\u573A\u666F\uFF0C\u652F\u6301\u5927\u89C4\u6A21\u573A\u666F\u7684\u63A5\u53E3\u7A0D\u540E\u5C06\u9646\u7EED\u5F00\u653E &lt;br&gt; \u5982\u60A8\u7684\u4E1A\u52A1\u5B58\u5728\u4E2A\u6027\u5316\u5B9A\u5236\u9700\u6C42\uFF0C\u8BF7\u4E0E\u6211\u4EEC\u8054\u7CFB: frank@dkvan.com \u6216 13162825161
   * @param input input (required)
   * @return Double
   * @throws ApiException if fails to make API call
   */
  public Double obtainDistanceUsingPOST(BeaconInput input) throws ApiException {
    Object localVarPostBody = input;
    
    // verify the required parameter 'input' is set
    if (input == null) {
      throw new ApiException(400, "Missing the required parameter 'input' when calling obtainDistanceUsingPOST");
    }
    
    // create path and map variables
    String localVarPath = "/gis/v1/ibeacon/distance".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<Double> localVarReturnType = new GenericType<Double>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * \u63D0\u5230iBeacon\u4FE1\u53F7\u6570\u636E\u548C\u57FA\u7AD9\u4F4D\u7F6E,\u76F4\u63A5\u8FD4\u56DE\u5B9A\u4F4D\u7684\u7ECF\u7EAC\u5EA6\u4FE1\u606F
   * \u8BE5\u63A5\u53E3\u4EC5\u9002\u5408\u5C0F\u89C4\u6A21\u4E1A\u52A1\u573A\u666F\uFF0C\u652F\u6301\u5927\u89C4\u6A21\u573A\u666F\u7684\u63A5\u53E3\u7A0D\u540E\u5C06\u9646\u7EED\u5F00\u653E &lt;br&gt; \u5982\u60A8\u7684\u4E1A\u52A1\u5B58\u5728\u4E2A\u6027\u5316\u5B9A\u5236\u9700\u6C42\uFF0C\u8BF7\u4E0E\u6211\u4EEC\u8054\u7CFB: frank@dkvan.com \u6216 13162825161
   * @param input input (required)
   * @return GISLocation
   * @throws ApiException if fails to make API call
   */
  public GISLocation obtainLocationUsingPOST(BeaconGroupInput input) throws ApiException {
    Object localVarPostBody = input;
    
    // verify the required parameter 'input' is set
    if (input == null) {
      throw new ApiException(400, "Missing the required parameter 'input' when calling obtainLocationUsingPOST");
    }
    
    // create path and map variables
    String localVarPath = "/gis/v1/ibeacon/location".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<GISLocation> localVarReturnType = new GenericType<GISLocation>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
