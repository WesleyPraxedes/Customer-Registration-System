package model;

public class Phone {
	
	private Long id;
	private String number;
	private String type;
	private Long customerId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	@Override
	public String toString() {
		//return "Phone [id=" + id + ", number=" + number + ", type=" + type + ", customerId=" + customerId + "]";
		return "[number=" + number + ", type=" + type + "]";
	}
	
	public String toStringSimple() {
		return "Phone [id=" + id + ", number=" + number + ", type=" + type + ", customerId=" + customerId + "]";
		//return "[number=" + number + ", type=" + type + "]";
	}
	
	
}
