<%@page import="com.kau.db.entity.Dependent"%>
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
<script type="text/javascript">
	
	function checkEmployee(element) {
		
		let names = [];

		const checkboxList = document.getElementsByName('select-id');
		for(let i = 0; i < checkboxList.length; i++) {
			console.log(i, '번 째:', checkboxList[i] );
			console.log(i, '번 째 체크상태 :', checkboxList[i].checked );
			if(checkboxList[i].checked) {
				names.push(checkboxList[i].value);
			}
		}

		const who = document.getElementById('who');
		who.innerHTML = names;
	}
	
	function changeSearchField(target) {
		const parent = document.getElementById("search-query-input");
		if(parent.hasChildNodes() == true) parent.removeChild(document.getElementById("search-query-child"));
		
		switch(target.value) {
			case "Dname":
				parent.innerHTML = `
					<select id="search-query-child" name="search-query">
						<option value="Headquarters">Headquarters</option>
					    <option value="Administration">Administration</option>
					    <option value="Research">Research</option>
					</select>
				`;
				break;
			case "Sex":
				parent.innerHTML = `
					<select id="search-query-child" name="search-query">
						<option value="M">M</option>
					    <option value="F">F</option>
					</select>
				`;
				break;
			case "Salary":
				parent.innerHTML = `
					<input type="text" id="search-query-child" value="" name="search-query">
				`;
				break;
			case "Bdate":
				parent.innerHTML = `
					<select id="search-query-child" name="search-query">
						<option value="01">01</option>
					    <option value="02">02</option>
					    <option value="03">03</option>
					    <option value="04">04</option>
					    <option value="05">05</option>
					    <option value="06">06</option>
					    <option value="07">07</option>
					    <option value="08">08</option>
					    <option value="09">09</option>
					    <option value="10">10</option>
					    <option value="11">11</option>
					    <option value="12">12</option>
					</select>
				`;
				break;
			case "Super_ssn":
				parent.innerHTML = `
					<input type="text" id="search-query-child" value="" name="search-query">
				`;
				break;
			default:
				break;
		} 
	}
