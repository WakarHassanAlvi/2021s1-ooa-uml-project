package fr.epita.quiz.datamodel;

/** A Class to store all the MCQ Choices
 *
 * **/
public class MCQChoice {

    protected String choice;//choice itself
    protected boolean valid;//true or false

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
