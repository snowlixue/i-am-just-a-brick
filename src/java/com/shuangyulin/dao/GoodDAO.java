package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.Good;
import com.shuangyulin.javabean.GoodClass;

/*关于商品信息处理的业务处理类*/
public class GoodDAO {
	private String errMessage; /* 保存业务处理时发生的错误信息 */

	private final int pageSize = 8; /* 设置每页显示的商品数量 */

	private int recordCount = 0; /* 保存查询到的总的记录条数 */

	private int totalPage = 0; /* 符合查询条件的总的页数 */

	private int currentPage = 1; /* 当前要显示的页面 */

	private String goodNo = ""; /* 查询商品编号关键字 */

	private String goodName = ""; /* 查询商品名称关键字 */

	private int goodClassId = 0; /* 查询的商品类别 */

	public GoodDAO() {
		this.errMessage = "";
	}

	public String getErrMessage() {
		return errMessage;
	}

	/* 传入商品信息模型对象，进行商品信息的添加业务 */
	public boolean AddGoodInfo(Good good) {
		/* 验证商品编号输入不能为空 */
		if (good.getGoodNo().equals("")) {
			this.errMessage = "商品编号信息输入不能为空!";
			return false;
		}
		/* 验证商品名称输入不能为空 */
		if (good.getGoodName().equals("")) {
			this.errMessage = "商品名称输入不能为空!";
			return false;
		}
		/* 严整该商品名称在系统中是否已经存在 */
		String sqlString = "select count(*) from [goodInfo] where goodNo='"
				+ good.getGoodNo() + "'";
		DB db = new DB();
		try {
			if (db.getRecordCount(sqlString) != 0) {
				this.errMessage = "该商品编号已经存在了!";
				db.all_close();
				return false;

			}
			/* 如果所有验证通过就执行sql插入新商品信息 */
			sqlString = "insert into [goodInfo] "
					+ "(goodNo,goodClassId,goodName,goodUnit,goodModel,goodSpecs,"
					+ "goodPrice,goodPlace,goodMemo,goodAddTime) values ('";
			sqlString += good.getGoodNo() + "',";
			sqlString += good.getGoodClassId() + ",'";
			sqlString += good.getGoodName() + "','";
			sqlString += good.getGoodUnit() + "','";
			sqlString += good.getGoodModel() + "','";
			sqlString += good.getGoodSpecs() + "',";
			sqlString += good.getGoodPrice() + ",'";
			sqlString += good.getGoodPlace() + "','";
			sqlString += good.getGoodMemo() + "','";
			sqlString += good.getGoodAddTime() + "')";
			/* 然后增加该商品的库存量为0 */
			String sqlString2 = "insert into [goodStockInfo] (goodNo,goodCount) values ('"
					+ good.getGoodNo() + "',0)";
			String[] sqlStrings = new String[] { sqlString, sqlString2 };
			if (!db.excuteSqlStrings(sqlStrings)) {
				this.errMessage = "添加商品信息发生了错误!";
				return false;
			}
			return true;
		} catch (Exception e) {
			this.errMessage = "发生了数据错误!";
			db.all_close();
			e.printStackTrace();
			return false;
		}
	}

	/* 查询符合条件的商品信息 */
	public ArrayList QueryGoodInfo() {
		this.PrepareQuery(); /* 首先根据查询条件计算总的记录和总的页数 */
		ArrayList<Good> goodList = new ArrayList<Good>(); /* 保存符合条件的某页记录的集合链表 */
		String sqlString = "select * from [goodInfo] where 1=1";
		if (goodNo != "")
			sqlString += " and goodNo like '%" + goodNo + "%'";
		if (goodName != "")
			sqlString += " and goodName like '%" + goodName + "%'";
		if (goodClassId != 0)
			sqlString += " and goodClassId=" + goodClassId;
		/* 调用数据层进行查询 */
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sqlString);
			if (this.currentPage < 1)
				this.currentPage = 1;
			if (this.currentPage > this.totalPage)
				this.currentPage = this.totalPage;
			/* 计算要移动多少记录数才可以到达要显示的页面 */
			int moveRecordCount = this.pageSize * (this.currentPage - 1);
			for (int i = 0; i < moveRecordCount; i++)
				rs.next();
			/* 移动rs记录指针到达目标页后就对目标页的每条记录进行处理 */
			for (int i = 0; i < this.pageSize; i++) {
				if (rs.next()) {
					Good good = new Good();
					good.setGoodNo(rs.getString("goodNo"));
					good.setGoodClassId(rs.getInt("goodClassId"));
					good.setGoodName(rs.getString("goodName"));
					good.setGoodUnit(rs.getString("goodUnit"));
					good.setGoodModel(rs.getString("goodModel"));
					good.setGoodSpecs(rs.getString("goodSpecs"));
					good.setGoodPrice(rs.getFloat("goodPrice"));
					good.setGoodPlace(rs.getString("goodPlace"));
					good.setGoodMemo(rs.getString("goodMemo"));
					good.setGoodAddTime(rs.getTimestamp("goodAddTime"));
					goodList.add(good);
				} else {
					break;
				}
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodList;
	}

