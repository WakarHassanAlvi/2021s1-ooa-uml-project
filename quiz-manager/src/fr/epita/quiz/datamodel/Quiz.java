package fr.epita.quiz.datamodel;

import java.util.List;

/**
 * Quiz Class consists of a list of Questions
 * It will beused to generate quizes for students
 * **/
public class Quiz {

    private String title;
    private List<Question> questionsList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList = questionsList;
    }
}
