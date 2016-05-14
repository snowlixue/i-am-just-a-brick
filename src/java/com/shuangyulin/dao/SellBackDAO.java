package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.SellBackInfo;

public class SellBackDAO {
	private String errMessage; /*保存业务逻辑处理的错误*/
	private String goodNo;		/*查询的商品编号*/
	private String sellNo;		/*查询的销售编号*/
	private String startDate;	/*查询的开始日期*/
	private String endDate;		/*查询的结束日期*/
	private float totalPrice = 0.0f;	/*当前查询条件下的所有销售退货总价格*/
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	public String getSellNo() {
		return sellNo;
	}
	public void setSellNo(String sellNo) {
		this.sellNo = sellNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/*传入商品退货信息模型对象，实现顾客退货的业务操作*/
    public boolean AddSellBackInfoAdd(SellBackInfo sellBackInfo,boolean isGood)
    {
        try {
			/*查询销售信息表中是否存在该销售单据号*/
			String sqlString = "select COUNT(*) as count from [sellInfo] where sellNo='" + sellBackInfo.getSellNo() + "'";
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			int count = 0;
			if(rs.next())
				count = rs.getInt("count");
			if(0 == count) {
				 this.errMessage = "你输入的销售单据号不存在!";
			     return false;
			}
			db.all_close();
			/*判断该次销售中是否存在该商品的信息*/
			sqlString = "select COUNT(*) as count from [sellInfo] where sellNo='" + sellBackInfo.getSellNo() + "' and goodNo='" + sellBackInfo.getGoodNo() + "'";
			rs = db.executeQuery(sqlString);
			count = 0 ;
			if(rs.next())
				count = rs.getInt("count");
			if(0 == count) {
				 this.errMessage = "该次销售没有该商品的信息！";
			     return false;
			}
			db.all_close();
			/*判断退货的商品数量是否正确*/
			sqlString = "select number from [sellInfo] where sellNo='" + sellBackInfo.getSellNo() + "' and goodNo='" + sellBackInfo.getGoodNo() + "'";
			int number = 0;
			rs = db.executeQuery(sqlString);
			if(rs.next())
				number = rs.getInt("number");
			if(sellBackInfo.getNumber() > number)
			{
			    this.errMessage = "你的退货数量不能大于销售时的数量!";
			    return false;
			}
			db.all_close();
			/*通过验证后执行商品退货信息的加入*/
			sqlString = "insert into [sellBackInfo] (sellNo,goodNo,price,number,totalPrice,sellBackReason,sellBackTime) values ('";
			sqlString += sellBackInfo.getSellNo() + "','";
			sqlString += sellBackInfo.getGoodNo() + "',";
			sqlString += sellBackInfo.getPrice() + ",";
			sqlString += sellBackInfo.getNumber() + ",";
			sqlString += sellBackInfo.getTotalPrice() + ",'";
			sqlString += sellBackInfo.getSellBackReason() + "','";
			sqlString += sellBackInfo.getSellBackTime() + "')";
			db.executeUpdate(sqlString);
			db.all_close();
			/*如果退回的商品是完好的，需要将商品入库*/
			if (isGood)
			{
			    sqlString = "update [goodStockInfo] set goodCount = goodCount + " + sellBackInfo.getNumber(); ;
			    db.executeUpdate(sqlString);
			    db.all_close();
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    /*根据商品编号，销售单据，开始时间和结束时间查询商品退货信息*/
    public ArrayList<SellBackInfo> QuerySellBackInfo(String goodNo,String sellNo, String startDate, String endDate)
    {
    	ArrayList<SellBackInfo> sellBackInfoList = new ArrayList<SellBackInfo>();
    	/*根据查询条件构造sql语句*/
        String sqlString = "select * from [sellBackInfo] where 1=1";
        if (goodNo != "")
            sqlString += " and goodNo like '%" + goodNo + "%'";
        if (sellNo != "")
            sqlString += " and sellNo like '%" + sellNo + "%'";
        if (startDate != "")
            sqlString += " and sellBackTime >= '" + startDate + "'";
        if (endDate != "")
            sqlString += " and sellBackTime <= '" + endDate + "'";
        try {
			/*调用数据层执行查询*/
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			while(rs.next()) {
				SellBackInfo sellBackInfo = new SellBackInfo();
				sellBackInfo.setSellBackId(rs.getInt("sellBackId"));
				sellBackInfo.setSellNo(rs.getString("sellNo"));
				sellBackInfo.setGoodNo(rs.getString("goodNo"));
				//sellBackInfo.setGoodName(rs.getString("goodName"));
				sellBackInfo.setPrice(rs.getFloat("price"));
				sellBackInfo.setNumber(rs.getInt("number"));
				sellBackInfo.setTotalPrice(rs.getFloat("totalPrice"));
				sellBackInfo.setSellBackReason(rs.getString("sellBackReason"));
				sellBackInfo.setSellBackTime(rs.getTimestamp("sellBackTime"));
				
				totalPrice += sellBackInfo.getTotalPrice();
				sellBackInfoList.add(sellBackInfo);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sellBackInfoList;
       
    }
	
	
}
