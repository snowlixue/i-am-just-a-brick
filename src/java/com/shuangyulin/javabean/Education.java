package com.shuangyulin.javabean;

/*本实体对应数据库中学历信息表
CREATE TABLE [dbo].[educationInfo] (
	[educationId] [int] IDENTITY (1, 1) NOT NULL ,						//学历层次编号
	[educationName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL		//学历层次名称
) ON [PRIMARY]
*/

public class Education {
  private int educationId;
  private String educationName;
public int getEducationId() {
	return educationId;
}
public void setEducationId(int educationId) {
	this.educationId = educationId;
}
public String getEducationName() {
	return educationName;
}
public void setEducationName(String educationName) {
	this.educationName = educationName;
}
}
