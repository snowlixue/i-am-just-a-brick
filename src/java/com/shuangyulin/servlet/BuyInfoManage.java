package com.shuangyulin.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.BuyInfoDAO;
import com.shuangyulin.javabean.BuyInfo;

public class BuyInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		/*如果是添加新的进货信息*/
		if(action.equals("add")) {
			AddBuyInfo(request, response);
		} else if(action.equals("query")) {
			BuyInfoQuery(request, response);
		}
	}

	private void BuyInfoQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*取得查询的参数信息*/
		String GoodNo = request.getParameter("GoodNo");
		if(null == GoodNo) GoodNo = "";
		String method = (String)request.getParameter("method");
		if(null == method) method = "post";
		String GoodName = request.getParameter("GoodName");
		if(null == GoodName) GoodName = "";
		if(method.equals("get")) {
			GoodName = new String(GoodName.getBytes("ISO-8859-1"),"GBK"); 
		}
		int GoodClassId = 0;
		if(null != request.getParameter("GoodClassId"))
			GoodClassId = Integer.parseInt((String)request.getParameter("GoodClassId"));
		String StartDate = request.getParameter("StartDate");
		if(null == StartDate) StartDate = "";
		String EndDate = request.getParameter("EndDate");
		if(null == EndDate) EndDate = "";
		int currentPage = 1;
		if(request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		/*调用业务层查询，查询前先传入查询的参数信息*/
		BuyInfoDAO buyInfoDAO = new BuyInfoDAO();
		buyInfoDAO.setGoodNo(GoodNo);
		buyInfoDAO.setGoodName(GoodName);
		buyInfoDAO.setGoodClassId(GoodClassId);
		buyInfoDAO.setStartDate(StartDate);
		buyInfoDAO.setEndDate(EndDate);
		buyInfoDAO.setCurrentPage(currentPage);
		ArrayList<BuyInfo> buyInfoList = buyInfoDAO.QueryBuyInfo();
		request.setAttribute("buyInfoList", buyInfoList);
		request.setAttribute("GoodNo", GoodNo);
		request.setAttribute("GoodName", GoodName);
		request.setAttribute("GoodClassId", GoodClassId);
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("recordCount",buyInfoDAO.getRecordCount());
		request.setAttribute("pageSize", buyInfoDAO.getPageSize());
		request.setAttribute("totalPage",buyInfoDAO.getTotalPage());
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("TotalPrice",buyInfoDAO.getTotalPrice());
		RequestDispatcher rd = request.getRequestDispatcher("BuyInfoQuery.jsp");
		rd.forward(request, response);
	}

	private void AddBuyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*取的进货参数信息*/
		String SupplierName = request.getParameter("SupplierName"); /*供应商*/
		String GoodNo = request.getParameter("GoodNo"); /*商品编号*/
		String Price = request.getParameter("Price"); /*进货价格*/
		String Number = request.getParameter("Number"); /*进货数量*/
		String BuyDate = request.getParameter("BuyDate"); /*进货日期*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date BuyDate_Util = null;
		try {
			BuyDate_Util = dateFormat.parse(BuyDate);  //util类型
		} catch (ParseException e) {
			System.out.println("转换日期错误!");
			e.printStackTrace();
		}
		java.sql.Date BuyDate_Sql = new java.sql.Date(BuyDate_Util.getTime());//sql类型 
		BuyInfo buyInfo = new BuyInfo(); /*建立商品进货信息对象*/
		buyInfo.setGoodNo(GoodNo);
		buyInfo.setSupplierName(SupplierName);
		buyInfo.setPrice(Float.parseFloat(Price));
		buyInfo.setNumber(Integer.parseInt(Number));
		buyInfo.setTotalPrice(Float.parseFloat(Price) * Integer.parseInt(Number));
		buyInfo.setBuyDate(BuyDate_Sql);
		/*调用业务层进行进货信息的添加操作*/
		BuyInfoDAO buyInfoDAO = new BuyInfoDAO();
		if(buyInfoDAO.AddBuyInfo(buyInfo))
			request.setAttribute("result", "商品进货添加成功!");
		else
			request.setAttribute("result", buyInfoDAO.getErrMessage());
		RequestDispatcher rd = request.getRequestDispatcher("BuyInfoAdd.jsp");
		rd.forward(request, response);
	}

}
