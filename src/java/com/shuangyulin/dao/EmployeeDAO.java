package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Admin;
import com.shuangyulin.javabean.Employee;
import com.shuangyulin.javabean.Good;

/*关于员工信息操作的业务操作类*/
public class EmployeeDAO {
	/*errMessage保存业务处理的错误消息*/
	private String errMessage;
	private final int  pageSize = 8; /*设置每页显示的员工数量*/
	private int recordCount = 0;	/*保存查询到的总的记录条数*/
	private int totalPage = 0;		/*符合查询条件的总的页数*/
	private int currentPage = 1; 	/*当前要显示的页面*/
	private String employeeNo = ""; /*查询员工的编号关键字*/
	private String employeeName = ""; /*查询员工的姓名关键字*/
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getErrMessage() {
		return errMessage;
	}

	/*验证员工登陆系统帐号和密码的正确行*/
	public static boolean checkLogin(Employee employee) {
		DB db = new DB();
		boolean flag = false;
		String sql = "select * from employeeInfo where employeeName='"
				+ employee.getEmployeeNo() + "' and employeePassword='"
				+ employee.getEmployeePassword() + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next())
				flag = true;
			else
				flag = false;
			
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return flag;
	}
	
	/*根据员工编号查询系统中是否已经存在该记录*/
	public boolean IsExistEmployee(String employeeNo) {
		boolean isExist = false;
		String sql = "select * from [employeeInfo] where employeeNo='" + employeeNo + "'";
		ResultSet rs = null;
		DB db = new DB();
		try {
			rs = db.executeQuery(sql);
			if(rs.next()) isExist = true;
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExist;
	}
	
	/*传入员工信息bean，执行该员工信息加入数据库*/
	public boolean AddEmployeeInfo(Employee employee) {
		/*首先查询系统中该员工编号是否已经存在*/
		if(IsExistEmployee(employee.getEmployeeNo())) {
			this.errMessage = "系统中已经存在了该员工编号!";
			return false;
		}
		String sql = "insert into [employeeInfo] " +
				"(employeeNo,employeeName,employeePassword,employeeSex," +
				"employeeBirthday,employeeEducationId,employeeHomeTel," +
				"employeeMobile,employeeCard,employeeEmail,employeeAddress) values ('";
		sql += employee.getEmployeeNo() + "','";
		sql += employee.getEmployeeName() + "','";
		sql += employee.getEmployeePassword() + "','";
		sql += employee.getEmployeeSex() + "','";
		sql += employee.getEmployeeBirthday() + "',";
		sql += employee.getEmployeeEducationId() + ",'";
		sql += employee.getEmployeeHomeTel() + "','";
		sql += employee.getEmployeeMobile() + "','";
		sql += employee.getEmployeeCard() + "','";
		sql += employee.getEmployeeEmail() + "','";
		sql += employee.getEmployeeAddress() + "')";
		
		boolean isSuccess = true;
		DB db = new DB();
		try {
			if(db.executeUpdate(sql) <= 0) {
				this.errMessage = "数据添加时发生了错误!";
				isSuccess = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.errMessage = "数据添加时发生了错误!";
			isSuccess = false;
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return isSuccess;
		
	}
	
	/*根据查询条件执行员工信息的查询*/
	public ArrayList QueryEmployeeInfo() {
		this.PrepareQuery();	/*首先根据查询条件计算总的记录和总的页数*/
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String sqlString = "select * from [employeeInfo] where 1=1";
		if(employeeNo != "")
			sqlString += " and employeeNo like '%" + employeeNo + "%'";
		if(employeeName != "")
			sqlString += " and employeeName like '%" + employeeName + "%'";
		/*调用数据层进行查询*/
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sqlString);
			if(this.currentPage < 1) this.currentPage = 1;
			if(this.currentPage > this.totalPage) this.currentPage = this.totalPage;
			/*计算要移动多少记录数才可以到达要显示的页面*/
			int moveRecordCount = this.pageSize * (this.currentPage - 1);
			for(int i=0;i<moveRecordCount;i++) rs.next();
			/*移动rs记录指针到达目标页后就对目标页的每条记录进行处理*/
			for(int i=0;i<this.pageSize;i++) {
				if(rs.next()) {
					Employee employee = new Employee();
					employee.setEmployeeNo(rs.getString("employeeNo"));
					employee.setEmployeeName(rs.getString("employeeName"));
					employee.setEmployeePassword(rs.getString("employeePassword"));
					employee.setEmployeeSex(rs.getString("employeeSex"));
					employee.setEmployeeBirthday(rs.getDate("employeeBirthday"));
					employee.setEmployeeEducationId(rs.getInt("employeeEducationId"));
					employee.setEmployeeHomeTel(rs.getString("employeeHomeTel"));
					employee.setEmployeeMobile(rs.getString("employeeMobile"));
					employee.setEmployeeCard(rs.getString("employeeCard"));
					employee.setEmployeeEmail(rs.getString("employeeEmail"));
					employee.setEmployeeAddress(rs.getString("employeeAddress"));
					employeeList.add(employee);
				} else {
					break;
				}
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeList;
	}

	private void PrepareQuery() {
		String sqlString = "select count(*) from [employeeInfo] where 1=1";
		if(employeeNo != "")
			sqlString += " and employeeNo like '%" + employeeNo + "%'";
		if(employeeName != "")
			sqlString += " and employeeName like '%" + employeeName + "%'";
		DB db = new DB();
		try {
			/*得到符合条件的总的记录数*/
			recordCount = db.getRecordCount(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*根据总的记录数和每页显示的记录条数计算总的页数*/
		this.totalPage = (this.recordCount + pageSize - 1) / this.pageSize;
		
	}
	
	/*根据员工编号查询该员工的详细信息并返回*/
	public static Employee GetEmployeeInfoByNo(String employeeNo) {
		Employee employee = null;
		String sql = "select * from [employeeInfo] where employeeNo='" + employeeNo + "'";
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sql);
			if(rs.next()) {
				employee = new Employee();
				employee.setEmployeeNo(employeeNo);
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setEmployeePassword(rs.getString("employeePassword"));
				employee.setEmployeeSex(rs.getString("employeeSex"));
				employee.setEmployeeBirthday(rs.getDate("employeeBirthday"));
				employee.setEmployeeEducationId(rs.getInt("employeeEducationId"));
				employee.setEmployeeHomeTel(rs.getString("employeeHomeTel"));
				employee.setEmployeeMobile(rs.getString("employeeMobile"));
				employee.setEmployeeCard(rs.getString("employeeCard"));
				employee.setEmployeeEmail(rs.getString("employeeEmail"));
				employee.setEmployeeAddress(rs.getString("employeeAddress"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
	/*传入员工实体模型，执行该员工信息的更新操作*/
	public int UpdateEmployeeInfo(Employee employee) {
		/*构造更新的sql语句*/
		String sql = "update [employeeInfo] set employeeName='";
		sql += employee.getEmployeeName() + "',employeeSex='";
		sql += employee.getEmployeeSex() + "',employeeEducationId=";
		sql += employee.getEmployeeEducationId() + ",employeeBirthday='";
		sql += employee.getEmployeeBirthday() + "',employeePassword='";
		sql += employee.getEmployeePassword() + "',employeeMobile='";
		sql += employee.getEmployeeMobile() + "',employeeCard='";
		sql += employee.getEmployeeCard() + "',employeeEmail='";
		sql += employee.getEmployeeEmail() + "',employeeAddress='";
		sql += employee.getEmployeeAddress() + "' where employeeNo='" + employee.getEmployeeNo() + "'";
		/*调用数据层执行更新*/
		DB db = new DB();
		int result = 0;
		try {
			result = db.executeUpdate(sql);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static boolean ChangePassword(Employee employee) {
		try {
			DB db = new DB();
			String sql = "update [employeeInfo] set employeePassword='" + employee.getEmployeePassword() + "' where employeeNo='" + employee.getEmployeeNo() + "'";
			int result = db.executeUpdate(sql);
			db.all_close();
			if( result > 0)
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
