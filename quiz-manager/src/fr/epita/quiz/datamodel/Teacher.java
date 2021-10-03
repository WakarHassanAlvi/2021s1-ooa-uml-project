package fr.epita.quiz.datamodel;

/**
 * A Teacher can create questions for the Quizes
 * **/

public class Teacher {

    private String name;
    private int ID;
    private String username;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Teacher(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