</script>
</head>
<body>
	<form action="employee" method="get" class="border-black p-8">
		<div class="search-list-container mb-8">
			<h4 class="search-list-title">검색 범위</h4>
			
			<select onchange="changeSearchField(this)" class="mr-12" name="search-field">
			    <option value="all">전체</option>
			    <option value="Dname">부서</option>
			    <option value="Sex">성별</option>
			    <option value="Salary">연봉</option>
			    <option value="Bdate">생일</option>
			    <option value="Super_ssn">부하직원</option>
			</select>
			
			<span id="search-query-input"></span>
			
		</div>
		<div class="search-list-container">
			<h4 class="search-list-title">검색 항목</h4>
		
			<label for="checkboxName">Name</label>
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isName" id="checkboxName"> 
			
			<label for="checkboxSsn">Ssn</label>
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isSsn" id="checkboxSsn"> 
			
			<label for="checkboxBdate">Bdate</label>
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isBdate" id="checkboxBdate"> 
			
			<label for="checkboxAddress">Address</label>
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isAddress" id="checkboxAddress"> 
			
			<label for="checkboxSex">Sex</label>
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isSex" id="checkboxSex"> 
			
			<label for="checkboxSalary">Salary</label>
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isSalary" id="checkboxSalary"> 
			
			<label for="checkboxSupervisor">Supervisor</label>	
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isSupervisor" id="checkboxSupervisor"> 
			
			<label for="checkboxDepartment">Department</label>
			<input class="mr-12 cb" type="checkbox" checked="checked" name="isDepartment" id="checkboxDepartment"> 
			<input type="submit" name="cmd" value="search-employee"> 
		</div>
		
	</form>
	<div>
		<div class="border-black p-8">
			<strong>검색된 인원 수: </strong>
			<span>${employeeList.size()}명</span>
		</div>
		<form action="employee" method="post">
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
				if(employeeList != null) {
				for(Employee e : employeeList){
					pageContext.setAttribute("e", e);	
				%>
					<tr>
						<td> <input type="checkbox" name="select-id" onclick="checkEmployee(this)" value="${e.ssn}"> </td>
						<td>${e.name}</td>
						<td>${e.ssn}</td>
						<td>${e.bDate}</td>
						<td>${e.address}</td>
						<td>${e.sex}</td>
						<td>${e.salary}</td>
						<td>${e.supervisor}</td>
						<td>${e.dName}</td>
					</tr>
				<% }}%>
				</tbody>
			</table>
			<div class="border-black p-8">
				<h3 class="py-4">수정 및 삭제하기</h3>
				<div id="delete-container" class="py-4">
					<span>
						<strong>선택한 직원: </strong>
						<span id="who"></span>
					</span>
					
					<input type="submit" name="cmd" value="delete" >
				</div>
				<div id="update-container">
					<label class="mr-12" for="updateType">수정:</label>
					<select class="mr-12" id="updateType" name="update-field">
					    <option value="address">Address</option>
					    <option value="sex">Sex</option>
					    <option value="salary">Salary</option>
					</select>
					<input class="mr-12" type="text" value="" name="update-query">
					<input type="submit" name="cmd" value="update">
				</div>
				
			</div>
		</form>
	</div>
	<div class="border-black p-8">
		<form action="employee" method="post">
			<h3 class="mb-8">새로운 직원 정보 추가</h3>
			
			<div class="mb-8">
				<label class="label-width" for="newFName">First Name</label>
				<input type="text" id="newFName" name="fname" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newMinit">Middle Initial</label>
				<input type="text" id="newMinit" name="minit" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newLName">Last Name</label>
				<input type="text" id="newLName" name="lname" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newSsn">Ssn</label>
				<input type="text" id="newSsn" name="ssn" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newBdate">Birthdate</label>
				<input type="date" id="newBdate" name="bdate" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newAddress">Address</label>
				<input type="text" id="newAddress" name="address" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newSex">Sex</label>
				<select id="newSex" name="sex">
				    <option value="M">M</option>
				    <option value="F">F</option>
				</select>
			</div>
			
			<div class="mb-8"> 
				<label class="label-width" for="newSalary">Salary</label>
				<input type="number" id="newSalary" name="salary" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newSuperSsn">Super_ssn</label>
				<input type="text" id="newSuperSsn" name="super_ssn" value="">
			</div>
			
			<div class="mb-8">
				<label class="label-width" for="newDno">Dno</label>
				<input type="number" id="newDno" name="dno" value="">
			</div>
			
			<input type="submit" name="cmd" value="insert">
			
		</form>
	</div>
	<div class="border-black p-8">
		<form action="employee" method="get" class="mb-8">
			<h3 class="mb-8">직원별 가족 검색</h3>
			
			<label for="Ssn-for-dependent">Ssn</label>
			<input type="text" id="Ssn-for-dependent" name="Ssn" value="">
			
			<input type="submit" name="cmd" value="search-dependent"> 
		</form>
		<table border="1" width="1000">
			<thead align="center">
				<tr>
					<th> Dependent Name </th>
					<th> Sex </th>
					<th> BDATE </th>
					<th> Relationship </th>
				</tr>
			</thead>
			<tbody align="center">
			<% 
			List<Dependent> dependentList = (List<Dependent>) request.getAttribute("dependentList");
			if(dependentList != null) { 
			for(Dependent d : dependentList){
				pageContext.setAttribute("d", d);	
			%>
				<tr>
					<td>${d.name}</td>
					<td>${d.sex}</td>
					<td>${d.bDate}</td>
					<td>${d.relationship}</td>
				</tr>
			<% }}%>
			</tbody>
		</table>
	</div>
</body>
</html>