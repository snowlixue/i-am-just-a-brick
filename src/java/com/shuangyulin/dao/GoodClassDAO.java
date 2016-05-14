package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;


import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.GoodClass;

/*关于商品类别处理的业务操作类*/

public class GoodClassDAO {
	/*保存业务处理的错误信息*/
	private String errMessage;
	
	public static ArrayList QueryAllGoodClassInfo(){
		ArrayList goodClassList = new ArrayList();
		/*查询商品类别信息表中的所有的商品类别信息*/
		String sql="select * from [goodClassInfo]";
		DB db=new DB();
		ResultSet rs;
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				/*将数据库中的每条商品类别信息保存在商品类别信息模型对象中*/
				GoodClass goodClass = new GoodClass();
				goodClass.setGoodClassId(rs.getInt("goodClassId"));
				goodClass.setGoodClassName(rs.getString("goodClassName"));
				goodClassList.add(goodClass);
			}
		} catch (Exception e) {
			System.out.println("遍历商品类别信息时发生了错误!");
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return goodClassList;
	}
	/*向系统中加入新的商品信息*/
	public boolean AddGoodClass(GoodClass goodClass){
		/*查询商品类别信息表中是否已经存在该商品类别信息*/
		String sql="select * from [goodClassInfo] where goodClassName='" + goodClass.getGoodClassName() + "'";
		DB db=new DB();
		boolean flag = true;
		ResultSet rs;
		try {
			rs = db.executeQuery(sql);
			if(rs.next()){
				flag = false;
				this.errMessage = "该商品类别已经存在了!";
				db.all_close();
			} else {
				sql = "insert into [goodClassInfo] (goodClassName) values ('" + goodClass.getGoodClassName() + "')";
				db.executeUpdate(sql);
				db.all_close();
			}
		} catch (Exception e) {
			flag = false;
			this.errMessage = "数据操作发生了错误!";
		} 
		return flag;
	}

	/*根据商品类别编号删除商品类别信息*/
	public boolean DeleteGoodClassById(int goodClassId) {
		boolean result = true;
		String sqlString = "select count(*) from [goodInfo] where goodClassId=" + goodClassId;
		DB db = new DB();
		try {
			if(db.getRecordCount(sqlString) != 0) {
				errMessage = "该商品类别下还存在商品信息，不能删除!";
				result = false;
				db.all_close();
			} else {
				sqlString = "delete from [goodClassInfo] where goodClassId=" + goodClassId;
				db.executeUpdate(sqlString);
				db.all_close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errMessage = "发生了数据错误!";
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/*根据商品类别编号得到商品类别名称　*/
	public static String GetGoodClassNameById(int goodClassId) {
		String goodClassName = "";
		String sqlString = "select goodClassName from [goodClassInfo] where goodClassId=" + goodClassId;
		DB db = new DB();
		goodClassName = db.GetScalarString(sqlString);
		db.all_close();
		return goodClassName;
	}
	
	public String getErrMessage() {
		return errMessage;
	}

}
