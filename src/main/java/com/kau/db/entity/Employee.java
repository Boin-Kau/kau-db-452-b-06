package com.kau.db.entity;

import java.util.Date;

public class Employee {
	private String name;
	private String ssn;
	private Date bDate;
	private String address;
	private String sex;
	private double salary;
	private String supervisor;
	private String dName;
	
	public Employee() {
		
	} 
	
	public Employee(String name, String ssn, Date bDate, String address, String sex, double salary, String supervisor,
			String dName) {
		this.name = name;
		this.ssn = ssn;
		this.bDate = bDate;
		this.address = address;
		this.sex = sex;
		this.salary = salary;
		this.supervisor = supervisor;
		this.dName = dName;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", ssn=" + ssn + ", bDate=" + bDate + ", address=" + address + ", sex=" + sex
				+ ", salary=" + salary + ", supervisor=" + supervisor + ", dName=" + dName + "]";
	}
	
}
