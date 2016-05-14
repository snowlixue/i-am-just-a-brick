package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shuangyulin.dao.AdminDAO;
import com.shuangyulin.dao.EmployeeDAO;
import com.shuangyulin.javabean.Admin;
import com.shuangyulin.javabean.Employee;


public class CheckLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.setContentType("text/html;charset=GBK");
		HttpSession session = request.getSession();
		/* 首先取得jsp页面传来的参数信息 */
		String txtName = request.getParameter("txtName");
		String txtPwd = request.getParameter("txtPwd");
		String identify = request.getParameter("identify");
		String code = request.getParameter("code");
		String sessionCode = (String) session.getAttribute("code");
		String errMessage = "";
		/* 验证输入信息的完整行和正确性 */
		if (txtName.equals(""))
			errMessage += "用户名不能为空!";
		if (txtPwd.equals(""))
			errMessage += "密码输入不能为空!";
		if (!code.equals(sessionCode))
			errMessage += "验证码输入不正确!";
		if (txtPwd.indexOf("'") != -1)
			errMessage += "请不要进行sql注入攻击!";
		/* 如果验证没有通过转到登陆页并提示错误信息 */
		if (!errMessage.equals("")) {
			request.setAttribute("errMessage", errMessage);
			RequestDispatcher wm = request.getRequestDispatcher("login.jsp");
			wm.forward(request, response);
			return;
		}
		/* 如果初步验证通过就需要进一步验证 */
		/* (1)如果是管理员身份登陆系统 */
		if (identify.equals("admin")) {
			Admin admin = new Admin();
			admin.setAdminUsername(txtName);
			admin.setAdminPassword(txtPwd);
			/*如果是管理员身份用户名和密码都验证成功则设置session的值然后重定向到管理首页*/
			if (AdminDAO.checkLogin(admin)) {
				session.setAttribute("adminFlag", true);
				session.setAttribute("adminUsername", txtName);
				response.sendRedirect("Admin/index.jsp");
			} else {
				errMessage += "管理员帐号或密码错误!";
				request.setAttribute("errMessage",errMessage);
				RequestDispatcher wm = request.getRequestDispatcher("login.jsp");
				wm.forward(request, response);
			}
		} else if (identify.equals("employee")) {
			Employee employee = new Employee();
			employee.setEmployeeNo(txtName);
			employee.setEmployeePassword(txtPwd);
			/*如果是员工身份用户名和密码都验证成功也设置session的值然后转到员工操作首页*/
			if(EmployeeDAO.checkLogin(employee)) {
				session.setAttribute("employeeFlag", true);
				session.setAttribute("employeeNo", txtName);
				response.sendRedirect("Employee/index.jsp");
			} else {
				errMessage += "员工帐号或密码错误!";
				request.setAttribute("errMessage",errMessage);
				RequestDispatcher wm = request.getRequestDispatcher("login.jsp");
				wm.forward(request, response);
			}
			

		}

	}
}
