package com.shuangyulin.javabean;

/*本实体javabean对应数据库中的商品类别信息表
CREATE TABLE [dbo].[goodClassInfo] (
	[goodClassId] [int] IDENTITY (1, 1) NOT NULL ,						//商品类别编号
	[goodClassName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL 		//商品类别名称
) ON [PRIMARY]
 */
public class GoodClass {
	private int goodClassId;
	private String goodClassName;
	public int getGoodClassId() {
		return goodClassId;
	}
	public void setGoodClassId(int goodClassId) {
		this.goodClassId = goodClassId;
	}
	public String getGoodClassName() {
		return goodClassName;
	}
	public void setGoodClassName(String goodClassName) {
		this.goodClassName = goodClassName;
	}
	
}
