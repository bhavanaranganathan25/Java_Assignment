package com.model;

import java.util.Objects;

public class Course {
	private int id;
	private String name;
	private int credits;
	private int teacherId;
	
	
	public Course() {
		}


	public Course(int id, String name, int credits, int teacherId) {
		super();
		this.id = id;
		this.name = name;
		this.credits = credits;
		this.teacherId = teacherId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}


	public int getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", credits=" + credits + ", teacherId=" + teacherId + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(credits, id, name, teacherId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && id == other.id && Objects.equals(name, other.name)
				&& teacherId == other.teacherId;
	}
	
	
}
