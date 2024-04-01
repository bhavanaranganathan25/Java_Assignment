package com.dto;

import java.util.Objects;

public class getteacherdto {
	private int courseId;
	private String courseName;
	private String firstName;
	public getteacherdto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public getteacherdto(int courseId, String courseName, String firstName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.firstName = firstName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Override
	public String toString() {
		return "getteacherdto [courseId=" + courseId + ", courseName=" + courseName + ", firstName=" + firstName + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(courseId, courseName, firstName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		getteacherdto other = (getteacherdto) obj;
		return courseId == other.courseId && Objects.equals(courseName, other.courseName)
				&& Objects.equals(firstName, other.firstName);
	}
	
	

}
