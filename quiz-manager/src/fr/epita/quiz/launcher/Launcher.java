package fr.epita.quiz.launcher;

import java.util.Scanner;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.QuestionDAO;

public class Launcher {

	public static void main(String[] args) {
		//UI
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello, input a question title :");
		String questionTitle = scanner.nextLine();
		System.out.println("Input the question difficulty (0 to 5)");
		Integer questionDifficulty = scanner.nextInt();

		//Business Logic
		Question question = new Question(questionTitle);
		question.setDifficulty(questionDifficulty);


		//Data access
		QuestionDAO dao = new QuestionDAO();
		dao.create(question);


	}
}
