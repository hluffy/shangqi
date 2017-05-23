package com.dk.gis.client.auth;

import java.util.Map;

import com.dk.gis.client.Pair;

import java.util.List;

public interface Authentication {
  /** Apply authentication settings to header and query params. */
  void applyToParams(List<Pair> queryParams, Map<String, String> headerParams);
}
