package com.shuangyulin.javabean;

/*本实体映射数据库中的员工销售业绩信息表
CREATE TABLE [dbo].[employeeSellResult] (
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//员工编号
	[employeeName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//员工姓名
	[employeeSellMoney] [float] NULL 									//销售金额
) ON [PRIMARY]
*/

public class EmployeeSellResult {
	private String employeeNo;
	private String employeeName;
	private float employeeSellMoney;
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
	public float getEmployeeSellMoney() {
		return employeeSellMoney;
	}
	public void setEmployeeSellMoney(float employeeSellMoney) {
		this.employeeSellMoney = employeeSellMoney;
	}
}
