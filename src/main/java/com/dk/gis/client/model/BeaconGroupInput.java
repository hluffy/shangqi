package com.dk.gis.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * \u7528\u4E8E\u8BA1\u7B97iBeancon\u5B9A\u4F4D\u7684\u8F93\u5165\u53C2\u6570
 **/

@ApiModel(description = "\u7528\u4E8E\u8BA1\u7B97iBeancon\u5B9A\u4F4D\u7684\u8F93\u5165\u53C2\u6570")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-09T01:15:50.884+08:00")
public class BeaconGroupInput   {
  
  private Double coefficient1 = null;
  private Double coefficient2 = null;
  private Double coefficient3 = null;
  private Double latitude1 = null;
  private Double latitude2 = null;
  private Double latitude3 = null;
  private Double longitude1 = null;
  private Double longitude2 = null;
  private Double longitude3 = null;
  private Double rssi1 = null;
  private Double rssi2 = null;
  private Double rssi3 = null;
  private Integer txPower1 = null;
  private Integer txPower2 = null;
  private Integer txPower3 = null;

  
  /**
   * ibeacon\u6D4B\u8DDD\u7CFB\u65701
   **/
  public BeaconGroupInput coefficient1(Double coefficient1) {
    this.coefficient1 = coefficient1;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "ibeacon\u6D4B\u8DDD\u7CFB\u65701")
  @JsonProperty("coefficient1")
  public Double getCoefficient1() {
    return coefficient1;
  }
  public void setCoefficient1(Double coefficient1) {
    this.coefficient1 = coefficient1;
  }

  
  /**
   * ibeacon\u6D4B\u8DDD\u7CFB\u65702
   **/
  public BeaconGroupInput coefficient2(Double coefficient2) {
    this.coefficient2 = coefficient2;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "ibeacon\u6D4B\u8DDD\u7CFB\u65702")
  @JsonProperty("coefficient2")
  public Double getCoefficient2() {
    return coefficient2;
  }
  public void setCoefficient2(Double coefficient2) {
    this.coefficient2 = coefficient2;
  }

  
  /**
   * ibeacon\u6D4B\u8DDD\u7CFB\u65703
   **/
  public BeaconGroupInput coefficient3(Double coefficient3) {
    this.coefficient3 = coefficient3;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "ibeacon\u6D4B\u8DDD\u7CFB\u65703")
  @JsonProperty("coefficient3")
  public Double getCoefficient3() {
    return coefficient3;
  }
  public void setCoefficient3(Double coefficient3) {
    this.coefficient3 = coefficient3;
  }

  
  /**
   * latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) of ibeacon in #1 group
   **/
  public BeaconGroupInput latitude1(Double latitude1) {
    this.latitude1 = latitude1;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) of ibeacon in #1 group")
  @JsonProperty("latitude1")
  public Double getLatitude1() {
    return latitude1;
  }
  public void setLatitude1(Double latitude1) {
    this.latitude1 = latitude1;
  }

  
  /**
   * latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) of ibeacon in #2 group
   **/
  public BeaconGroupInput latitude2(Double latitude2) {
    this.latitude2 = latitude2;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) of ibeacon in #2 group")
  @JsonProperty("latitude2")
  public Double getLatitude2() {
    return latitude2;
  }
  public void setLatitude2(Double latitude2) {
    this.latitude2 = latitude2;
  }

  
  /**
   * latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) of ibeacon in #3 group
   **/
  public BeaconGroupInput latitude3(Double latitude3) {
    this.latitude3 = latitude3;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "latitude(\u7EAC\u5EA6,\u5982\u5317\u7EAC34.29958) of ibeacon in #3 group")
  @JsonProperty("latitude3")
  public Double getLatitude3() {
    return latitude3;
  }
  public void setLatitude3(Double latitude3) {
    this.latitude3 = latitude3;
  }

  
  /**
   * longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888) of ibeacon in #1 group
   **/
  public BeaconGroupInput longitude1(Double longitude1) {
    this.longitude1 = longitude1;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888) of ibeacon in #1 group")
  @JsonProperty("longitude1")
  public Double getLongitude1() {
    return longitude1;
  }
  public void setLongitude1(Double longitude1) {
    this.longitude1 = longitude1;
  }

  
  /**
   * longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888) of ibeacon in #2 group
   **/
  public BeaconGroupInput longitude2(Double longitude2) {
    this.longitude2 = longitude2;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888) of ibeacon in #2 group")
  @JsonProperty("longitude2")
  public Double getLongitude2() {
    return longitude2;
  }
  public void setLongitude2(Double longitude2) {
    this.longitude2 = longitude2;
  }

  
  /**
   * longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888) of ibeacon in #3 group
   **/
  public BeaconGroupInput longitude3(Double longitude3) {
    this.longitude3 = longitude3;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "longitude(\u7ECF\u5EA6,\u5982\u4E1C\u7ECF121.58888) of ibeacon in #3 group")
  @JsonProperty("longitude3")
  public Double getLongitude3() {
    return longitude3;
  }
  public void setLongitude3(Double longitude3) {
    this.longitude3 = longitude3;
  }

  
  /**
   * rssi of ibeacon in #1 group
   **/
  public BeaconGroupInput rssi1(Double rssi1) {
    this.rssi1 = rssi1;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "rssi of ibeacon in #1 group")
  @JsonProperty("rssi1")
  public Double getRssi1() {
    return rssi1;
  }
  public void setRssi1(Double rssi1) {
    this.rssi1 = rssi1;
  }

  
  /**
   * rssi of ibeacon in #2 group
   **/
  public BeaconGroupInput rssi2(Double rssi2) {
    this.rssi2 = rssi2;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "rssi of ibeacon in #2 group")
  @JsonProperty("rssi2")
  public Double getRssi2() {
    return rssi2;
  }
  public void setRssi2(Double rssi2) {
    this.rssi2 = rssi2;
  }

  
  /**
   * rssi of ibeacon in #3 group
   **/
  public BeaconGroupInput rssi3(Double rssi3) {
    this.rssi3 = rssi3;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "rssi of ibeacon in #3 group")
  @JsonProperty("rssi3")
  public Double getRssi3() {
    return rssi3;
  }
  public void setRssi3(Double rssi3) {
    this.rssi3 = rssi3;
  }

  
  /**
   * txPower of ibeacon in #1 group
   **/
  public BeaconGroupInput txPower1(Integer txPower1) {
    this.txPower1 = txPower1;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "txPower of ibeacon in #1 group")
  @JsonProperty("txPower1")
  public Integer getTxPower1() {
    return txPower1;
  }
  public void setTxPower1(Integer txPower1) {
    this.txPower1 = txPower1;
  }

  
  /**
   * txPower of ibeacon in #2 group
   **/
  public BeaconGroupInput txPower2(Integer txPower2) {
    this.txPower2 = txPower2;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "txPower of ibeacon in #2 group")
  @JsonProperty("txPower2")
  public Integer getTxPower2() {
    return txPower2;
  }
  public void setTxPower2(Integer txPower2) {
    this.txPower2 = txPower2;
  }

  
  /**
   * txPower of ibeacon in #3 group
   **/
  public BeaconGroupInput txPower3(Integer txPower3) {
    this.txPower3 = txPower3;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "txPower of ibeacon in #3 group")
  @JsonProperty("txPower3")
  public Integer getTxPower3() {
    return txPower3;
  }
  public void setTxPower3(Integer txPower3) {
    this.txPower3 = txPower3;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconGroupInput beaconGroupInput = (BeaconGroupInput) o;
    return Objects.equals(this.coefficient1, beaconGroupInput.coefficient1) &&
        Objects.equals(this.coefficient2, beaconGroupInput.coefficient2) &&
        Objects.equals(this.coefficient3, beaconGroupInput.coefficient3) &&
        Objects.equals(this.latitude1, beaconGroupInput.latitude1) &&
        Objects.equals(this.latitude2, beaconGroupInput.latitude2) &&
        Objects.equals(this.latitude3, beaconGroupInput.latitude3) &&
        Objects.equals(this.longitude1, beaconGroupInput.longitude1) &&
        Objects.equals(this.longitude2, beaconGroupInput.longitude2) &&
        Objects.equals(this.longitude3, beaconGroupInput.longitude3) &&
        Objects.equals(this.rssi1, beaconGroupInput.rssi1) &&
        Objects.equals(this.rssi2, beaconGroupInput.rssi2) &&
        Objects.equals(this.rssi3, beaconGroupInput.rssi3) &&
        Objects.equals(this.txPower1, beaconGroupInput.txPower1) &&
        Objects.equals(this.txPower2, beaconGroupInput.txPower2) &&
        Objects.equals(this.txPower3, beaconGroupInput.txPower3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coefficient1, coefficient2, coefficient3, latitude1, latitude2, latitude3, longitude1, longitude2, longitude3, rssi1, rssi2, rssi3, txPower1, txPower2, txPower3);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconGroupInput {\n");
    
    sb.append("    coefficient1: ").append(toIndentedString(coefficient1)).append("\n");
    sb.append("    coefficient2: ").append(toIndentedString(coefficient2)).append("\n");
    sb.append("    coefficient3: ").append(toIndentedString(coefficient3)).append("\n");
    sb.append("    latitude1: ").append(toIndentedString(latitude1)).append("\n");
    sb.append("    latitude2: ").append(toIndentedString(latitude2)).append("\n");
    sb.append("    latitude3: ").append(toIndentedString(latitude3)).append("\n");
    sb.append("    longitude1: ").append(toIndentedString(longitude1)).append("\n");
    sb.append("    longitude2: ").append(toIndentedString(longitude2)).append("\n");
    sb.append("    longitude3: ").append(toIndentedString(longitude3)).append("\n");
    sb.append("    rssi1: ").append(toIndentedString(rssi1)).append("\n");
    sb.append("    rssi2: ").append(toIndentedString(rssi2)).append("\n");
    sb.append("    rssi3: ").append(toIndentedString(rssi3)).append("\n");
    sb.append("    txPower1: ").append(toIndentedString(txPower1)).append("\n");
    sb.append("    txPower2: ").append(toIndentedString(txPower2)).append("\n");
    sb.append("    txPower3: ").append(toIndentedString(txPower3)).append("\n");
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

