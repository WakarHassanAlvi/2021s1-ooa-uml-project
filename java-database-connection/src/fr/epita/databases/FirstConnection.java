package fr.epita.databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.epita.northwind.datamodel.Shipper;

public class FirstConnection {

	public static void main(String[] args) throws SQLException {
		Connection connection =
				DriverManager.getConnection("jdbc:postgresql://localhost:5432/northwind", "postgres", "postgres");

		List<Shipper> shippers = listShippers(connection);
		System.out.println(shippers);

		int size = shippers.size();
		System.out.println(size);


//		Shipper shipper = new Shipper(99, "testCompany", "33123456789");
//		createShipper(connection, shipper);
		connection.close();
	}

	private static void createShipper(Connection connection, Shipper shipper) throws SQLException {
		if (connection.isClosed()){
			return;
		}
		PreparedStatement preparedStatement = connection.prepareStatement("insert into shippers(shipper_id, company_name, phone) values (?,?,?)");
		preparedStatement.setLong(1,shipper.getId());
		preparedStatement.setString(2, shipper.getCompanyName());
		preparedStatement.setString(3, shipper.getPhoneNumber());
		preparedStatement.execute();
	}

	private static void update(Connection connection, long id, String companyName, String phoneNumber) throws SQLException {
		if (connection.isClosed()){
			return;
		}
		PreparedStatement preparedStatement = connection.prepareStatement("update shippers set shipper_id = ?, company_name =?, phone = ? where shipper_id = ?");
		preparedStatement.setLong(1,id);
		preparedStatement.setString(2, companyName);
		preparedStatement.setString(3, phoneNumber);
		preparedStatement.setLong(4, id);

		preparedStatement.execute();
	}

	private static List<Shipper> listShippers(Connection connection) throws SQLException {
		List<Shipper> returnedList = new ArrayList<>();
		PreparedStatement preparedStatement =
				connection.prepareStatement("SELECT * FROM shippers ORDER BY shipper_id LIMIT 100");

		ResultSet currentRow = preparedStatement.executeQuery();
		while (currentRow.next()){
			//todo add a shipper for each entry in the resulting cursor (resultSet)
			Shipper shipper = new Shipper();
			shipper.setCompanyName(currentRow.getString("company_name"));
			shipper.setId(currentRow.getLong("shipper_id"));
			shipper.setPhoneNumber(currentRow.getString("phone"));
//			new Shipper(currentRow.getLong("shipper_id")
//					, currentRow.getString("company_name")
//					, currentRow.getString("phone"));
			returnedList.add(shipper);
		}
		return returnedList;
	}

}
