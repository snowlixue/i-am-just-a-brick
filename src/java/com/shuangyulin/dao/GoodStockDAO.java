package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.GoodStock;

public class GoodStockDAO {
	private String errMessage;
	private String goodNo;	/*查询库存的商品编号关键字*/
	private String goodName;/*查询库存的商品名称关键字*/
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
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
	
	/*根据查询条件实现商品库存信息的查询*/
    public ArrayList<GoodStock> QueryGoodStockInfo()
    {
    	ArrayList<GoodStock> goodStockList = new ArrayList<GoodStock>();
        String sqlString = "select * from [goodStockInfoView] where 1=1";
        if(!goodNo.equals(""))
            sqlString += " and goodNo like '%" + goodNo + "%'";
        if (!goodName.equals(""))
            sqlString += " and goodName like '%" + goodName + "%'";
        try {
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			while(rs.next()) {
				GoodStock goodStock = new GoodStock();
				goodStock.setGoodNo(rs.getString("goodNo"));
				goodStock.setGoodCount(rs.getInt("goodCount"));
				goodStockList.add(goodStock);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return goodStockList;
    }
    /*查询商品库存过多或过少的商品库存信息*/
    public ArrayList<GoodStock> QueryGoodStockWarningInfo()
    {
    	ArrayList<GoodStock> goodStockList = new ArrayList<GoodStock>();
        String sqlString = "select * from [goodStockInfoView] where goodCount>200 or goodCount<20";
        try {
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			while(rs.next()) {
				GoodStock goodStock = new GoodStock();
				goodStock.setGoodNo(rs.getString("goodNo"));
				goodStock.setGoodCount(rs.getInt("goodCount"));
				goodStockList.add(goodStock);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return goodStockList;
    }
	
}
