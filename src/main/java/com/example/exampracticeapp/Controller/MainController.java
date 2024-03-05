package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import javafx.stage.Stage;

public class MainController {
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private Stage primaryStage;
    public void setMainViewController(DBSubjectRepository subjectRepository, DBChapterRepository chapterRepository,
                                      DBQuestionRepository questionRepository, DBAnswerRepository answerRepository,
                                      DBAnswerIdeaRepository answerIdeaRepository, Stage primaryStage)
    {
        this.subjectRepository=subjectRepository;
        this.questionRepository=questionRepository;
        this.answerRepository=answerRepository;
        this.answerIdeaRepository=answerIdeaRepository;
        this.chapterRepository=chapterRepository;
        this.primaryStage=primaryStage;
    }
}
