package fr.epita.quiz.launcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import fr.epita.quiz.datamodel.*;
import fr.epita.quiz.exceptions.CreationException;
import fr.epita.quiz.services.data.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * MAin Launcher of Quiz Manager Console Application. This is the Entry Point
 **/

public class Launcher {

	/**global topics list, we will use this to generate a Quiz for Students**/
	public static Set<String> globalTopicsList = new LinkedHashSet<>();

	/**Function to export the Quiz Questions with Answers to a txt File**/
	public static void export_quiz(Quiz studentQuiz) throws SQLException {

		try
		{
			File quizFile = new File("quiz_export.txt");/**File Creation**/

			FileWriter quizWriter = new FileWriter("quiz_export.txt");/**File Write**/
			quizWriter.write("Welcome to the Quiz: " + studentQuiz.getTitle() + "\n");
			quizWriter.write("Please find all the Quiz Questions with Answers below:\n\n");

			OpenAnswerDAO oaDAOSearch = new OpenAnswerDAO();
			MCQAnswerDAO mcqDAOSearch = new MCQAnswerDAO();
			List<Answer> searchedAnswers =  new ArrayList<>();
			/**Loop through questions and their respective answers**/
			List<Question> quizQuestions = studentQuiz.getQuestionsList();
			for (int i=0;i<quizQuestions.size();i++)
			{
				quizWriter.write((i+1) + ") " + quizQuestions.get(i).getQuestion()+"\n");/**write question**/
				/**To get answer to a question individually**/
				Answer answer = oaDAOSearch.getAnswer(quizQuestions.get(i).getId());/**get answer of above question**/
				String answerExists = answer.getAnswer();
				if(!answerExists.equals("not exists"))/**Open Answer**/
				{
					quizWriter.write("Answer: " + answer.getAnswer()+"\n\n");/**write answer**/
				}
				else/**MCQ Answer**/
				{
					Answer mcqAnswer = mcqDAOSearch.getAnswer(quizQuestions.get(i).getId());/**get answer of above question**/
					searchedAnswers.add(mcqAnswer);
					List<MCQChoice> choices = mcqAnswer.getChoices();
					for (i=0;i<choices.size();i++)
					{
						if(choices.get(i).getChoice().equals(mcqAnswer.getAnswer()))/**check which choice is correct**/
						{
							quizWriter.write((i+1) + ". " + choices.get(i).getChoice() + " (Correct Answer)\n");/**write to file**/
						}
						else
						{
							quizWriter.write((i+1) + ". " + choices.get(i).getChoice()+"\n");/**write to file**/
						}
					}
					quizWriter.write("\n\n");
				}
			}
			quizWriter.close();
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException, CreationException {

		/**UI**/
		System.out.println("/** Welcome to the Quiz Manager Console Application! **/");
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select from the following to proceed:");
		System.out.println("Press 1 if you are a Teacher");
		System.out.println("Press 2 if you are a Student");
		System.out.println("Press 3 to Exit!");

		int userType = scan.nextInt();
		scan.nextLine();

		while(userType!=3)
		{
			switch (userType) {
				case 1:/**Teacher Module**/
					/**An instance of Teacher Created for Authentication Purpose**/
					Teacher teacher = new Teacher("Thomas", "thomas@epita.fr", "thomas@123");
					TeacherDAO teacherDAO = new TeacherDAO();
					teacherDAO.create(teacher);

					System.out.println("/** Welcome to Teacher's Module **/");
					System.out.println("Please Enter your Email");/**thomas@epita.fr**/
					String email = scan.nextLine();
					System.out.println("Please Enter your Password");/**thomas@123**/
					String password = scan.nextLine();

					/**Check if Authentication is True**/
					Boolean teacher_exists = teacherDAO.authenticate(email, password);
					if(teacher_exists==TRUE)/**Auth True**/
					{
						System.out.println("Authenticated Successfully!");
						System.out.println("/** Welcome to the Teacher's Module of Quiz Manager. **/");
						System.out.println("Enter Quiz Questions one by one!");
						//System.out.println("/*********Please enter the no. of questions (enter > 0) :*********/");
						System.out.println("Please select the Type of Question from the following:");
						System.out.println("Press 1 for Open Question");
						System.out.println("Press 2 for MCQ Question");
						System.out.println("Press 3 to Logout!");
						String questionType = scan.nextLine();

						if(questionType.equals("3"))
						{
							System.out.println("You are Logged out, See you later!");
						}

						int questionCounter = 0;/**To keep track of no. of questions**/

						/**keep taking input Questions and Answers from Teacher until Teacher presses 3 to Logout**/
						while ((!questionType.equals("3")))
						{
							System.out.println("Input a Question Title :");
							String questionTitle = scan.nextLine();
							System.out.println("Input the question topics (separated by , if more than 1)");
							String questionTopics = scan.nextLine();
							System.out.println("Input the question difficulty (0 to 5)");
							Integer questionDifficulty = scan.nextInt();
							scan.nextLine();

							/**Split Question Topics**/
							List<String> topicsList = Arrays.asList(questionTopics.split(","));
							for (int i=0;i<topicsList.size();i++)
							{
								/**store all the topics of all questions, later we will use this to generate quiz**/
								globalTopicsList.add(topicsList.get(i));
							}
							///////////////////////////////////////////////////////////////

							/**Business Logic**/
							Question question = new Question(questionTitle);/**Instance of Question**/
							question.setDifficulty(questionDifficulty);
							question.setTopics(topicsList);

							//Data access
							QuestionDAO dao = new QuestionDAO();
							int questionID = dao.create(question);/**Store Question in DB**/

							/**open answer**/
							if(questionType.equals("1"))/**Answer for Open Question Type**/
							{
								System.out.println("Please Input the Answer to the Question below:)");
								String openAnswer = scan.nextLine();

								/**Business Logic**/
								/**Store answer in Answer Instance**/
								Answer answer = new OpenAnswer();/**Polymorphism**/
								answer.setAnswer(openAnswer);
								answer.setQuestionID(questionID);

								/**Data Access**/
								OpenAnswerDAO oaDAO = new OpenAnswerDAO();
								oaDAO.create(answer);/**store Answer in DB**/
							}
							else if(questionType.equals("2"))/**Answer for MCQ Question**/
							{
								System.out.println("Please Input the Multiple Choice Answers one by one.");
								System.out.println("Press q to Stop!");
								String mcqAnswer = scan.nextLine();

								List<MCQChoice> choices = new ArrayList<>();/**list of mcq choices**/
								do
								{
									System.out.println("Please Input the correctness of the MCQ Option above.");
									System.out.println("Press 0 for False, 1 for True");
									int correctness  = scan.nextInt();

									/**populate MCQ Choices class**/
									MCQChoice choice = new MCQChoice();
									choice.setChoice(mcqAnswer);
									if(correctness==0)
									{
										choice.setValid(FALSE);
									}
									else if(correctness==1)
									{
										choice.setValid(TRUE);
									}

									/**Add to MCQ Choices List**/
									choices.add(choice);

									scan.nextLine();
									System.out.println("Please Input the Multiple Choice Answers one by one.");
									System.out.println("Press q to Stop!");
									mcqAnswer = scan.nextLine();

								}while (!mcqAnswer.equals("q"));//keep taking choices until Teacher presses q

								/**Business Logic**/
								/**store Answer in instance**/
								Answer answer = new MCQAnswer();/**Polymorphism**/
								answer.setAnswer(choices);/**mcq choices**/
								answer.setQuestionID(questionID);

								/**Data Access**/
								MCQAnswerDAO mcqDAO = new MCQAnswerDAO();
								mcqDAO.create(answer);/**Store Answer in DB**/

							}

							System.out.println("Please select the Type of Question from the following:");
							System.out.println("Press 1 for Open Question");
							System.out.println("Press 2 for MCQ Question");
							System.out.println("Press 3 to Logout!");
							questionType = scan.nextLine();

							questionCounter++;
						}

						if(questionCounter>0)/**Only display below message if Teacher inserted some Questions**/
						{
							System.out.println("/*************************************************/");
							System.out.println("Thank you for creating Questions!");
							System.out.println("These questions will be used to generate Quizes.");
							System.out.println("/*************************************************/");
						}
					}
					break;
				case 2:/**Student Module**/
					/**Created a Student Instance for Authentication Purpose**/
					Student student = new Student("Waqar", "waqar@epita.fr", "waqar@123");
					StudentDAO studentDAO = new StudentDAO();
					studentDAO.create(student);/**Store in DB**/

					System.out.println("/** Welcome to Student Module **/");
					System.out.println("Please Enter your Email");/**waqar@epita.fr**/
					String semail = scan.nextLine();
					System.out.println("Please Enter your Password");/**waqar@123**/
					String spassword = scan.nextLine();

					/**Check Authentication Success**/
					Boolean student_exists = studentDAO.authenticate(semail, spassword);
					if(student_exists)/**Success**/
					{
						System.out.println("Authenticated Successfully!");
						System.out.println("Please select a Topic from the following to start a Quiz (Separated by , if more than 1)");
						System.out.println("Select the Topic No. please!");

						/**convert global topics list to ArrayList**/
						List<String> topicsList = new ArrayList<>(globalTopicsList);

						/**print all the topics for Student to Chose to generate Quiz**/
						for (int i=0;i<topicsList.size();i++)
						{
							System.out.println((i+1) + ") " + topicsList.get(i));
						}

						String quizTopics = scan.nextLine();

						/**We will iterate through this list of topics to search for all Questions with this topic in DB**/
						List<String> quizTopicsList = Arrays.asList(quizTopics.split(","));

						List<Question> searchedQuestion = new ArrayList<Question>();/**All Questions will be stored here**/
						String quizName = "";

						/**iteration**/
						for (int i=0;i<quizTopicsList.size();i++)
						{
							int quizTopic = Integer.parseInt(quizTopicsList.get(i));
							String searchTopic = topicsList.get(quizTopic-1);

							quizName = quizName.concat(searchTopic + " ");/**quiz title**/

							QuestionDAO dao_search = new QuestionDAO();
							/**get all questions with search topic from DB and store in list**/
							searchedQuestion.addAll(dao_search.search(searchTopic));

						}
						//int quizTopic = scan.nextInt();
						//scan.nextLine();
						System.out.println("You have chosen " + quizName + " as your Quiz Topic!");

						/**Initiate a Quiz Class**/
						Quiz studentQuiz = new Quiz();
						studentQuiz.setTitle(quizName);
						studentQuiz.setQuestionsList(searchedQuestion);/**set searched questions to Quiz**/

						System.out.println("Please Answer the Quiz Questions one by One");

						/*
						To get answers to all questions of a topic
						OpenAnswerDAO oaDAOSearch = new OpenAnswerDAO();
						List<Answer> searchedAnswers = oaDAOSearch.getAnswers(searchTopic);//get answers of above questions

						MCQAnswerDAO mcqDAOSearch = new MCQAnswerDAO();
						List<Answer> searchedMCQAnswers = mcqDAOSearch.getAnswers(searchTopic);//get answers of above questions
						*/

						OpenAnswerDAO oaDAOSearch = new OpenAnswerDAO();
						MCQAnswerDAO mcqDAOSearch = new MCQAnswerDAO();
						List<Answer> searchedAnswers =  new ArrayList<>();

						int quizScore = 0;/**calculate score to display at end**/
						List<Question> quizQuestions = studentQuiz.getQuestionsList();/**get questions from Quiz Instance**/

						/**Loop through questions and their respective answers**/
						for (int i=0;i<quizQuestions.size();i++)
						{
							System.out.println((i+1) + ") " + quizQuestions.get(i).getQuestion());//print question

							/**To get answer to each question individually**/
							Answer answer = oaDAOSearch.getAnswer(quizQuestions.get(i).getId());/**get answer of above question**/
							String answerExists = answer.getAnswer();
							if(!answerExists.equals("not exists"))/**check if it's Open Answer Question**/
							{
								//System.out.println(answer.getAnswer());
								System.out.println("/**************************************************/");
								searchedAnswers.add(answer);

								System.out.println("Please enter your answer to the above Question:");
								String reponde = scan.nextLine();

								if(reponde.equals(answer.getAnswer()))/**compare Student Answer with actual answer**/
								{
									quizScore++;/**correct answer by Student**/
								}
								System.out.println("Please Answer the next Question now");
							}
							else/**MCQ Question**/
							{
								Answer mcqAnswer = mcqDAOSearch.getAnswer(quizQuestions.get(i).getId());/**get answer of above question**/
								searchedAnswers.add(mcqAnswer);

								List<MCQChoice> choices = mcqAnswer.getChoices();/**get all choices**/
								for (i=0;i<choices.size();i++)
								{
									System.out.println((i+1) + ". " + choices.get(i).getChoice());
								}

								System.out.println("/*********************************************************/");
								System.out.println("Please select your choice from the above Question (Select Choice No.):");
								int choice = scan.nextInt();
								scan.nextLine();
								String reponde = choices.get(choice-1).getChoice();
								if(reponde.equals(mcqAnswer.getAnswer()))/**match Student Answer with Actual Answer**/
								{
									quizScore++;/**correct answer by student**/
								}
							}

							//System.out.println(searchedAnswers.get(i).getAnswer());

							/*for mcq answers
							List<MCQChoice> choices = searchedMCQAnswers.get(i).getChoices();
							for (int j=0;j<choices.size();j++)
							{
								System.out.println(choices.get(j).getChoice());
							}
							System.out.println("//////////////////////////////////////////////////////////////");
							*/

							//System.out.println(searchedMCQAnswers.get(i).getAnswer());
							//Get Answer of Each Question Separately
							//int questionID = searchedQuestion.get(i).getId();


						}

						System.out.println("/*****************************************************/");
						System.out.println("Your Quiz Score is: " + quizScore + "/" + quizQuestions.size());
						System.out.println("Do you want to export this Quiz?");
						System.out.println("Press Y for yes, N for no.");
						String export = scan.nextLine();
						if(export.equals("Y"))
						{
							export_quiz(studentQuiz);/**function to export Quiz Questions with Answers**/
							System.out.println("The Quiz has been exported to the file quiz_export.txt");
						}
						System.out.println("/*****************************************************/");
					}

					//String[] arguments = new String[] {"123"};
					//main(arguments);
					break;
				case 3:
					System.out.println("Thanks for coming, Bye!");
					break;
				default:
					System.out.println("Thank you for coming! Bye");
			}//switch

			System.out.println("Please select from the following to proceed:");
			System.out.println("Press 1 if you are a Teacher");
			System.out.println("Press 2 if you are a Student");
			System.out.println("Press 3 to Exit!");
			userType = scan.nextInt();
			scan.nextLine();
		}//while

		if(userType==3)
		{
			System.out.println("Thanks for coming, see you again. Bye!");
		}
		scan.close();
	}//main
}
