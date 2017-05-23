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
public class BeaconInput   {
  
  private Double coefficient1 = null;
  private Double coefficient2 = null;
  private Double coefficient3 = null;
  private Double rssi = null;
  private Integer txPower = null;

  
  /**
   * ibeacon\u6D4B\u8DDD\u7CFB\u65701
   **/
  public BeaconInput coefficient1(Double coefficient1) {
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
  public BeaconInput coefficient2(Double coefficient2) {
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
  public BeaconInput coefficient3(Double coefficient3) {
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
   * rssi of ibeacon
   **/
  public BeaconInput rssi(Double rssi) {
    this.rssi = rssi;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "rssi of ibeacon")
  @JsonProperty("rssi")
  public Double getRssi() {
    return rssi;
  }
  public void setRssi(Double rssi) {
    this.rssi = rssi;
  }

  
  /**
   * txPower of ibeacon
   **/
  public BeaconInput txPower(Integer txPower) {
    this.txPower = txPower;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "txPower of ibeacon")
  @JsonProperty("txPower")
  public Integer getTxPower() {
    return txPower;
  }
  public void setTxPower(Integer txPower) {
    this.txPower = txPower;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BeaconInput beaconInput = (BeaconInput) o;
    return Objects.equals(this.coefficient1, beaconInput.coefficient1) &&
        Objects.equals(this.coefficient2, beaconInput.coefficient2) &&
        Objects.equals(this.coefficient3, beaconInput.coefficient3) &&
        Objects.equals(this.rssi, beaconInput.rssi) &&
        Objects.equals(this.txPower, beaconInput.txPower);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coefficient1, coefficient2, coefficient3, rssi, txPower);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BeaconInput {\n");
    
    sb.append("    coefficient1: ").append(toIndentedString(coefficient1)).append("\n");
    sb.append("    coefficient2: ").append(toIndentedString(coefficient2)).append("\n");
    sb.append("    coefficient3: ").append(toIndentedString(coefficient3)).append("\n");
    sb.append("    rssi: ").append(toIndentedString(rssi)).append("\n");
    sb.append("    txPower: ").append(toIndentedString(txPower)).append("\n");
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

