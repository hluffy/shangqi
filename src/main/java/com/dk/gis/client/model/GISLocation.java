package com.dk.gis.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * iBeancon\u5B9A\u4F4D\u7684\u4F4D\u7F6E\u4FE1\u606F
 **/

@ApiModel(description = "iBeancon\u5B9A\u4F4D\u7684\u4F4D\u7F6E\u4FE1\u606F")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-09T01:15:50.884+08:00")
public class GISLocation   {
  
  private Double latitude = null;
  private Double longitude = null;
  private String name = null;

  
  /**
   * latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) 
   **/
  public GISLocation latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) ")
  @JsonProperty("latitude")
  public Double getLatitude() {
    return latitude;
  }
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  
  /**
   * longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888)  
   **/
  public GISLocation longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888)  ")
  @JsonProperty("longitude")
  public Double getLongitude() {
    return longitude;
  }
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  
  /**
   * location name 
   **/
  public GISLocation name(String name) {
    this.name = name;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "location name ")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GISLocation gISLocation = (GISLocation) o;
    return Objects.equals(this.latitude, gISLocation.latitude) &&
        Objects.equals(this.longitude, gISLocation.longitude) &&
        Objects.equals(this.name, gISLocation.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GISLocation {\n");
    
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

