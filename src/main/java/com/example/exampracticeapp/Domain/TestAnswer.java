package com.example.exampracticeapp.Domain;

import java.util.Objects;

public class TestAnswer extends Entity<Long>
{
    Question question;
    String answer;
    String veracity;

    public TestAnswer(Question question, String answer, String veracity) {
        this.question = question;
        this.answer = answer;
        this.veracity = veracity;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getVeracity() {
        return veracity;
    }

    public void setVeracity(String veracity) {
        this.veracity = veracity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestAnswer that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getQuestion(), that.getQuestion()) && Objects.equals(getAnswer(), that.getAnswer()) && Objects.equals(getVeracity(), that.getVeracity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuestion(), getAnswer(), getVeracity());
    }

    @Override
    public String toString() {
        return "TestAnswer{" +
                "question=" + question +
                ", answer='" + answer + '\'' +
                ", veracity='" + veracity + '\'' +
                "} " + super.toString();
    }
}
