package fr.epita.quiz.datamodel;

import java.util.List;

/**Parent Class
 * It will deal with all the answers of respective questions
 * It can be of 2 types
 * MCQ & Open Answer (Child Classes)
 **/
public class Answer {

    protected int id;/** answer id **/
    protected int QuestionID;/** respective questiond id **/
    protected String answer;/** open answer **/
    protected List<MCQChoice> choices;/** mcq **/

    public String getAnswer()
    {
        return this.answer;
    }

    public List<MCQChoice> getChoices()
    {
        return this.choices;
    }

    public void setAnswer(List<MCQChoice> choices)
    {
        this.choices = choices;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
