package com.shuangyulin.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.EmployeeDAO;
import com.shuangyulin.dao.GoodDAO;
import com.shuangyulin.javabean.Employee;
import com.shuangyulin.javabean.Good;

public class EmployeeInfoManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("add")) {
			AddEmployeeInfo(request, response);
		} else if(action.equals("query")) {
			QueryEmployeeInfo(request, response);
		} else if(action.equals("update")) {
			UpdateEmployeeInfo(request, response);
		}
		
	}

	private void UpdateEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*取得员工信息参数*/
		String employeeNo = request.getParameter("EmployeeNo");
		String employeeName = request.getParameter("EmployeeName");
		String employeeSex = request.getParameter("EmployeeSex");
		int employeeEducationId = Integer.parseInt(request.getParameter("EmployeeEducationId"));
		/*对生日日期进行转换*/
		String employeeBirthday = request.getParameter("EmployeeBirthday");
		java.util.Date EmployeeBirthdayDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(employeeBirthday);
			EmployeeBirthdayDate = dateFormat.parse(employeeBirthday);  //util类型
		} catch (ParseException e) {
			System.out.println("转换日期错误!");
			e.printStackTrace();
		}
		java.sql.Date employeeBirthday_SQLDATE = new java.sql.Date(EmployeeBirthdayDate.getTime());//sql类型 
		String employeePassword = request.getParameter("EmployeePassword");
		String employeeMobile = request.getParameter("EmployeeMobile");
		String employeeCard = request.getParameter("EmployeeCard");
		String employeeEmail = request.getParameter("EmployeeEmail");
		String employeeAddress = request.getParameter("EmployeeAddress");
		/*建立员工模型*/
		Employee employee = new Employee();
		employee.setEmployeeNo(employeeNo);
		employee.setEmployeeName(employeeName);
		employee.setEmployeeSex(employeeSex);
		employee.setEmployeeEducationId(employeeEducationId);
		employee.setEmployeeBirthday(employeeBirthday_SQLDATE);
		employee.setEmployeePassword(employeePassword);
		employee.setEmployeeMobile(employeeMobile);
		employee.setEmployeeCard(employeeCard);
		employee.setEmployeeEmail(employeeEmail);
		employee.setEmployeeAddress(employeeAddress);
		/*调用业务层执行员工信息的更新*/
		EmployeeDAO employeeDAO = new EmployeeDAO();
		int result = employeeDAO.UpdateEmployeeInfo(employee);
		if(result > 0) {
			request.setAttribute("result", "员工信息更新成功!");
		} else {
			request.setAttribute("result", "员工信息更新失败!");
		}
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeInfoUpdate.jsp?EmployeeNo=" + employeeNo);
		rd.forward(request, response);
	}

	private void QueryEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*首先取得查询的关键字*/
		String EmployeeNo = request.getParameter("EmployeeNo");
		if(null == EmployeeNo) EmployeeNo = "";
		String EmployeeName = request.getParameter("EmployeeName");
		String method = request.getParameter("method");
		if(null == method) method = "post";
		if(method.equals("get"))
			EmployeeName = new String(EmployeeName.getBytes("ISO-8859-1"),"GBK"); 
		if(null == EmployeeName) EmployeeName = "";
		int currentPage = 1;
		if(request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		/*调用业务层对符合条件的员工信息进行查询*/
		EmployeeDAO employeeDAO = new EmployeeDAO();
		/*设置查询条件*/
		employeeDAO.setEmployeeNo(EmployeeNo);
		employeeDAO.setEmployeeName(EmployeeName);
		employeeDAO.setCurrentPage(currentPage);
		ArrayList<Employee> employeeList = employeeDAO.QueryEmployeeInfo();
		request.setAttribute("employeeList", employeeList);
		request.setAttribute("EmployeeNo", EmployeeNo);
		request.setAttribute("EmployeeName",EmployeeName);
		request.setAttribute("recordCount",employeeDAO.getRecordCount());
		request.setAttribute("pageSize", employeeDAO.getPageSize());
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("totalPage",employeeDAO.getTotalPage());
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeManage.jsp");
		rd.forward(request, response);
	}

	private void AddEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*首先从视图层取得该员工各个属性的信息并转化类型*/
		String employeeNo = request.getParameter("employeeNo");
		String employeeName = request.getParameter("employeeName");
		String employeePassword = request.getParameter("employeePassword");
		String employeeSex = request.getParameter("employeeSex");
		int employeeEducationId = Integer.parseInt(request.getParameter("educationId"));
		String employeeHomeTel = request.getParameter("employeeHomeTel");
		String employeeMobile = request.getParameter("employeeMobile");
		String employeeCard = request.getParameter("employeeCard");
		String employeeEmail = request.getParameter("employeeEmail");
		String employeeAddress = request.getParameter("employeeAddress");
		String employeeBirthday = request.getParameter("employeeBirthday");
		java.util.Date employeeBirthdayDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println(employeeBirthday);
			employeeBirthdayDate = dateFormat.parse(employeeBirthday);  //util类型
		} catch (ParseException e) {
			System.out.println("转换日期错误!");
			e.printStackTrace();
		}
		java.sql.Date employeeBirthdayTime = new java.sql.Date(employeeBirthdayDate.getTime());//sql类型 

		/*然后将员工信息保存在员工信息bean中*/
		Employee employee = new Employee();
		employee.setEmployeeNo(employeeNo);
		employee.setEmployeeName(employeeName);
		employee.setEmployeePassword(employeePassword);
		employee.setEmployeeSex(employeeSex);
		employee.setEmployeeBirthday(employeeBirthdayTime);
		employee.setEmployeeEducationId(employeeEducationId);
		employee.setEmployeeHomeTel(employeeHomeTel);
		employee.setEmployeeMobile(employeeMobile);
		employee.setEmployeeCard(employeeCard);
		employee.setEmployeeEmail(employeeEmail);
		employee.setEmployeeAddress(employeeAddress);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		if(employeeDAO.AddEmployeeInfo(employee)) {
			request.setAttribute("result", "员工信息添加成功!");
		} else {
			request.setAttribute("result", employeeDAO.getErrMessage());
		}
		RequestDispatcher rd = request.getRequestDispatcher("EmployeeInfoAdd.jsp");
		rd.forward(request, response);
	}

}
