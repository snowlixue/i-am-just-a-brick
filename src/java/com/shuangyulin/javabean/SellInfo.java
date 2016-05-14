package com.shuangyulin.javabean;
/*本实体映射数据库中的销售信息表
CREATE TABLE [dbo].[sellInfo] (
	[sellInfoId] [int] IDENTITY (1, 1) NOT NULL ,				//系统记录编号
	[sellNo] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,//销售单据编号
	[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//商品编号
	[price] [float] NULL ,										//销售单价
	[number] [int] NULL ,										//销售数量
	[totalPrice] [float] NULL ,									//销售总价
	[sellTime] [datetime] NULL ,								//销售时间
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL 	//销售的员工编号
) ON [PRIMARY]
*/
public class SellInfo {
	private int sellInfoId;
	private String sellNo;
	private String goodNo;
	private float price;
	private int number;
	private float totalPrice;
	private java.sql.Timestamp sellTime;
	private String employeeNo;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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
	public int getSellInfoId() {
		return sellInfoId;
	}
	public void setSellInfoId(int sellInfoId) {
		this.sellInfoId = sellInfoId;
	}
	public String getSellNo() {
		return sellNo;
	}
	public void setSellNo(String sellNo) {
		this.sellNo = sellNo;
	}
	public java.sql.Timestamp getSellTime() {
		return sellTime;
	}
	public void setSellTime(java.sql.Timestamp sellTime) {
		this.sellTime = sellTime;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public SellInfo() {
		sellInfoId = 0;
		sellNo = "";
		goodNo = "";
		price = 0.0f;
		number = 0;
		totalPrice = 0.0f;
		java.util.Date nowTime = new java.util.Date();
		sellTime = new java.sql.Timestamp(nowTime.getTime());
		employeeNo = "";
	}
}
