package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Supplier;

public class SupplierDAO {
	private String errMessage;
	public String getErrMessage() {
		return errMessage;
	}


	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}


	/*查询所有的供应商信息并返回*/
	public static ArrayList<Supplier> QueryAllSupplier() {
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		String sql = "select * from [supplierInfo]";
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sql);
			while(rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setSupplierName(rs.getString("supplierName"));
				supplier.setSupplierLawyer(rs.getString("supplierLawyer"));
				supplier.setSupplierTelephone(rs.getString("supplierTelephone"));
				supplier.setSupplierAddress(rs.getString("supplierAddress"));
				supplierList.add(supplier);
			}
			db.all_close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return supplierList;
	}
	
	
	/*传入供应商信息模型对象，实现供应商信息的添加操作*/
    public boolean AddSupplierInfo(Supplier supplier)
    {
    	if(supplier.getSupplierName().equals("")) {
    		this.errMessage = "供应商名称信息不能为空!";
    		return false;
    	}
        /*首先查询该供应商名称是否存在*/
        String sqlString = "select * from [supplierInfo] where supplierName='" + supplier.getSupplierName() + "'";
        try {
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			if(rs.next()) {
				this.errMessage = "该供应商名称信息已经存在!";
				db.all_close();
				return false;
			}
			/*构造sql语句，实现新的供应商信息的加入*/
			sqlString = "insert into [supplierInfo] (supplierName,supplierLawyer,supplierTelephone,supplierAddress) values ('";
			sqlString += supplier.getSupplierName() + "','";
			sqlString += supplier.getSupplierLawyer() + "','";
			sqlString += supplier.getSupplierTelephone() + "','";
			sqlString += supplier.getSupplierAddress() + "')";
			db.executeUpdate(sqlString);
			db.all_close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
}
