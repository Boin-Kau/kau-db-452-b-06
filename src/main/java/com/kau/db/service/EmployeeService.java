package com.kau.db.service;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.FileDataSource;
import javax.jws.soap.SOAPBinding.Style;

import com.kau.db.entity.Employee;

public class EmployeeService {
	String url = "jdbc:mysql://umc-3rd-server-database.cv5p23dkqa5m.ap-northeast-2.rds.amazonaws.com:3306/db_term_project?serverTimezone=UTC";
	String user = "admin";
	String password = "umc3rdserver";
	
	// 1. EMPLOYEE 테이블의 attribute들을 모두 출력하기	
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			
			String query = "SELECT E.Fname, E.Minit, E.Lname, E.Ssn, E.Bdate, E.Address, E.Sex, E.Salary, S.Fname, S.Minit, S.Lname, D.Dname"
					+ " FROM EMPLOYEE AS E left join EMPLOYEE AS S on E.Super_ssn =  S.Ssn"
					+ " left join DEPARTMENT AS D on E.Dno = D.Dnumber;";
			PreparedStatement p = connection.prepareStatement(query);
			
			ResultSet r = p.executeQuery();
			
			while(r.next()) {
				String fName = r.getString("E.Fname");
				String minit = r.getString("E.Minit");
				String lName = r.getString("E.Lname");
				String ssn = r.getString("E.Ssn");
				Date bDate = r.getDate("E.Bdate");
				String address = r.getString("E.Address");
				String sex = r.getString("E.Sex");
				double salary = r.getDouble("E.Salary");
				String superFName = r.getString("S.Fname");
				String superMinit = r.getString("S.Minit");
				String superLName = r.getString("S.Lname");
				String dName = r.getString("D.Dname");
				
				Employee employee = new Employee(
						fName + " " + minit + " " + lName,
						ssn,
						bDate,
						address,
						sex,
						salary,
						superFName + " " + superMinit + " " + superLName,
						dName
				);
				
				employeeList.add(employee);
			}
			
			r.close();
			p.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employeeList;
	}

	// 2. 검색 범위, 검색 항목 필터를 적용하여 해당되는 직원의 정보를 검색해서 출력하기
	public List<Employee> getEmployeeList(
			String field, 
			String query, 
			String isName, 
			String isSsn,
			String isBdate,
			String isAddress,
			String isSex,
			String isSalary,
			String isSupervisor,
			String isDepartment) {
		
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT CONCAT(E.Fname,' ', E.Minit,' ', E.Lname,' ') AS Name, E.Ssn, E.Bdate, E.Address, E.Sex, E.Salary, CONCAT(S.Fname,' ', S.Minit,' ', S.Lname,' ') AS Supervisor, Dname";
			sql += " FROM EMPLOYEE AS E left join EMPLOYEE AS S on E.Super_ssn =  S.Ssn left join DEPARTMENT AS D on E.Dno = D.Dnumber;";
//			if(field != "all" && query.length() > 0) {
//				
//				
////				if(field == "Department") {
////					sql += "WHERE"
////				}
////				sql += " WHERE E.";
////				sql += field; 
////				sql += "=";
////				sql += query;
//			}
			
			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet r = p.executeQuery();
			
			while(r.next()) {
				String name = null;
				String ssn = null;
				Date bDate= null;
				String address = null;
				String sex = null;
				double salary = 0.0f;
				String supervisor = null;
				String dName = null;
				
				if(isName != null) name = r.getString("Name");
				if(isSsn != null) ssn = r.getString("E.Ssn");
				if(isBdate != null) bDate = r.getDate("E.Bdate");
				if(isAddress != null) address = r.getString("E.Address");
				if(isSex != null) sex = r.getString("E.Sex");
				if(isSalary != null) salary = r.getDouble("E.Salary");
				if(isSupervisor != null) supervisor = r.getString("Supervisor");
				if(isDepartment != null) dName = r.getString("Dname");
			
				Employee employee = new Employee(
						name,
						ssn,
						bDate,
						address,
						sex,
						salary,
						supervisor,
						dName
				);
				
				System.out.println(employee);
				employeeList.add(employee);
			}
			
			r.close();
			p.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return employeeList;
	}



	// 3. 검색된 직원을 선택하고 DB에서 삭제
	public int deleteEmployee(int[] ids) {
		
		int result = 0; // 삭제한 Employee 데이터 개수
		
		String params = "";
		
		for(int i = 0; i < ids.length; i++) {
			params += ids[i];
			if(i < ids.length-1) {
				params += ",";
			}
		}
		
		String sql = "DELETE FROM EMPLOYEE WHERE Ssn IN ("+params+")";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement st = connection.createStatement();
			
			result = st.executeUpdate(sql);
			
			st.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	// 5. 새로운 직원의 정보를 GUI 에서 직접 추가

}