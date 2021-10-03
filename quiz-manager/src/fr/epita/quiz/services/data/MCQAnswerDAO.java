package fr.epita.quiz.services.data;

import fr.epita.quiz.datamodel.Answer;
import fr.epita.quiz.datamodel.MCQAnswer;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.OpenAnswer;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.conf.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD Operations for MCQ Answers
 * **/

public class MCQAnswerDAO {
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
        String createMCQAnswerTable = "CREATE TABLE IF NOT EXISTS MCQANSWER(ID IDENTITY PRIMARY KEY, QUESTIONID INT, CHOICE VARCHAR(255), VALID BOOLEAN);";

        PreparedStatement preparedStatement = connection.prepareStatement(createMCQAnswerTable);
        preparedStatement.execute();

        return connection;
    }

    /** create am mcq answer in DB **/
    public void create(Answer newAnswer) throws CreationException {
        try (Connection connection = getConnection()){

            //Get MCQ Options and iterate
            List<MCQChoice> choices = newAnswer.getChoices();
            for (int i=0;i<choices.size();i++)
            {
                System.out.println(choices.get(i).getChoice());
                System.out.println(choices.get(i).isValid());
                String insertMCQStatement = "INSERT INTO MCQANSWER(QUESTIONID,CHOICE,VALID) VALUES (?,?,?)";

                PreparedStatement insertAnswer = connection.prepareStatement(insertMCQStatement, Statement.RETURN_GENERATED_KEYS);
                insertAnswer.setInt(1, newAnswer.getQuestionID());
                insertAnswer.setString(2, choices.get(i).getChoice());
                insertAnswer.setBoolean(3, choices.get(i).isValid());
                insertAnswer.execute();
            }

        }catch (SQLException sqle){
            CreationException creationException = new CreationException();
            creationException.initCause(sqle);
            throw creationException;
        }


    }

    /**Get Answer to a Particular Question**/
    public Answer getAnswer(int questionID) throws SQLException {

        Connection connection = getConnection();

        PreparedStatement selectAnswerStatement = connection.prepareStatement("SELECT ID, CHOICE, VALID FROM MCQANSWER WHERE QUESTIONID=?");
        selectAnswerStatement.setInt(1, questionID);
        ResultSet resultSetAnswer = selectAnswerStatement.executeQuery();

        List<MCQChoice> choices = new ArrayList<>();
        int answerID = -1;
        while (resultSetAnswer.next()){
            answerID = resultSetAnswer.getInt("ID");
            String choice = resultSetAnswer.getString("CHOICE");
            Boolean isValid = resultSetAnswer.getBoolean("VALID");
            MCQChoice mcqChoice = new MCQChoice();
            mcqChoice.setChoice(choice);
            mcqChoice.setValid(isValid);
            choices.add(mcqChoice);
        }

        MCQAnswer mcqAnswer = new MCQAnswer();
        mcqAnswer.setId(answerID);
        mcqAnswer.setQuestionID(questionID);
        mcqAnswer.setAnswer(choices);

        connection.close();
        return mcqAnswer;
    }

    /**Get Answers of all Questions based on a Topic**/
    public List<Answer> getAnswers(String topic) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT QUESTIONID FROM TOPIC WHERE TITLE=?");
        statement.setString(1, topic);
        ResultSet question_ids = statement.executeQuery();

        List<Answer> answers = new ArrayList<>();

        while (question_ids.next()) {
            int question_id = question_ids.getInt("QUESTIONID");
            PreparedStatement selectAnswerStatement = connection.prepareStatement("SELECT ID, CHOICE, VALID FROM MCQANSWER WHERE QUESTIONID=?");
            selectAnswerStatement.setInt(1, question_id);
            ResultSet resultSetAnswer = selectAnswerStatement.executeQuery();

            List<MCQChoice> choices = new ArrayList<>();
            int answerID = -1;
            while (resultSetAnswer.next()){
                answerID = resultSetAnswer.getInt("ID");
                String choice = resultSetAnswer.getString("CHOICE");
                Boolean isValid = resultSetAnswer.getBoolean("VALID");
                MCQChoice mcqChoice = new MCQChoice();
                mcqChoice.setChoice(choice);
                mcqChoice.setValid(isValid);
                choices.add(mcqChoice);
            }

            MCQAnswer mcqAnswer = new MCQAnswer();
            mcqAnswer.setId(answerID);
            mcqAnswer.setQuestionID(question_id);
            mcqAnswer.setAnswer(choices);
            answers.add(mcqAnswer);
        }
        connection.close();
        return answers;
    }
}
