package net.learntechnology.empmaint.domain;

import com.tien.chicken.domain.Customer;


public class Employee extends BaseVO {

	private Integer id;
	private Integer age;
	private String firstName;
	private String lastName;
	private Customer department;

	public Employee() {
	}

	public Employee(Integer id, String firstName, String lastName, Integer age, Customer department) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Customer getDepartment() {
		return department;
	}

	public void setDepartment(Customer department) {
		this.department = department;
	}
}
