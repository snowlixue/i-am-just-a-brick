package com.shuangyulin.javabean;

import java.sql.Date;
import java.sql.Timestamp;

/*本实体映射数据库中的商品进货退货信息表
CREATE TABLE [dbo].[buyBackInfo] (
		[buyBackId] [int] IDENTITY (1, 1) NOT NULL ,					//系统记录编号
		[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//商品编号
		[supplierName] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,	//供应商
		[price] [float] NULL ,											//退货单价
		[number] [int] NULL ,											//退货数量
		[totalPrice] [float] NULL ,										//退货总金额
		[buyBackDate] [datetime] NULL ,									//退货日期
		[buyBackReason] [text] COLLATE Chinese_PRC_CI_AS NULL ,			//退货原因
		[buyBackAddTime] [datetime] NULL 								//退货信息录入时间
	) ON [PRIMARY]
*/
public class BuyBackInfo {
	private int buyBackId;
	private String goodNo;
	private String supplierName;
	private float price;
	private int number;
	private float totalPrice;
	private java.sql.Date buyBackDate;
	private String buyBackReason;
	private java.sql.Timestamp buyBackAddTime;
	public java.sql.Timestamp getBuyBackAddTime() {
		return buyBackAddTime;
	}
	public void setBuyBackAddTime(java.sql.Timestamp buyBackAddTime) {
		this.buyBackAddTime = buyBackAddTime;
	}
	public java.sql.Date getBuyBackDate() {
		return buyBackDate;
	}
	public void setBuyBackDate(java.sql.Date buyBackDate) {
		this.buyBackDate = buyBackDate;
	}
	public int getBuyBackId() {
		return buyBackId;
	}
	public void setBuyBackId(int buyBackId) {
		this.buyBackId = buyBackId;
	}
	public String getBuyBackReason() {
		return buyBackReason;
	}
	public void setBuyBackReason(String buyBackReason) {
		this.buyBackReason = buyBackReason;
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BuyBackInfo() {
		buyBackId = 0;
		goodNo = supplierName = buyBackReason = "";
		price = 0.0f;
		number = 0;
		totalPrice = 0.0f;
		java.util.Date nowDate = new java.util.Date();
		buyBackDate = new Date(nowDate.getTime());
		buyBackAddTime = new Timestamp(nowDate.getTime());
	}
	
}
