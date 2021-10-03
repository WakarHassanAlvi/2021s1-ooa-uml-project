package fr.epita.quiz.datamodel;

/** Child of Parent Answer
 * It will have an open answer to a question
 * **/
public class OpenAnswer extends Answer {

    //protected String answer;

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }
}
