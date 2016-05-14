package com.shuangyulin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.GoodDAO;
import com.shuangyulin.javabean.Good;

public class GetGoodInfo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String goodNo = request.getParameter("goodNo");
		GoodDAO goodDAO = new GoodDAO();
		Good good = goodDAO.GetGoodInfoByGoodNo(goodNo);
		response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control","no-cache");
		PrintWriter pw = response.getWriter();
		pw.println("<GoodInfo>");
		pw.println("<GoodNo>" + good.getGoodNo() + "</GoodNo>");
		pw.println("<GoodName>" + good.getGoodName() + "</GoodName>");
		pw.println("<GoodModel>" + good.getGoodModel() + "</GoodModel>");
		pw.println("<GoodSpecs>" + good.getGoodSpecs() + "</GoodSpecs>");
		pw.println("<GoodPlace>" + good.getGoodPlace() + "</GoodPlace>");
		pw.println("</GoodInfo>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