	/* 得到符合某个查询条件的总的页数 */
	public void PrepareQuery() {
		String sqlString = "select count(*) from [goodInfo] where 1=1";
		if (goodNo != "")
			sqlString += " and goodNo like '%" + goodNo + "%'";
		if (goodName != "")
			sqlString += " and goodName like '%" + goodName + "%'";
		if (goodClassId != 0)
			sqlString += " and goodClassId=" + goodClassId;
		DB db = new DB();
		try {
			/* 得到符合条件的总的记录数 */
			recordCount = db.getRecordCount(sqlString);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* 根据总的记录数和每页显示的记录条数计算总的页数 */
		this.totalPage = (this.recordCount + pageSize - 1) / this.pageSize;
	}

	/* 根据商品编号得到某个商品的详细信息 */
	public static Good GetGoodInfoByGoodNo(String goodNo) {
		Good good = new Good();
		String sql = "select * from [goodInfo] where goodNo='" + goodNo + "'";
		DB db = new DB();
		ResultSet rs;
		try {
			rs = db.executeQuery(sql);
			if (rs.next()) {
				/* 将数据库中的该商品信息保存在商品信息模型对象中 */
				good.setGoodNo(rs.getString("goodNo"));
				good.setGoodClassId(rs.getInt("goodClassId"));
				good.setGoodName(rs.getString("goodName"));
				good.setGoodUnit(rs.getString("goodUnit"));
				good.setGoodModel(rs.getString("goodModel"));
				good.setGoodSpecs(rs.getString("goodSpecs"));
				good.setGoodPrice(rs.getFloat("goodPrice"));
				good.setGoodPlace(rs.getString("goodPlace"));
				good.setGoodMemo(rs.getString("goodMemo"));
				good.setGoodAddTime(rs.getTimestamp("goodAddTime"));
			}
		} catch (Exception e) {
			System.out.println("遍历商品类别信息时发生了错误!");
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return good;
	}

	/* 更新商品信息 */
	public static int UpdateGoodInfo(Good good) {
		int result = 0;
		String sql = "update [goodInfo] set goodClassId=";
		sql += good.getGoodClassId() + ",goodName='";
		sql += good.getGoodName() + "',goodUnit='";
		sql += good.getGoodUnit() + "',goodModel='";
		sql += good.getGoodModel() + "',goodSpecs='";
		sql += good.getGoodSpecs() + "',goodPrice=";
		sql += good.getGoodPrice() + ",goodPlace='";
		sql += good.getGoodPlace() + "',goodMemo='";
		sql += good.getGoodMemo() + "' where goodNo='" + good.getGoodNo() + "'";
		DB db = new DB();
		try {
			result = db.executeUpdate(sql);
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static ArrayList<Good> QueryAllGoodInfo() {
		ArrayList<Good> goodList = new ArrayList<Good>();
		String sqlString = "select * from [goodInfo]";
		/* 调用数据层进行查询 */
		DB db = new DB();
		try {
			ResultSet rs = db.executeQuery(sqlString);
			while (rs.next()) {
				Good good = new Good();
				good.setGoodNo(rs.getString("goodNo"));
				good.setGoodClassId(rs.getInt("goodClassId"));
				good.setGoodName(rs.getString("goodName"));
				good.setGoodUnit(rs.getString("goodUnit"));
				good.setGoodModel(rs.getString("goodModel"));
				good.setGoodSpecs(rs.getString("goodSpecs"));
				good.setGoodPrice(rs.getFloat("goodPrice"));
				good.setGoodPlace(rs.getString("goodPlace"));
				good.setGoodMemo(rs.getString("goodMemo"));
				good.setGoodAddTime(rs.getTimestamp("goodAddTime"));
				goodList.add(good);
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getGoodClassId() {
		return goodClassId;
	}

	public void setGoodClassId(int goodClassId) {
		this.goodClassId = goodClassId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodNo() {
		return goodNo;
	}

	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
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

	public int getPageSize() {
		return pageSize;
	}
}
