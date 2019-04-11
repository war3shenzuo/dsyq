package com.etop.management.entity;
import java.math.BigDecimal;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServicePurchaseEntity<br>
 */
public class EtopServicePurchase extends EtopService{
	
		private java.lang.String purchaseId;//   id	private java.lang.String serviceId;//   服务id	private java.lang.String goodsId;//   商品id	private java.lang.String goodsName;//   商品名称	private java.lang.String unit;//   单位	private BigDecimal unitPrice;//   单价	private java.lang.Integer number;//   数量	private BigDecimal totalPrice;//   总价	private java.lang.String description;//   描述	public java.lang.String getPurchaseId() {	    return this.purchaseId;	}	public void setPurchaseId(java.lang.String purchaseId) {	    this.purchaseId=purchaseId;	}	public java.lang.String getServiceId() {	    return this.serviceId;	}	public void setServiceId(java.lang.String serviceId) {	    this.serviceId=serviceId;	}	public java.lang.String getGoodsId() {	    return this.goodsId;	}	public void setGoodsId(java.lang.String goodsId) {	    this.goodsId=goodsId;	}	public java.lang.String getGoodsName() {	    return this.goodsName;	}	public void setGoodsName(java.lang.String goodsName) {	    this.goodsName=goodsName;	}	public java.lang.String getUnit() {	    return this.unit;	}	public void setUnit(java.lang.String unit) {	    this.unit=unit;	}	public BigDecimal getUnitPrice() {	    return this.unitPrice;	}	public void setUnitPrice(BigDecimal unitPrice) {	    this.unitPrice=unitPrice;	}	public java.lang.Integer getNumber() {	    return this.number;	}	public void setNumber(java.lang.Integer number) {	    this.number=number;	}	public BigDecimal getTotalPrice() {	    return this.totalPrice;	}	public void setTotalPrice(BigDecimal totalPrice) {	    this.totalPrice=totalPrice;	}	public java.lang.String getDescription() {	    return this.description;	}	public void setDescription(java.lang.String description) {	    this.description=description;	}
}

