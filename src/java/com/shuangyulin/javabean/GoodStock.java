package com.shuangyulin.javabean;

/*本实体类映射数据库中的商品库存信息表*
CREATE TABLE [dbo].[goodStockInfo] (
	[goodNo] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//商品编号
	[goodCount] [int] NULL 											//商品数量
) ON [PRIMARY]
*/
public class GoodStock {
	private String goodNo;
	private int goodCount;
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
