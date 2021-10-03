package fr.epita.quiz.services.data;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.OpenAnswer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.conf.Configuration;

import static java.sql.PreparedStatement.*;

/**
 * CRUD operations for Question Class
 * **/

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

		/**create question table**/
		String createQuestionTable = "CREATE TABLE IF NOT EXISTS QUESTION(ID IDENTITY PRIMARY KEY, TITLE VARCHAR(255), DIFFICULTY INT);";

		PreparedStatement preparedStatement = connection.prepareStatement(createQuestionTable);
		preparedStatement.execute();

		/**I am creating a separate Topic Table to store Topics for Each Question**/
		/**create topics table**/
		String createTopicsTable = "CREATE TABLE IF NOT EXISTS TOPIC(ID IDENTITY PRIMARY KEY, TITLE VARCHAR(255), QUESTIONID INT);";

		PreparedStatement preparedStatementTopics = connection.prepareStatement(createTopicsTable);
		preparedStatementTopics.execute();

		return connection;
	}

	/** insert a question and topics in respective tables**/
	public int create(Question newQuestion) throws CreationException {//Insert a Question in DB
		long question_id = -1;
		try (Connection connection = getConnection()){

			String insertQuestionStatement = "INSERT INTO QUESTION(TITLE, DIFFICULTY) VALUES (?,?)";

			PreparedStatement insertQuestion = connection.prepareStatement(insertQuestionStatement, Statement.RETURN_GENERATED_KEYS);
			insertQuestion.setString(1, newQuestion.getQuestion());
			insertQuestion.setInt(2, newQuestion.getDifficulty());
			insertQuestion.execute();

			ResultSet generatedKeys = insertQuestion.getGeneratedKeys();
			if (generatedKeys.next()) {
				question_id = generatedKeys.getLong(1);

				//////////////////////////////////////////////////
				List<String> topics = newQuestion.getTopics();
				for (int i=0;i<topics.size();i++)
				{
					//System.out.println(topics.get(i));
					String insertTopicStatement = "INSERT INTO TOPIC(TITLE, QUESTIONID) VALUES (?,?)";

					PreparedStatement insertTopics = connection.prepareStatement(insertTopicStatement, Statement.RETURN_GENERATED_KEYS);
					insertTopics.setString(1, topics.get(i));
					insertTopics.setInt(2, (int)question_id);
					insertTopics.execute();
				}
				/////////////////////////////////////////////////
			} else {
				// Throw exception?
			}

		}catch (SQLException sqle){
			CreationException creationException = new CreationException();
			creationException.initCause(sqle);
			throw creationException;
		}

		return (int)question_id;

	}

	/**get a list of all questions based on a topic**/
	public List<Question> search(String topic) throws SQLException{

		Connection connection = getConnection();

		PreparedStatement statement = connection.prepareStatement("SELECT QUESTIONID FROM TOPIC WHERE TITLE=?");
		statement.setString(1, topic);
		ResultSet question_ids = statement.executeQuery();

		List<Question> questions = new ArrayList<>();

		while (question_ids.next()) {
			int question_id = question_ids.getInt("QUESTIONID");
			PreparedStatement selectStatement = connection.prepareStatement("SELECT TITLE, DIFFICULTY FROM QUESTION WHERE ID=?");
			selectStatement.setInt(1, question_id);
			ResultSet resultSet = selectStatement.executeQuery();

			while (resultSet.next()){
				int id = question_id;
				int difficulty = resultSet.getInt("DIFFICULTY");
				String title = resultSet.getString("TITLE");
				Question question = new Question(title);
				question.setId(id);
				question.setDifficulty(difficulty);
				questions.add(question);
			}
		}
		connection.close();
		return questions;
	}


}
