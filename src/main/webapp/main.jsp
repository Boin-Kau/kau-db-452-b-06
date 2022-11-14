<%@page import="com.kau.db.entity.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>

	<form id="checkbox-list-container">
		<h4 id="checkbox-list-title">검색 항목</h4>
	
		<label for="checkboxName">Name</label>
		<input type="checkbox" checked="checked" name="isName" id="checkboxName"> 
		
		<label for="checkboxSsn">Ssn</label>
		<input type="checkbox" checked="checked" name="isSsn" id="checkboxSsn"> 
		
		<label for="checkboxBdate">Bdate</label>
		<input type="checkbox" checked="checked" name="isBdate" id="checkboxBdate"> 
		
		<label for="checkboxAddress">Address</label>
		<input type="checkbox" checked="checked" name="isAddress" id="checkboxAddress"> 
		
		<label for="checkboxSex">Sex</label>
		<input type="checkbox" checked="checked" name="isSex" id="checkboxSex"> 
		
		<label for="checkboxSalary">Salary</label>
		<input type="checkbox" checked="checked" name="isSalary" id="checkboxSalary"> 
		
		<label for="checkboxSupervisor">Supervisor</label>	
		<input type="checkbox" checked="checked" name="isSupervisor" id="checkboxSupervisor"> 
		
		<label for="checkboxDepartment">Department</label>
		<input type="checkbox" checked="checked" name="isDepartment" id="checkboxDepartment"> 
		<input type="submit" name="method" value="search"> 
	</form>
	<div>
		<table border="1" width="1000">
			<thead align="center">
				<tr>
					<th> 선택 </th>
					<th> Name </th>
					<th> Ssn </th>
					<th> BDATE </th>
					<th> Address </th>
					<th> sex </th>
					<th> salary </th>
					<th> supervisor </th>
					<th> Department </th>
				</tr>
			</thead>
			<tbody align="center">
			
			<% 
			List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
			for(Employee e : employeeList){
				pageContext.setAttribute("e", e);	
			%>
				<tr>
					<td> <input type="checkbox" name="select" onClick="check(this)" value="${e.ssn}"> </td>
					<td>${e.name}</td>
					<td>${e.ssn}</td>
					<td>${e.bDate}</td>
					<td>${e.address}</td>
					<td>${e.sex}</td>
					<td>${e.salary}</td>
					<td>${e.supervisor}</td>
					<td>${e.dName}</td>
				</tr>
			
			
			<% }%>
			</tbody>
		</table>
	
	</div>
</body>
</html>