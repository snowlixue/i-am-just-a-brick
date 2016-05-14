package com.shuangyulin.javabean;

import java.sql.Date;

/*本实体javabean对应数据库中的员工信息表
CREATE TABLE [dbo].[employeeInfo] (						
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//员工编号
	[employeeName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//员工姓名
	[employeePassword] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,	//员工登陆密码
	[employeeSex] [nchar] (1) COLLATE Chinese_PRC_CI_AS NULL ,			//员工性别
	[employeeBirthday] [datetime] NULL ,								//员工生日
	[employeeEducationId] [int] NULL ,									//教育层次编号
	[employeeHomeTel] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//家庭电话
	[employeeMobile] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//移动电话
	[employeeCard] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//身份证号
	[employeeEmail] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,		//邮件地址
	[employeeAddress] [nvarchar] (80) COLLATE Chinese_PRC_CI_AS NULL 	//居住地址
) ON [PRIMARY]
*/
public class Employee {
	private String employeeNo;
	private String employeeName;
	private String employeePassword;
	private String employeeSex;
	private Date employeeBirthday;
	private int employeeEducationId;
	private String employeeHomeTel;
	private String employeeMobile;
	private String employeeCard;
	private String employeeEmail;
	private String employeeAddress;
	
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public Date getEmployeeBirthday() {
		return employeeBirthday;
	}
	public void setEmployeeBirthday(Date employeeBirthday) {
		this.employeeBirthday = employeeBirthday;
	}
	public String getEmployeeCard() {
		return employeeCard;
	}
	public void setEmployeeCard(String employeeCard) {
		this.employeeCard = employeeCard;
	}
	public int getEmployeeEducationId() {
		return employeeEducationId;
	}
	public void setEmployeeEducationId(int employeeEducationId) {
		this.employeeEducationId = employeeEducationId;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeHomeTel() {
		return employeeHomeTel;
	}
	public void setEmployeeHomeTel(String employeeHomeTel) {
		this.employeeHomeTel = employeeHomeTel;
	}
	public String getEmployeeMobile() {
		return employeeMobile;
	}
	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
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
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public String getEmployeeSex() {
		return employeeSex;
	}
	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}
	
}
