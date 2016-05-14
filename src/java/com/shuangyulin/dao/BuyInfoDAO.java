package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.BuyInfo;
import com.shuangyulin.javabean.Employee;

/*关于商品进货信息操作的业务操作类*/
public class BuyInfoDAO {
	/* errMessage保存业务处理的错误消息 */
	private String errMessage;

	private final int pageSize = 3; /* 设置每页显示的进货记录数量 */

	private int recordCount = 0; /* 保存查询到的总的记录条数 */

	private int totalPage = 0; /* 符合查询条件的总的页数 */

	private int currentPage = 1; /* 当前要显示的页面 */
	
	private String goodNo;	/*查询商品编号的关键字*/
	private String goodName; /*查询商品名称的关键字*/
	private int goodClassId; /*查询的商品类别*/
	private String startDate; /*查询的开始日期*/
	private String endDate; /*查询的结束日期*/
	private float totalPrice = 0.0f; /*符合条件的进货总价*/

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getGoodClassId() {
		return goodClassId;
	}

	public void setGoodClassId(int goodClassId) {
		this.goodClassId = goodClassId;
	}

	public String getGoodNo() {
		return goodNo;
	}

	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
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

	/* 执行商品进货信息的登记操作 */
	public boolean AddBuyInfo(BuyInfo buyInfo) {
		try {
			/* 首先验证管理员输入的商品编号在系统中是否存在 */
			String sqlString = "select * from [goodInfo] where goodNo='"
					+ buyInfo.getGoodNo() + "'";
			DB db = new DB();
			ResultSet rs = db.executeQuery(sqlString);
			if (!rs.next()) {
				this.errMessage = "该商品不存在!";
				db.all_close();
				return false;
			}

			/* 构造sql语句实现进货信息的加入操作 */
			String insertString = "insert into [buyInfo] (goodNo,supplierName,price,number,totalPrice,buyDate,addTime) values ('";
			insertString += buyInfo.getGoodNo() + "','";
			insertString += buyInfo.getSupplierName() + "',";
			insertString += buyInfo.getPrice() + ",";
			insertString += buyInfo.getNumber() + ",";
			insertString += buyInfo.getTotalPrice() + ",'";
			insertString += buyInfo.getBuyDate() + "','";
			insertString += buyInfo.getAddTime() + "')";

			/* 增加对应商品的库存的sql */
			String updateString = "update [goodStockInfo] set goodCount = goodCount + "
					+ buyInfo.getNumber()
					+ " where goodNo='"
					+ buyInfo.getGoodNo()
					+ "'";

			String sqlStrings[] = new String[] { insertString, updateString };
			boolean result = db.excuteSqlStrings(sqlStrings);
			if (!result) {
				this.errMessage = "登记商品进货信息时发生了错误!";
				db.all_close();
				return false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*根据查询条件执行进货信息的查询*/
	public ArrayList QueryBuyInfo() {
		this.PrepareQuery();	/*首先根据查询条件计算总的记录数，总的页数，总的价格*/
		ArrayList<BuyInfo> buyInfoList = new ArrayList<BuyInfo>();
		String sqlString = "select * from [buyInfoView] where 1=1";
		if(!goodNo.equals(""))
			sqlString += " and goodNo like '%" +goodNo + "%'";
		if(!goodName.equals(""))
			sqlString += " and goodName like '%" + goodName + "%'";
		if(0 != goodClassId)
			sqlString += " and goodClassId=" + goodClassId;
		if(!startDate.equals(""))
			sqlString += " and buyDate>='" + startDate + "'";
		if(!endDate.equals(""))
			sqlString += " and buyDate<='" + endDate + "'";
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
					BuyInfo buyInfo = new BuyInfo();
					buyInfo.setBuyId(rs.getInt("buyId"));
					buyInfo.setGoodNo(rs.getString("goodNo"));
					buyInfo.setSupplierName(rs.getString("supplierName"));
					buyInfo.setPrice(rs.getFloat("price"));
					buyInfo.setNumber(rs.getInt("number"));
					buyInfo.setTotalPrice(rs.getFloat("totalPrice"));
					buyInfo.setBuyDate(rs.getDate("buyDate"));
					buyInfo.setAddTime(rs.getTimestamp("addTime"));
					buyInfoList.add(buyInfo);
				} else {
					break;
				}
			}
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buyInfoList;
	}

	private void PrepareQuery() {
		String sqlString = "select count(*) as recordCount,sum(totalPrice) as totalPrice from [buyInfoView] where 1=1";
		if(!goodNo.equals(""))
			sqlString += " and goodNo like '%" +goodNo + "%'";
		if(!goodName.equals(""))
			sqlString += " and goodName like '%" + goodName + "%'";
		if(0 != goodClassId)
			sqlString += " and goodClassId=" + goodClassId;
		if(!startDate.equals(""))
			sqlString += " and buyDate>='" + startDate + "'";
		if(!endDate.equals(""))
			sqlString += " and buyDate<='" + endDate + "'";
		DB db = new DB();
		try {
			/*得到符合条件的总的记录数*/
			ResultSet rs = db.executeQuery(sqlString);
			if(rs.next()) {
				recordCount = rs.getInt("recordCount");
				totalPrice = rs.getFloat("totalPrice");
			}
			
			db.all_close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*根据总的记录数和每页显示的记录条数计算总的页数*/
		this.totalPage = (this.recordCount + pageSize - 1) / this.pageSize;
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
}
