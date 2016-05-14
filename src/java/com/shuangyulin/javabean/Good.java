package com.shuangyulin.javabean;

import java.sql.Date;
import java.text.SimpleDateFormat;

/*本数据实体类对应数据库中的商品信息表*/
/*商品信息表
CREATE TABLE [dbo].[goodInfo] (
	[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,		//商品编号
	[goodClassId] [int] NULL ,											//商品类别编号
	[goodName] [nvarchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,		//商品名称
	[goodUnit] [nvarchar] (2) COLLATE Chinese_PRC_CI_AS NULL ,			//商品单位
	[goodModel] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//商品型号
	[goodSpecs] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//商品规格
	[goodPrice] [float] NULL ,											//商品出售单价
	[goodPlace] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,		//商品生产地
	[goodMemo] [char] (10) COLLATE Chinese_PRC_CI_AS NULL ,				//商品附加信息
	[goodAddTime] [datetime] NULL 										//商品加入时间
) ON [PRIMARY]*/
public class Good {
	private String goodNo;
	private int goodClassId;
	private String goodName;
	private String goodUnit;
	private String goodModel;
	private String goodSpecs;
	private float goodPrice;
	private String goodPlace;
	private String goodMemo;
	private java.sql.Timestamp goodAddTime;
	public java.sql.Timestamp getGoodAddTime() {
		return goodAddTime;
	}
	public void setGoodAddTime(java.sql.Timestamp goodAddTime) {
		this.goodAddTime = goodAddTime;
	}
	public int getGoodClassId() {
		return goodClassId;
	}
	public void setGoodClassId(int goodClassId) {
		this.goodClassId = goodClassId;
	}
	public String getGoodMemo() {
		return goodMemo;
	}
	public void setGoodMemo(String goodMemo) {
		this.goodMemo = goodMemo;
	}
	public String getGoodModel() {
		return goodModel;
	}
	public void setGoodModel(String goodModel) {
		this.goodModel = goodModel;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	public String getGoodPlace() {
		return goodPlace;
	}
	public void setGoodPlace(String goodPlace) {
		this.goodPlace = goodPlace;
	}
	public float getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(float goodPrice) {
		this.goodPrice = goodPrice;
	}
	public String getGoodSpecs() {
		return goodSpecs;
	}
	public void setGoodSpecs(String goodSpec) {
		this.goodSpecs = goodSpec;
	}
	public String getGoodUnit() {
		return goodUnit;
	}
	public void setGoodUnit(String goodUnit) {
		this.goodUnit = goodUnit;
	}
	
	public void test() {
		java.util.Date date=new java.util.Date(); 
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println(df.format(date)); 
	}
}
