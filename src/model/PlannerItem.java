package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="planner")
public class PlannerItem {

	// attributes
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id; 
	@Column(name="COURSE")
	private String course;
	@Column(name="ASSIGNMENT")
	private String assignment;
	
	// constructors
	public PlannerItem() {
		// default constructor
		super();
	}
	
	public PlannerItem(String course, String assignment) {
		super();
		this.course = course;
		this.assignment = assignment;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	
	// methods
	public String returnPlannerDetails() {
		return this.course + ": " + this.assignment;
	}
	
}
