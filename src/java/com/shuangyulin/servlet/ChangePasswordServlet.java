package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/*修改密码的控制层*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.javabean.Admin;
import com.shuangyulin.javabean.Employee;
import com.shuangyulin.dao.*;

public class ChangePasswordServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identify = request.getParameter("identify");
		if(identify.equals("admin")) {
			Admin admin = new Admin();
			admin.setAdminUsername(request.getParameter("adminUsername"));
			admin.setAdminPassword(request.getParameter("NewPassword"));
			if(AdminDAO.ChangePassword(admin))
				request.setAttribute("result", "密码修改成功!");
			else
				request.setAttribute("result", "密码修改失败!");
			RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
			rd.forward(request, response);
		} else if(identify.equals("employee")) {
			Employee employee = new Employee();
			employee.setEmployeeNo(request.getParameter("employeeNo"));
			employee.setEmployeePassword(request.getParameter("NewPassword"));
			if(EmployeeDAO.ChangePassword(employee))
				request.setAttribute("result", "密码修改成功!");
			else
				request.setAttribute("result", "密码修改失败!");
			RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
			rd.forward(request, response);
		}
	}

}
