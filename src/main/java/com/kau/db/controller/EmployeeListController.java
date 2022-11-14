package com.kau.db.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kau.db.entity.Employee;
import com.kau.db.service.EmployeeService;



@WebServlet("/employee")
public class EmployeeListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String field_ = request.getParameter("field");
		String query_ = request.getParameter("query");
		String isName_ = request.getParameter("isName");
		String isSsn_ = request.getParameter("isSsn");
		String isBdate_ = request.getParameter("isBdate");
		String isAddress_ = request.getParameter("isAddress");
		String isSex_ = request.getParameter("isSex");
		String isSalary_ = request.getParameter("isSalary");
		String isSupervisor_ = request.getParameter("isSupervisor");
		String isDepartment_ = request.getParameter("isDepartment");
		
//		String field = "all";
//		if(field_ != null) {
//			field = field_;
//		}
//		
//		String query = "";
//		if(query_ != null) {
//			query = query_;
//		}
//		
//		String isName = "off";
//		if(isName_ != null) {
//			isName = isName_;
//		}
//		
//		String isSsn = "off";
//		if(isSsn_ != null) {
//			isSsn = isSsn_;
//		}
//		
//		String isBdate = "off";
//		if(isBdate_ != null) {
//			isBdate = isBdate_;
//		}
//		
//		String isAddress = "off";
//		if(isAddress_ != null) {
//			isAddress = isAddress_;
//		}
//		
//		String isSex = "off";
//		if(isSex_ != null) {
//			isSex = isSex_;
//		}
//		
//		String isSalary = "off";
//		if(isSalary_ != null) {
//			isSalary = isSalary_;
//		}
//		
//		String isSupervisor = "off";
//		if(isSupervisor_ != null) {
//			isSupervisor = isSupervisor_;
//		}
//		
//		String isDepartment = "off";
//		if(isDepartment_ != null) {
//			isDepartment = isDepartment_;
//		}
		
		EmployeeService service = new EmployeeService();
		List<Employee> employeeList;
		if(field_ == null && query_ == null && isName_ == null && isSsn_ == null && isBdate_ == null && isAddress_ == null && isSex_ == null && isSalary_ == null && isSupervisor_ == null && isDepartment_ == null){
			employeeList = service.getEmployeeList();
		} else {
			employeeList = service.getEmployeeList(field_, query_, isName_, isSsn_, isBdate_, isAddress_, isSex_, isSalary_, isSupervisor_, isDepartment_);
		}
		
		request.setAttribute("employeeList", employeeList);
		
		// forward
		request.getRequestDispatcher("/main.jsp").forward(request, response);
		
		// When you use http protocol, use below.
//		String employeeListString = new Gson().toJson(employeeList);
//		
//		PrintWriter out = response.getWriter();
//		out.print(employeeListString);
//        out.flush();  
	}
}