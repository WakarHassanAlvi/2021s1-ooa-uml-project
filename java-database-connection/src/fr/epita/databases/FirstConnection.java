package fr.epita.databases;

import java.sql.*;

public class FirstConnection {

	public static void main(String[] args) throws SQLException {
		Connection connection =
				DriverManager.getConnection("jdbc:postgresql://localhost:5432/northwind", "postgres", "postgres");

		PreparedStatement preparedStatement =
				connection.prepareStatement("SELECT * FROM customers ORDER BY customer_id LIMIT 100 ");

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()){
			System.out.println(resultSet.getString("contact_name"));
		}



	}

}
