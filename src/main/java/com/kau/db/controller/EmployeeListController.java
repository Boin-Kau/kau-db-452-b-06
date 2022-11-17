package com.kau.db.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.kau.db.entity.Employee;
import com.kau.db.entity.EmployeeReq;
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// insert, update, delete mode 
		String cmd = request.getParameter("cmd");
		// insert data
		String fname = request.getParameter("fname");
		String minit = request.getParameter("minit");
		String lname = request.getParameter("lname");
		String ssn = request.getParameter("ssn");
		String bdate = request.getParameter("bdate");
		String address = request.getParameter("address");
		String sex = request.getParameter("sex");
		String salary = request.getParameter("salary");
		String super_ssn = request.getParameter("super_ssn");
		String dno = request.getParameter("dno");
		
		EmployeeReq employeeReq = new EmployeeReq();
		employeeReq.setFname(fname);
		employeeReq.setMinit(minit);
		employeeReq.setLname(lname);
		employeeReq.setSsn(ssn);
		employeeReq.setBdate(bdate);
		employeeReq.setAddress(address);
		employeeReq.setSex(sex);
		employeeReq.setSalary(Double.parseDouble(salary));
		employeeReq.setSuper_ssn(super_ssn);
		employeeReq.setDno(Integer.parseInt(dno));
		
		// update, delete ssns
		String[] updateIds = request.getParameterValues("update-id");
		
		EmployeeService service = new EmployeeService();
		
		switch(cmd) {
		case "insert":
			int insertNum = service.insertEmployee(employeeReq);
			break;
			
		case "delete": 
			int [] ids = new int[updateIds.length];
			for(int i = 0; i < updateIds.length; i++) {
				ids[i] = Integer.parseInt(updateIds[i]);
			}
			int deleteNum = service.deleteEmployee(ids);
			break;
		}
		
		response.sendRedirect("employee");
	
	}
}