package com.shuangyulin.javabean;

/*本实体javabean对应数据库中的管理员信息表
 
CREATE TABLE [dbo].[admin] (
	[adminUsername] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//管理员帐号
	[adminPassword] [varchar] (32) COLLATE Chinese_PRC_CI_AS NULL 		//管理员密码
) ON [PRIMARY]
*/
public class Admin {
	private String adminUsername;
	private String adminPassword;
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
}
