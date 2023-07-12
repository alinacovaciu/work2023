package com.ssn.practica.work.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity 
public class Course {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")

public Course()
{

}
public Course( String name) {
	super();
	this.name = name;
	
}
@ManyToMany(mappedBy="courses")
private List<Trainee> trainees=new ArrayList<>();


	private String name;
	

	public Long getId() {
		return id;
	}
	public String getName()
	{
		return name;
	}
	public int getAge()
	{
		return age;
	}
	
}
}
