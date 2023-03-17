package model;

import java.util.List;

public class Customer {
	
	private Long id;
	private String name;
	private String email;
	
	//List<Customer> list = new ArrayList<Customer>();
	List<Phone> phones;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	public String toStringPhones() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phones=" + phones + "]";
	}
	
	
	
}