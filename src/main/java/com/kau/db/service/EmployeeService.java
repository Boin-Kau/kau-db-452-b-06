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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.kau.db.entity.Dependent;
import com.kau.db.entity.Employee;
import com.kau.db.entity.EmployeeReq;
import com.oracle.wls.shaded.org.apache.xpath.operations.And;

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
			sql += " FROM EMPLOYEE AS E left join EMPLOYEE AS S on E.Super_ssn =  S.Ssn left join DEPARTMENT AS D on E.Dno = D.Dnumber";
			
			if(!field.equals("all") && field != null) {
				switch (field) {
				case "Dname":
					sql += " WHERE " + field + "='" + query+"';";
					break;
				case "Sex":
				case "Super_ssn":
					sql += " WHERE E." + field + "='" + query+"';";
					break;
				case "Salary":
					sql += " WHERE E." + field + ">" + query+";";
					break;
				case "Bdate":
					sql += " WHERE E." + field + " LIKE " + "'_____" + query+"___';";
					break;
				default:
					break;
				}

			} else {
				sql += ";";
			}
			System.out.println(sql);
			
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
			
			if(result != 0) {
				String sql2 = "DELETE FROM WORKS_ON WHERE Essn IN ("+params+")";
				st.executeUpdate(sql2);
				
				String sql3 = "DELETE FROM DEPENDENT WHERE Essn IN ("+params+")";
				st.executeUpdate(sql3);
				
				String sql4 = "UPDATE DEPARTMENT SET Mgr_ssn='vacancy' WHERE Mgr_ssn IN ("+params+")";
				st.executeUpdate(sql4);
				
				String sql5 = "UPDATE EMPLOYEE SET Super_ssn=NULL WHERE Super_ssn IN ("+params+")";
				int check = st.executeUpdate(sql5);
				
				if(check != 0) {
					String sql6 = "UPDATE EMPLOYEE SET modified = CURRENT_TIMESTAMP Super_ssn IN ("+params+")";
					st.executeUpdate(sql6);
				}
			}
			
			st.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 4. 검색된 직원을 선택하고, 수정할 항목 선택 후 수정사항을 입력하여 그 직원의 해당 항목 정보를 수정
	public int updateEmployee(int[] updateIds, String field, String query) {
		
		int result = 0; 
		
		String sql = "UPDATE EMPLOYEE SET " + field + "= ? WHERE ssn = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setString(1, query);
			st.setInt(2, updateIds[0]);
			
			result = st.executeUpdate();
			if(result == 1) {
				System.out.println("update 성공"); // update 성공시 수정날짜 갱신
	    		st = connection.prepareStatement("UPDATE EMPLOYEE SET modified = CURRENT_TIMESTAMP where ssn=?");
	    		st.setInt(1, updateIds[0]);
	    		st.executeUpdate();
			} else {
				System.out.println("update 실패");
			}
		
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
	public int insertEmployee(EmployeeReq employeeReq) {
		
		int result = 0;
		
		String sql = "INSERT INTO EMPLOYEE(Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno, created, modified) VALUES(?,?,?,?,?,?,?,?,?,?, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, employeeReq.getFname());
			st.setString(2, employeeReq.getMinit());
			st.setString(3, employeeReq.getLname());
			st.setString(4, employeeReq.getSsn());
			st.setString(5, employeeReq.getBdate());
			st.setString(6, employeeReq.getAddress());
			st.setString(7, employeeReq.getSex());
			st.setDouble(8, employeeReq.getSalary());
			st.setString(9, employeeReq.getSuper_ssn());
			st.setInt(10, employeeReq.getDno());
			
			result = st.executeUpdate();
			if(result == 1) {
				System.out.println("insert complete");
			} else {
				System.out.println("insert fail");
			}
			
			st.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 7. 직원별 가족을 모두 출력하기 
	public List<Dependent> getDependentList(String ssn) {
		List<Dependent> dependentList = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT D.Dependent_name, D.Sex, D.Bdate, D.Relationship FROM EMPLOYEE, DEPENDENT AS D WHERE Essn = Ssn AND Ssn = ?";
			PreparedStatement p = connection.prepareStatement(sql);
			
			p.setString(1, ssn);
			ResultSet r = p.executeQuery();
			
			while(r.next()) {
				String name = r.getString("D.Dependent_name");
				String sex = r.getString("D.Sex");
				Date bDate = r.getDate("D.Bdate");
				String relationship = r.getString("D.Relationship");
				
				Dependent dependent = new Dependent(
						name,
						sex,
						bDate,
						relationship
				);
				
				dependentList.add(dependent);
			}
			
			r.close();
			p.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dependentList;
	}

	// 8. 부서별로 월급 일괄 수정하기
	public int updateSalaryByDepartment(String departmentName, String departmentSalary) {
		int result = 0;
		
		String sql = "UPDATE (EMPLOYEE left join DEPARTMENT on Dnumber = Dno) SET Salary = ? WHERE Dname = ?;";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setDouble(1, Double.parseDouble(departmentSalary));
			st.setString(2, departmentName);
			
			result = st.executeUpdate();
			if(result >= 1) {
				System.out.println("update 성공"); // update 성공시 수정날짜 갱신
	    		st = connection.prepareStatement("UPDATE (EMPLOYEE left join DEPARTMENT on Dnumber = Dno) SET modified = CURRENT_TIMESTAMP where Dname = ?");
	    		st.setString(1, departmentName);
	    		st.executeUpdate();
			} else {
				System.out.println("update 실패");
			}
			
			st.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
