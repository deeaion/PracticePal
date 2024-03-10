package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import javafx.stage.Stage;

public class SettingsController {
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    public void setSettingsController(DBSubjectRepository subjectRepository, DBChapterRepository chapterRepository,
                                               DBQuestionRepository questionRepository, DBAnswerRepository answerRepository,
                                               DBAnswerIdeaRepository answerIdeaRepository, DBTestAnswerRepository testAnswerRepository,DBTestResultsRepository testResultsRepository,
                                               Stage primaryStage)
    {
        this.subjectRepository=subjectRepository;
        this.questionRepository=questionRepository;
        this.answerRepository=answerRepository;
        this.answerIdeaRepository=answerIdeaRepository;
        this.chapterRepository=chapterRepository;
        this.testResultsRepository=testResultsRepository;
        this.testAnswerRepository=testAnswerRepository;
        this.primaryStage=primaryStage;
    }
}
