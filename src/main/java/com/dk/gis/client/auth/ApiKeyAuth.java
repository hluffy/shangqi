package com.dk.gis.client.auth;

import java.util.Map;

import com.dk.gis.client.Pair;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-09T01:15:50.884+08:00")
public class ApiKeyAuth implements Authentication {
  private final String location;
  private final String paramName;

  private String apiKey;
  private String apiKeyPrefix;

  public ApiKeyAuth(String location, String paramName) {
    this.location = location;
    this.paramName = paramName;
  }

  public String getLocation() {
    return location;
  }

  public String getParamName() {
    return paramName;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getApiKeyPrefix() {
    return apiKeyPrefix;
  }

  public void setApiKeyPrefix(String apiKeyPrefix) {
    this.apiKeyPrefix = apiKeyPrefix;
  }

  @Override
  public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams) {
    if (apiKey == null) {
      return;
    }
    String value;
    if (apiKeyPrefix != null) {
      value = apiKeyPrefix + " " + apiKey;
    } else {
      value = apiKey;
    }
    if (location == "query") {
      queryParams.add(new Pair(paramName, value));
    } else if (location == "header") {
      headerParams.put(paramName, value);
    }
  }
}
