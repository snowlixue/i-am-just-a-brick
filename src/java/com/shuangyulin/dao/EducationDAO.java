package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Education;

/*关于学历信息操作的业务处理类*/
public class EducationDAO {

	/*查询所有的学历信息并返回*/
	public static ArrayList QueryAllEducation() {
		ArrayList educationList = new ArrayList();
		String sql = "select * from [educationInfo]";
		ResultSet rs;
		DB db = new DB();
		try {
			rs = db.executeQuery(sql);
			while(rs.next()) {
				Education education = new Education();
				education.setEducationId(rs.getInt("educationId"));
				education.setEducationName(rs.getString("educationName"));
				educationList.add(education);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return educationList;
	}
}
