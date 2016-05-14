package com.shuangyulin.javabean;

/*本实体类映射数据库中购物车信息表
CREATE TABLE [dbo].[goodCartInfo] (
	[goodCartId] [int] IDENTITY (1, 1) NOT NULL ,					//系统记录编号
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//员工编号
	[goodNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//商品编号
	[goodCount] [int] NULL 											//商品数量
) ON [PRIMARY]
*/
public class GoodCart {
	private int goodCartId;
	private String employeeNo;
	private String goodNo;
	private int goodCount;
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public int getGoodCartId() {
		return goodCartId;
	}
	public void setGoodCartId(int goodCartId) {
		this.goodCartId = goodCartId;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	
}
