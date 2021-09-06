package fr.epita.quiz.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import fr.epita.quiz.datamodel.Question;

public class QuestionDAO {



	private Connection getConnection() throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "root", "root");
		String schema = connection.getSchema();
		if (!"PUBLIC".equals(schema)){
			throw new Exception("connection was not successful");
		}
		return connection;
	}


	public void create(Question question){
		Connection connection = getConnection();
		String insertStatement = "INSERT INTO QUESTION(TITLE, DIFFICULTY) VALUES (?,?)";

		PreparedStatement insert = connection.prepareStatement(insertStatement,  Statement.RETURN_GENERATED_KEYS);
		insert.setString(1, question.getQuestion());
		insert.setInt(2,  question.getDifficulty());
	}


}
