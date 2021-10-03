package fr.epita.quiz.services.data;

import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.datamodel.Teacher;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.conf.Configuration;

import java.sql.*;

/**
 * CRUD Operations on Student Class
 **/

public class TeacherDAO {

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
        String createTeacherTable = "CREATE TABLE IF NOT EXISTS TEACHER(ID IDENTITY PRIMARY KEY, SNAME VARCHAR(255), USERNAME VARCHAR(255), PASSWORD VARCHAR(255));";

        PreparedStatement preparedStatement = connection.prepareStatement(createTeacherTable);
        preparedStatement.execute();

        return connection;
    }


    public void create(Teacher newTeacher) throws CreationException {

        try (Connection connection = getConnection()){

            String insertTeacherStatement = "INSERT INTO TEACHER(SNAME, USERNAME, PASSWORD) VALUES (?,?,?)";

            PreparedStatement insertTeacher = connection.prepareStatement(insertTeacherStatement, Statement.RETURN_GENERATED_KEYS);
            insertTeacher.setString(1, newTeacher.getName());
            insertTeacher.setString(2, newTeacher.getUsername());
            insertTeacher.setString(3, newTeacher.getPassword());
            insertTeacher.execute();

        }catch (SQLException sqle){
            CreationException creationException = new CreationException();
            creationException.initCause(sqle);
            throw creationException;
        }

    }

    public Boolean authenticate(String username, String password) throws SQLException, CreationException {

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT SNAME,USERNAME,PASSWORD FROM TEACHER WHERE USERNAME=? AND PASSWORD=?");
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
