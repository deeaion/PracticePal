package com.example.exampracticeapp.Domain;

import java.util.Objects;

public class Answer extends Entity<Long> {
    private String answerForQuestion;
    private Question question;

    public Answer(String answerForQuestion,Question question) {
        this.answerForQuestion = answerForQuestion;
        this.question=question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerForQuestion() {
        return answerForQuestion;
    }

    public void setAnswerForQuestion(String answerForQuestion) {
        this.answerForQuestion = answerForQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer answer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAnswerForQuestion(), answer.getAnswerForQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAnswerForQuestion());
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerForQuestion='" + answerForQuestion + '\'' +
                "} " + super.toString();
    }

    public Question getQuestion() {
        return question;
    }
}
