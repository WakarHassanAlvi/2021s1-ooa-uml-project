package fr.epita.quiz.services.data;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.conf.Configuration;

public class QuestionDAO {



	private Connection getConnection() throws SQLException {

		Configuration conf = Configuration.getInstance();
		Connection connection = DriverManager.getConnection(conf.getConfValue("db.url"),
				conf.getConfValue("db.user"),
				conf.getConfValue("db.password"));
		String schema = connection.getSchema();
		if (!"PUBLIC".equals(schema)){
			throw new RuntimeException("connection was not successful");
		}
		String createStatement = "CREATE TABLE IF NOT EXISTS QUESTION(ID IDENTITY PRIMARY KEY, TITLE VARCHAR(255), DIFFICULTY INT);";

		PreparedStatement preparedStatement = connection.prepareStatement(createStatement);
		preparedStatement.execute();

		return connection;
	}


	public void create(Question newQuestion) throws CreationException {
		try (Connection connection = getConnection()){

			String insertStatement = "INSERT INTO QUESTION(TITLE, DIFFICULTY) VALUES (?,?)";

			PreparedStatement insert = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);
			insert.setString(1, newQuestion.getQuestion());
			insert.setInt(2, newQuestion.getDifficulty());
			insert.execute();
		}catch (SQLException sqle){
			CreationException creationException = new CreationException();
			creationException.initCause(sqle);
			throw creationException;
		}
	}

	public List<Question> search(Question questionCriteria) throws SQLException{
		String selectStatement = "SELECT ID, TITLE, DIFFICULTY FROM QUESTION";

		Connection connection = getConnection();
		PreparedStatement select = connection.prepareStatement(selectStatement);
		ResultSet resultSet = select.executeQuery();


		List<Question> questions = new ArrayList<>();
		while (resultSet.next()){
			int id = resultSet.getInt("ID");
			int difficulty = resultSet.getInt("DIFFICULTY");
			String title = resultSet.getString("TITLE");
			Question question = new Question(title);
			question.setId(id);
			question.setDifficulty(difficulty);
			questions.add(question);
		}
		connection.close();
		return questions;
	}


}
