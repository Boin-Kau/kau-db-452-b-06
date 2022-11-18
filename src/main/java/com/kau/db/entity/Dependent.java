package com.kau.db.entity;

import java.util.Date;

public class Dependent {
	private String name;
	private String sex;
	private Date bDate;
	private String relationship;
	
	public Dependent() {
		
	}
	
	public Dependent(String name, String sex, Date bDate, String relationship) {
		this.name = name;
		this.sex = sex;
		this.bDate = bDate;
		this.relationship = relationship;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Override
	public String toString() {
		return "Dependent [name=" + name + ", sex=" + sex + ", bDate=" + bDate + ", relationship=" + relationship + "]";
	}
}
