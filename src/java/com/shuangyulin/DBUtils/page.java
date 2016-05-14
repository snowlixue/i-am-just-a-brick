package com.shuangyulin.DBUtils;

import com.shuangyulin.DBUtils.DB;

import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;

public class page {

	int ShowPage = 1; // 设置想要显示的页数

	int RowCount = 0; // ResultSet的记录数目 初始值

	int PageCount = 0; // ResultSet分页后的总页数 初始值

	int redundance = 0; // 设置分页最后一页的记录数 初始值

	public String HttpFile;

	DB db; // 操作数据库对象

	public page() {
	}

	int Cint(String cint) {
		try {
			int n;
			n = Integer.parseInt(cint);
			return n;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public int getCount() throws Exception { // 获取数据总数
		db = new DB();
		int count = 0;
		ResultSet rs = null;
		try {
			String sql = "select count(id) as count from liuyan_temp";
			rs = db.executeQuery(sql);
			if (rs.next())
				count = rs.getInt(1);
		} catch (Exception e) {
			throw e;
		} finally {
			db.all_close();
		}
		return count;
	}

	// 主要工作函数，根据所给的条件从表中读取相应的记录

	public ResultSet myQuery(HttpServletRequest request,
			HttpServletResponse response, int PageSize) throws Exception {
		HttpFile = request.getRequestURI();
		RowCount = this.getCount();
		// if(Rowcount==0)无数据
		redundance = RowCount % PageSize;
		if (redundance == 0) { // 计算页数
			PageCount = RowCount / PageSize;
		} else {
			PageCount = (RowCount - redundance) / PageSize;
			PageCount++;
		}
		String ToPage = request.getParameter("ToPage");
		if (ToPage == "")
			ToPage = "1";
		ShowPage = Cint(ToPage);
		if (ShowPage > PageCount)
			ShowPage = PageCount;
		if (ShowPage <= 0)
			ShowPage = 1;
		String sql = "select * from liuyan_temp order by id desc limit "
				+ (ShowPage - 1) * PageSize + "," + PageSize + "";
		db = new DB();
		ResultSet rs = db.executeQuery(sql);
		return rs;

	}

	// 显示总页数
	public int getTotalPages() {
		return PageCount;
	}

	// 显示当前所在页数
	public int getCurrenPages() {
		return ShowPage;
	}

	// 显示数据总数
	public int getRowCount() {
		return RowCount;
	}

	public String PageFooter() // 显示上下翻页
	{
		String str = "";
		int next, prev;
		prev = ShowPage - 1;
		next = ShowPage + 1;
		str += "<form aciont=" + HttpFile + ">";
		str += "查询到<span>" + getRowCount() + "</span>条记录" + "    共<span>"
				+ getTotalPages() + "</span>页";
		str += " 第<span>" + getCurrenPages() + "</span>页 ";
		if (ShowPage > 1)
			str += " <A href=" + HttpFile + "?ToPage=1" + ">首页</A> ";
		else
			str += " 首页 ";
		if (ShowPage > 1)
			str += " <A href=" + HttpFile + "?ToPage=" + prev + ">上一页</A> ";
		else
			str += " 上一页 ";
		if (ShowPage < PageCount)
			str += " <A href=" + HttpFile + "?ToPage=" + next + ">下一页</A> ";
		else
			str += " 下一页 ";
		if (PageCount > 1 && ShowPage != PageCount)
			str += " <A href=" + HttpFile + "?ToPage=" + PageCount + ">尾页</A>";
		else
			str += " 尾页 ";
		str += "第<input name=ToPage type=text size=2>页 <input type=submit value=GO></form>";
		return str;
	}

	public void all_close() {
		db.all_close();
	}
}
