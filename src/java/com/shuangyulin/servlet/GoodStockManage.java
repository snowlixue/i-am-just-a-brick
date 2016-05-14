package com.shuangyulin.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.GoodStockDAO;
import com.shuangyulin.javabean.GoodStock;
/*商品库存管理的控制层*/
public class GoodStockManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("query")) {
			/*获取查询参数*/
			String GoodNo = request.getParameter("GoodNo");
			String GoodName = request.getParameter("GoodName");
			/*注入参数到业务处理层*/
			GoodStockDAO goodStockDAO = new GoodStockDAO();
			goodStockDAO.setGoodNo(GoodNo);
			goodStockDAO.setGoodName(GoodName);
			/*调用业务层的查询功能获取查询结果*/
			ArrayList<GoodStock> goodStockList = goodStockDAO.QueryGoodStockInfo();
			request.setAttribute("goodStockList",goodStockList);
			request.setAttribute("GoodNo",GoodNo);
			request.setAttribute("GoodName",GoodName);
			RequestDispatcher rd = request.getRequestDispatcher("GoodStockQuery.jsp");
			rd.forward(request, response);
		} else if(action.equals("warning")) {
			GoodStockDAO goodStockDAO = new GoodStockDAO();
			ArrayList<GoodStock> goodStockList = goodStockDAO.QueryGoodStockWarningInfo();
			request.setAttribute("goodStockList",goodStockList);
			RequestDispatcher rd = request.getRequestDispatcher("GoodStockWarning.jsp");
			rd.forward(request, response);
		}
	}

}
