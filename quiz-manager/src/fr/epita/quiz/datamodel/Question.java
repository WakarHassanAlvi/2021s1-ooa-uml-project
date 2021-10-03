package fr.epita.quiz.datamodel;


import java.util.List;

/**
 * Question entity is dealing with global question information
 */

public class Question {

	public static final int DEFAULT_DIFFICULTY = 2;

	private Integer id;

	//Defaults to 2
	private Integer difficulty = DEFAULT_DIFFICULTY;
	private String question;
	private List<String> topics;

	private Question(){

	}
	public Question(String title){
		this.question = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}


}
