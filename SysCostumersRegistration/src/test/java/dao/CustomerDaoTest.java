package dao;

import java.util.List;

import org.junit.Test;

import model.Customer;

public class CustomerDaoTest {
	
	CustomerDao dao = new CustomerDao();
	
	@Test
	public void addTest() {
		Customer customer = new Customer();
		customer.setName("Jason Hendrix");
		customer.setEmail("jasonhendrix@gmail.com");
		dao.add(customer);
	}
	
	@Test
	public void listTest() {
		try {
			List<Customer> customers = dao.list();
			for (Customer customer : customers) {
				System.out.println(customer);
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listCustomerWithPhonesTest() {
		try {
			List<Customer> customers = dao.listCustomerWithPhones();
			for (Customer customer : customers) {
				System.out.println(customer.toStringPhones());
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void searchByNameTest() {
		try {
			List<Customer> customers = dao.searchByNameList("jason");
			for (Customer customer : customers) {
				System.out.println(customer);
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void searchByIdTest() {
		try {
			Customer customer = dao.searchById(5L);
			System.out.println(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void updateTest() {
		try {
			Customer customer = dao.searchById(5L);
			customer.setName("New Name");
			dao.update(customer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void deleteTest() {
		try {
			dao.delete(1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}