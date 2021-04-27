package io.javabrains.springbootstarter.topic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import javax.validation.constraints.Size;


@Entity
@Table(name="emp1")
public class Employee {
	
	@Id
	private Integer id;
	
	@NotBlank(message = "Name must not be blank!")
	@Size(min = 3, max = 15)
	private String name;
	
	@NotBlank(message = "Gender must not be blank!")
	@Size(min = 4, max = 6)
	private String gender;
	
	public Employee() {
		
	}
	public Employee(Integer id, String name, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	
}
