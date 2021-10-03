
import java.sql.SQLException;
import java.util.List;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.data.QuestionDAO;

public class QuestionDAOTests {


	public static void main(String[] args) throws SQLException {
		QuestionDAO dao = new QuestionDAO();
		try {
			dao.create(new Question("test question"));
		}catch (CreationException ce){
			System.out.println("something went wrong during question creation");
		}


		List<Question> search = dao.search(null);

		Question returnedQuestion = search.get(0);
		if (!"test question".equals(returnedQuestion.getQuestion())){
			System.out.println("error! titles mismatch");
			return;
		}
		System.out.println("success");

	}
}
