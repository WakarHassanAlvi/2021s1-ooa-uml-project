package fr.epita.quiz.datamodel;

import java.util.List;

/**Child Class of Answer
 * It has multiple choices of which one or more could be correct
 * **/
public class MCQAnswer extends Answer{

    //protected List<MCQChoice> choices;

    @Override
    public void setAnswer(List<MCQChoice> choices) {
        this.choices = choices;
    }

    @Override
    public List<MCQChoice> getChoices()
    {
        return this.choices;
    }

    @Override
    public String getAnswer() {

        String answer="";
        for (int i=0;i<this.choices.size();i++)
        {
            if(choices.get(i).isValid())
            {
                answer = choices.get(i).getChoice();
                break;
            }
        }

        return answer;
    }
}
