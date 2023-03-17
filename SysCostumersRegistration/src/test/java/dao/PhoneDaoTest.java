package dao;

import java.util.List;

import org.junit.Test;

import model.Phone;

public class PhoneDaoTest {
	
	PhoneDao dao = new PhoneDao();
	
	@Test
	public void addPhoneTest() {

		Phone phone = new Phone();
		phone.setNumber("(86) 4445-4545");
		phone.setType("Home");
		phone.setCustomerId(9L);
		
		dao.add(phone);

	}
	
	
	@Test
	public void searchByIdTest() {
		try {
			Phone phone = dao.searchById(9L);
			System.out.println(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void listByCustomerIdTest() {
		try {
			List<Phone> phones = dao.listByCustomerId(9L);
			for (Phone phone : phones) {
				System.out.println(phone);
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void listByCustomerIdSimpleTest() {
		try {
			List<Phone> phones = dao.listByCustomerId(9L);
			for (Phone phone : phones) {
				System.out.println(phone.toStringSimple());
				System.out.println("----------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void deleteTest() {
		try {
			dao.delete(11L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
