package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection_JDBC.SingleConnection;
import model.Customer;

public class CustomerDao{

	private Connection connection;

	public CustomerDao() {
		connection = SingleConnection.getConnection();
	}
	
	// Add customer
	public void add(Customer customer) {
		try {
			String sql = "insert into customer(name, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, customer.getName()); 
			insert.setString(2, customer.getEmail());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	// Update customer
	public void update(Customer customer) {
		try {
			String sql = "update customer set name = ?, email = ? where id = " + customer.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getEmail());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}	
	
	// Search customer by id
	public Customer searchById(Long id) throws Exception {
		Customer customer = new Customer();
		String sql = "select * from customer where id = " + id; 

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) { 
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setEmail(resultSet.getString("email"));
		}
		return customer;
	}
	
	// Search customer by name
	public List<Customer> searchByNameList(String partName) throws Exception {

		List<Customer> list = new ArrayList<Customer>();

		String sql = "SELECT * FROM customer WHERE name ~* '"+partName+"' order by name"; 

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setEmail(resultSet.getString("email"));

			list.add(customer);
		}
		return list;
	}
	
	// Search customer by email
	public boolean checkExistEmail(String email) throws Exception {
		
		String sql = "SELECT * FROM customer WHERE email like '%"+email+"%'"; 
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
            return true;
		}
		return false;
	}
	
    // Display customer
	public List<Customer> list() throws Exception {
		List<Customer> list = new ArrayList<Customer>();

		String sql = "select * from customer";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setEmail(resultSet.getString("email"));

			list.add(customer);
		}
		return list;
	}
	
	// Display customer with phones
	public List<Customer> listCustomerWithPhones() throws Exception {
		List<Customer> list = new ArrayList<Customer>();

		String sql = "select * from customer";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Customer customer = new Customer();
			customer.setId(resultSet.getLong("id"));
			customer.setName(resultSet.getString("name"));
			customer.setEmail(resultSet.getString("email"));
			PhoneDao dao = new PhoneDao();
			customer.setPhones(dao.listByCustomerId(customer.getId()));

			list.add(customer);
		}

		return list;
	}
	
	// Delete customer
	public void delete(Long id) {
		try {
			String sqlphone = "delete from phone where customer_id = " + id;
			String sql = "delete from customer where id = " + id;
			
			PreparedStatement statement = connection.prepareStatement(sqlphone);
			statement.execute();
			connection.commit();
			
			statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
				System.out.println("Ocorreu um erro() "+e);
		}
	}
}