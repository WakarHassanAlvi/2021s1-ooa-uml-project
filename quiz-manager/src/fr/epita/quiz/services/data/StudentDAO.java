package fr.epita.quiz.services.data;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.conf.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD Operations on Student Class
 **/

public class StudentDAO {

    private Connection getConnection() throws SQLException {

        Configuration conf = Configuration.getInstance();
        Connection connection = DriverManager.getConnection(conf.getConfValue("db.url"),
                conf.getConfValue("db.user"),
                conf.getConfValue("db.password"));
        String schema = connection.getSchema();
        if (!"PUBLIC".equals(schema)){
            throw new RuntimeException("connection was not successful");
        }

        //////////////////////////////////create student table///////////////////////////////
        String createStudentTable = "CREATE TABLE IF NOT EXISTS STUDENT(ID IDENTITY PRIMARY KEY, SNAME VARCHAR(255), USERNAME VARCHAR(255), PASSWORD VARCHAR(255));";

        PreparedStatement preparedStatement = connection.prepareStatement(createStudentTable);
        preparedStatement.execute();

        return connection;
    }


    public void create(Student newStudent) throws CreationException {

        try (Connection connection = getConnection()){

            String insertStudentStatement = "INSERT INTO STUDENT(SNAME, USERNAME, PASSWORD) VALUES (?,?,?)";

            PreparedStatement insertStudent = connection.prepareStatement(insertStudentStatement, Statement.RETURN_GENERATED_KEYS);
            insertStudent.setString(1, newStudent.getName());
            insertStudent.setString(2, newStudent.getUsername());
            insertStudent.setString(3, newStudent.getPassword());
            insertStudent.execute();

        }catch (SQLException sqle){
            CreationException creationException = new CreationException();
            creationException.initCause(sqle);
            throw creationException;
        }

    }

    public Boolean authenticate(String username, String password) throws SQLException, CreationException {

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT SNAME,USERNAME,PASSWORD FROM STUDENT WHERE USERNAME=? AND PASSWORD=?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next() == false) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        }catch (SQLException sqle){
            CreationException creationException = new CreationException();
            creationException.initCause(sqle);
            throw creationException;
        }
    }

}
