package com.example.exampracticeapp.Domain;

import java.util.Objects;

public class AnswerIdea extends Entity<Long>{
    private String Idea;
    private Question question;

    public AnswerIdea(String idea, Question question) {
        Idea = idea;
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getIdea() {
        return Idea;
    }

    public void setIdea(String idea) {
        Idea = idea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerIdea that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getIdea(), that.getIdea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdea());
    }

    @Override
    public String toString() {
        return "AnswerIdea{" +
                "Idea='" + Idea + '\'' +
                "} " + super.toString();
    }
}
