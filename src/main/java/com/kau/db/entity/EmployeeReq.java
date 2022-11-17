package com.kau.db.entity;

import java.sql.Date;

public class EmployeeReq {
	private String fname;
	private String minit;
	private String lname;
	private String ssn;
	private String bdate;
	private String address;
	private String sex; 
	private double salary;
	private String super_ssn; 
	private int dno;
	
	public EmployeeReq() {
		
	}
	
	public EmployeeReq(String fname, String minit, String lname, String ssn, String bdate, String address, String sex,
			double salary, String super_ssn, int dno) {
		this.fname = fname;
		this.minit = minit;
		this.lname = lname;
		this.ssn = ssn;
		this.bdate = bdate;
		this.address = address;
		this.sex = sex;
		this.salary = salary;
		this.super_ssn = super_ssn;
		this.dno = dno;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMinit() {
		return minit;
	}
	public void setMinit(String minit) {
		this.minit = minit;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
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
	public String getSuper_ssn() {
		return super_ssn;
	}
	public void setSuper_ssn(String super_ssn) {
		this.super_ssn = super_ssn;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}

	@Override
	public String toString() {
		return "EmployeeReq [fname=" + fname + ", minit=" + minit + ", lname=" + lname + ", ssn=" + ssn + ", bdate="
				+ bdate + ", address=" + address + ", sex=" + sex + ", salary=" + salary + ", super_ssn=" + super_ssn
				+ ", dno=" + dno + "]";
	}
	
	
	
	
}


