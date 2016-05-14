package com.shuangyulin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.SellBackDAO;
import com.shuangyulin.javabean.SellBackInfo;
/*销售退货的控制层*/
public class SellBackInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equals("add")) /*添加销售退货信息*/
			AddSellBackInfo(request,response);
		else if(action.equals("query"))
			QuerySellBackInfo(request,response);
			
	}

	private void QuerySellBackInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*获取查询参数*/
		String GoodNo = request.getParameter("GoodNo");
		String SellNo = request.getParameter("SellNo");
		String StartDate = request.getParameter("StartDate");
		String EndDate = request.getParameter("EndDate");
		/*调用业务层执行顾客退货信息的查询*/
		SellBackDAO sellBackDAO = new SellBackDAO();
		ArrayList<SellBackInfo> sellBackInfoList = sellBackDAO.QuerySellBackInfo(GoodNo, SellNo, StartDate, EndDate);
		request.setAttribute("StartDate", StartDate);
		request.setAttribute("EndDate", EndDate);
		request.setAttribute("GoodNo", GoodNo);
		request.setAttribute("SellNo", SellNo);
		request.setAttribute("TotalPrice", sellBackDAO.getTotalPrice());
		request.setAttribute("sellBackInfoList",sellBackInfoList);
		RequestDispatcher rd = request.getRequestDispatcher("SellBackInfoQuery.jsp");
		rd.forward(request, response);
	}

	private void AddSellBackInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*取得参数*/
		SellBackInfo sellBackInfo = new SellBackInfo();
		sellBackInfo.setSellNo(request.getParameter("SellNo"));
		sellBackInfo.setGoodNo(request.getParameter("GoodNo"));
		sellBackInfo.setPrice(Float.parseFloat(request.getParameter("Price")));
		sellBackInfo.setNumber(Integer.parseInt(request.getParameter("Number")));
		sellBackInfo.setTotalPrice(sellBackInfo.getPrice() * sellBackInfo.getNumber());
		sellBackInfo.setSellBackReason(request.getParameter("SellBackReason"));
		String IsGood = request.getParameter("IsGood");
		boolean isGoodFlag = false;
		if(IsGood.equals("yes")) isGoodFlag = true;
		/*调用业务层执行顾客退货业务*/
		SellBackDAO sellBackDAO = new SellBackDAO();
		boolean isSuccess = sellBackDAO.AddSellBackInfoAdd(sellBackInfo, isGoodFlag);
		if(isSuccess)
			request.setAttribute("result", "顾客办理退货成功!");
		else
			request.setAttribute("result", "失败:" + sellBackDAO.getErrMessage());
		RequestDispatcher rd = request.getRequestDispatcher("SellBackInfoAdd.jsp");
		rd.forward(request, response);
		
	}

}
