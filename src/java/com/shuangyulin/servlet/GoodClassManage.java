package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shuangyulin.javabean.GoodClass;
import com.shuangyulin.dao.GoodClassDAO;

public class GoodClassManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* 首先取得jsp页面传来的操作命令和商品类别信息参数信息 */
		String action = request.getParameter("action");
		/*如果是向系统中添加新的商品信息*/
		if(action.equals("add")) {
			String goodClassName = request.getParameter("goodClassName");
			GoodClass goodClass = new GoodClass();
			goodClass.setGoodClassName(goodClassName);
			GoodClassDAO goodClassDAO = new GoodClassDAO();
			/*调用业务层执行商品类别信息的添加操作,成功返回true*/
			boolean result = goodClassDAO.AddGoodClass(goodClass);
			if(result == true) {
				request.setAttribute("result", "商品类别信息添加成功!");
			} else {
				request.setAttribute("result",goodClassDAO.getErrMessage());
			}
			RequestDispatcher rd = request.getRequestDispatcher("GoodClassManage.jsp");
		    rd.forward(request, response);
		} else if(action.equals("del")) {
			/*如果是删除商品类别，首先取得商品类别编号*/
			int goodClassId  = Integer.parseInt(request.getParameter("goodClassId"));
			GoodClassDAO goodClassDAO = new GoodClassDAO();
			/*调用业务层执行商品类别的删除，成功返回true*/
			boolean result = goodClassDAO.DeleteGoodClassById(goodClassId);
			if(true == result)
				request.setAttribute("result","商品类别删除成功!");
			else
				request.setAttribute("result",goodClassDAO.getErrMessage());
			RequestDispatcher rd = request.getRequestDispatcher("GoodClassManage.jsp");
			rd.forward(request, response);
			
		}
	}

}
