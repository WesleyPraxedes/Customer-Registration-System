package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection_JDBC.SingleConnection;
import model.Phone;

public class PhoneDao {

	private Connection connection;

	public PhoneDao() {
		connection = SingleConnection.getConnection();
	}

	public void add(Phone phone) {
		try {
			String sql = "insert into phone(number, type, customer_id) VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, phone.getNumber());
			statement.setString(2, phone.getType());
			statement.setLong(3, phone.getCustomerId());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public Phone searchById(Long id) throws Exception {
		String sql = "select * from phone where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		Phone phone = new Phone();

		while (resultSet.next()) {
			phone.setId(resultSet.getLong("id"));
			phone.setNumber(resultSet.getString("number"));
			phone.setType(resultSet.getString("type"));
			phone.setCustomerId(resultSet.getLong("customer_id"));
		}
		return phone;
	}

	public List<Phone> listByCustomerId(Long customerId) throws Exception {
		List<Phone> list = new ArrayList<Phone>();

		String sql = "select * from phone where customer_id = " + customerId;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			Phone phone = new Phone();
			phone.setId(resultSet.getLong("id"));
			phone.setNumber(resultSet.getString("number"));
			phone.setType(resultSet.getString("type"));
			phone.setCustomerId(resultSet.getLong("customer_id"));

			list.add(phone);
		}

		return list;
	}
	
	public void delete(Long id) {
		try {

			String sql = "delete from phone where id =" + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
