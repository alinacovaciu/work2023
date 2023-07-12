package com.ssn.practica.work.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity

@ManyToMany(cascade = { CascadeType.ALL })
@JoinTable(
    name = "Trainee_Course", 
    joinColumns = { @JoinColumn(name = "trainee_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "course_id") }
)
private List<Course> courses=new ArrayList<>();
public Trainee()
{

}
public Trainee(Long id, String name, int age) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
}
public class Trainee {
	private Long id;
	private String name;
	private int age;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
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
