package com.example.exampracticeapp.Domain;

import com.example.exampracticeapp.Domain.Enum.Difficulty;

import java.util.Objects;

public class Chapter extends Entity<Long>{
    private Subject ChapterSubject;
    private int ChapterNumber;
    private String ChapterTitle;
    private Difficulty ChapterDifficulty;

    public Chapter(Subject chapterSubject, int chapterNumber, String chapterTitle, Difficulty chapterDifficulty) {
        ChapterSubject = chapterSubject;
        ChapterNumber = chapterNumber;
        ChapterTitle = chapterTitle;
        ChapterDifficulty = chapterDifficulty;
    }

    public Subject getChapterSubject() {
        return ChapterSubject;
    }

    public void setChapterSubject(Subject chapterSubject) {
        ChapterSubject = chapterSubject;
    }

    public int getChapterNumber() {
        return ChapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        ChapterNumber = chapterNumber;
    }

    public String getChapterTitle() {
        return ChapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        ChapterTitle = chapterTitle;
    }

    public Difficulty getChapterDifficulty() {
        return ChapterDifficulty;
    }

    public void setChapterDifficulty(Difficulty chapterDifficulty) {
        ChapterDifficulty = chapterDifficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chapter chapter)) return false;
        if (!super.equals(o)) return false;
        return getChapterNumber() == chapter.getChapterNumber() && Objects.equals(getChapterSubject(), chapter.getChapterSubject()) && Objects.equals(getChapterTitle(), chapter.getChapterTitle()) && Objects.equals(getChapterDifficulty(), chapter.getChapterDifficulty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getChapterSubject(), getChapterNumber(), getChapterTitle(), getChapterDifficulty());
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "ChapterSubject=" + ChapterSubject +
                ", ChapterNumber=" + ChapterNumber +
                ", ChapterTitle='" + ChapterTitle + '\'' +
                ", ChapterDifficulty='" + ChapterDifficulty + '\'' +
                "} " + super.toString();
    }
}
