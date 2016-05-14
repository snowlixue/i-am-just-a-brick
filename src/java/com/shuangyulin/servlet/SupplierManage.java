package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.SupplierDAO;
import com.shuangyulin.javabean.Supplier;

/*关于供应商管理的控制层*/
public class SupplierManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("add")) {
			Supplier supplier = new Supplier();
			supplier.setSupplierName(request.getParameter("SupplierName"));
			supplier.setSupplierLawyer(request.getParameter("SupplierLawyer"));
			supplier.setSupplierTelephone(request.getParameter("SupplierTelephone"));
			supplier.setSupplierAddress(request.getParameter("SupplierAddress"));
			SupplierDAO supplierDAO = new SupplierDAO();
			if(supplierDAO.AddSupplierInfo(supplier))
				request.setAttribute("result", "供应商信息添加成功!");
			else
				request.setAttribute("result", "失败:" + supplierDAO.getErrMessage());
			RequestDispatcher rd = request.getRequestDispatcher("SupplierManage.jsp");
			rd.forward(request, response);
		}
	}
	
}
