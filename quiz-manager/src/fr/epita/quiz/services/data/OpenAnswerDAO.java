package fr.epita.quiz.services.data;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.OpenAnswer;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.conf.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD Operations for Open Answer Class
 * **/

public class OpenAnswerDAO {

    private Connection getConnection() throws SQLException {

        Configuration conf = Configuration.getInstance();
        Connection connection = DriverManager.getConnection(conf.getConfValue("db.url"),
                conf.getConfValue("db.user"),
                conf.getConfValue("db.password"));
        String schema = connection.getSchema();
        if (!"PUBLIC".equals(schema)){
            throw new RuntimeException("connection was not successful");
        }

        /**create open answer table**/
        String createOpenAnswerTable = "CREATE TABLE IF NOT EXISTS OPENANSWER(ID IDENTITY PRIMARY KEY, QUESTIONID INT, ANSWER VARCHAR(255));";

        PreparedStatement preparedStatement = connection.prepareStatement(createOpenAnswerTable);
        preparedStatement.execute();

        return connection;
    }

    /** insert a open answer in DB **/
    public void create(Answer newAnswer) throws CreationException {//insert
        try (Connection connection = getConnection()){

            String insertAnswerStatement = "INSERT INTO OPENANSWER(QUESTIONID,ANSWER) VALUES (?,?)";

            PreparedStatement insertAnswer = connection.prepareStatement(insertAnswerStatement, Statement.RETURN_GENERATED_KEYS);
            insertAnswer.setInt(1, newAnswer.getQuestionID());
            insertAnswer.setString(2, newAnswer.getAnswer());
            insertAnswer.execute();

        }catch (SQLException sqle){
            CreationException creationException = new CreationException();
            creationException.initCause(sqle);
            throw creationException;
        }

    }

    /**Search for a particular Answer of a Question**/
    public Answer getAnswer(int questionID) throws SQLException {

        Connection connection = getConnection();

        PreparedStatement selectAnswerStatement = connection.prepareStatement("SELECT ID, ANSWER FROM OPENANSWER WHERE QUESTIONID=?");
        selectAnswerStatement.setInt(1, questionID);
        ResultSet resultSetAnswer = selectAnswerStatement.executeQuery();
        Answer openAnswer = new OpenAnswer();
        if (resultSetAnswer.next() == false)//if the answer does not exist
        {
            //int id = resultSetAnswer.getInt("ID");
            //String answer = resultSetAnswer.getString("ANSWER");
            openAnswer.setId(-1);
            openAnswer.setQuestionID(questionID);
            openAnswer.setAnswer("not exists");
        }
        else//if the answer exists
        {
             do{
                int id = resultSetAnswer.getInt("ID");
                String answer = resultSetAnswer.getString("ANSWER");
                openAnswer.setId(id);
                openAnswer.setQuestionID(questionID);
                openAnswer.setAnswer(answer);
             }while (resultSetAnswer.next());
        }

        connection.close();
        return openAnswer;
    }

    /**function to get all the answers to questions with a particular topic**/
    public List<Answer> getAnswers(String topic) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT QUESTIONID FROM TOPIC WHERE TITLE=?");
        statement.setString(1, topic);
        ResultSet question_ids = statement.executeQuery();

        List<Answer> answers = new ArrayList<>();

        while (question_ids.next()) {
            int question_id = question_ids.getInt("QUESTIONID");
            PreparedStatement selectAnswerStatement = connection.prepareStatement("SELECT ID, ANSWER FROM OPENANSWER WHERE QUESTIONID=?");
            selectAnswerStatement.setInt(1, question_id);
            ResultSet resultSetAnswer = selectAnswerStatement.executeQuery();

            while (resultSetAnswer.next()){
                int id = resultSetAnswer.getInt("ID");
                String answer = resultSetAnswer.getString("ANSWER");
                OpenAnswer openAnswer = new OpenAnswer();
                openAnswer.setId(id);
                openAnswer.setQuestionID(question_id);
                openAnswer.setAnswer(answer);
                answers.add(openAnswer);
            }
        }
        connection.close();
        return answers;
    }

}
