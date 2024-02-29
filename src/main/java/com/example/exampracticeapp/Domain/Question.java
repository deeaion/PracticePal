package com.example.exampracticeapp.Domain;

import com.example.exampracticeapp.Domain.Enum.QuestionType;

import java.util.Objects;

public class Question extends Entity<Long>{
    private Chapter questionChapter;
    private String questionTitle;
    private QuestionType questionType;

    public Question(Chapter questionChapter, String questionTitle, QuestionType questionType) {
        this.questionChapter = questionChapter;
        this.questionTitle = questionTitle;
        this.questionType = questionType;
    }

    public Chapter getQuestionChapter() {
        return questionChapter;
    }

    public void setQuestionChapter(Chapter questionChapter) {
        this.questionChapter = questionChapter;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getQuestionChapter(), question.getQuestionChapter()) && Objects.equals(getQuestionTitle(), question.getQuestionTitle()) && getQuestionType() == question.getQuestionType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuestionChapter(), getQuestionTitle(), getQuestionType());
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionChapter=" + questionChapter +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionType=" + questionType +
                "} " + super.toString();
    }
}
