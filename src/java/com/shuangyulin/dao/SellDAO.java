package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Employee;
import com.shuangyulin.javabean.EmployeeSellResult;
import com.shuangyulin.javabean.SellInfo;

public class SellDAO {
	private String errMessage; /*保存业务逻辑处理的错误*/
	private String employeeNo;	/*查询的员工编号*/
	private String sellNo;		/*查询的销售编号*/
	private String startDate;	/*查询的开始日期*/
	private String endDate;		/*查询的结束日期*/
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
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
	
	/*传入商品销售信息模型，实现销售信息的登记操作*/
    public static boolean AddSellInfo(SellInfo sellInfo)
    {
        String sqlString = "insert into [sellInfo] (sellNo,goodNo,price,number,totalPrice,sellTime,employeeNo) values ('";
        sqlString += sellInfo.getSellNo() + "','";
        sqlString += sellInfo.getGoodNo() + "',";
        sqlString += sellInfo.getPrice() + ",";
        sqlString += sellInfo.getNumber() + ",";
        sqlString += sellInfo.getTotalPrice() + ",'";
        sqlString += sellInfo.getSellTime() + "','";
        sqlString += sellInfo.getEmployeeNo() + "')";
        DB db = new DB();
        try {
			if(db.executeUpdate(sqlString)>0)
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
	public ArrayList<EmployeeSellResult> QueryEmployeeSellResult() {
		/*生成员工业绩*/
		this.MakeEmployeeSellResult();
		ArrayList<EmployeeSellResult> employeeSellResultList = new ArrayList<EmployeeSellResult>();
		try {
			DB db = new DB();
			/*所有员工在该时间段内的销售业绩计算完毕后就返回该结果集(按销售额排序)*/
			String sqlString = "select employeeNo,employeeName,employeeSellMoney from [employeeSellResult] order by employeeSellMoney DESC";
			ResultSet rs = db.executeQuery(sqlString);
			while(rs.next()) {
				EmployeeSellResult employeeSellResult = new EmployeeSellResult();
				employeeSellResult.setEmployeeName(rs.getString("employeeName"));
				//String employeeNo = rs.getString("employeeNo");
				//employeeSellResult.setEmployeeNo(employeeNo);
				employeeSellResult.setEmployeeSellMoney(rs.getFloat("employeeSellMoney"));
				employeeSellResultList.add(employeeSellResult);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeSellResultList;
	}
	
	public void MakeEmployeeSellResult() {
		/*首先清空员工销售业绩信息表中的信息(因为要对其进行重新统计)*/
		DB db;
		try {
			db = new DB();
			String sqlString = "delete from [employeeSellResult]";
			db.executeUpdate(sqlString);
			db.all_close();
			/*查询所有的员工信息*/
			sqlString = "select employeeNo,employeeName from [employeeInfo]";
			ResultSet rs = db.executeQuery(sqlString);
			//EmployeeDAO employeeDAO = new EmployeeDAO();
			//employeeDAO.setEmployeeName("");
			//employeeDAO.setEmployeeNo("");
			//ArrayList<Employee> employeeList = (ArrayList<Employee>)employeeDAO.QueryEmployeeInfo();
			/*遍历每个员工记录，统计该员工在查询时间类的销售总金额*/
			//for(int i=0;i<employeeList.size();i++)
			while(rs.next())
			{
				//Employee employee = employeeList.get(i);
			    String employeeNo = rs.getString("employeeNo");
			    String employeeName = rs.getString("employeeName");
			    float employeeSellMoney = this.GetEmployeeSellMoney(employeeNo); /*计算员工的销售业绩*/
			    /*将该员工该时间内的销售业绩加入到信息表中*/
			    sqlString = "insert into [employeeSellResult] (employeeNo,employeeName,employeeSellMoney) values ('";
			    sqlString += employeeNo + "','";
			    sqlString += employeeName + "',";
			    sqlString += employeeSellMoney + ")";
			    db.executeUpdate(sqlString);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public float GetEmployeeSellMoney(String employeeNo) {
		float employeeSellMoney = 0.0f;
		/*查询该员工在指定时间内的销售业绩*/
	    String sqlString = "select sum(totalPrice) as employeeSellMoney from [sellInfo] where employeeNo='" + employeeNo + "'";
	    if (!startDate.equals(""))
	        sqlString += " and sellTime >= '" + startDate + "'";
	    if (!endDate.equals(""))
	        sqlString += " and sellTime <= '" + endDate + "'";
		try {
			DB tempdb = new DB();
			ResultSet rs = tempdb.executeQuery(sqlString);
			if(rs.next())
			  employeeSellMoney = rs.getFloat("employeeSellMoney");
			tempdb.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return employeeSellMoney;
	}
	public ArrayList<SellInfo> QuerySellInfo() {
		ArrayList<SellInfo> sellInfoList = new ArrayList<SellInfo>();
		/*根据查询条件构造sql语句*/
		String sql = "select * from [sellInfo] where 1=1";
		if(!employeeNo.equals(""))
			sql += " and employeeNo like '%" + employeeNo + "%'";
		if(!sellNo.equals(""))
			sql += " and sellNo like '%" + sellNo + "%'";
		if(!startDate.equals(""))
			sql += " and sellTime > '" + startDate + "'";
		if(!endDate.equals(""))
			sql += " and sellTime < '" + endDate + "'";
		/*调用数据层执行查询*/
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				SellInfo sellInfo = new SellInfo();
				sellInfo.setSellInfoId(rs.getInt("sellInfoId"));
				sellInfo.setSellNo(rs.getString("sellNo"));
				sellInfo.setGoodNo(rs.getString("goodNo"));
				sellInfo.setPrice(rs.getFloat("price"));
				sellInfo.setNumber(rs.getInt("number"));
				sellInfo.setTotalPrice(rs.getFloat("totalPrice"));
				sellInfo.setSellTime(rs.getTimestamp("sellTime"));
				sellInfo.setEmployeeNo(rs.getString("employeeNo"));
				sellInfoList.add(sellInfo);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sellInfoList;
	}
	
}
