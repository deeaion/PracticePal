package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PracticeTestsController {

    @FXML
    private Button btn_StartTest;

    @FXML
    private TextField txt_fromChapters;

    @FXML
    private TextField txt_nrOfQuestions;

    @FXML
    private TextField txt_subject;

    @FXML
    private TextField txt_toChapters;
    private Service service;

    @FXML
    void handleStartTest(ActionEvent event) {

    }
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    public void setDataPracticeTestsController(Service service,
                                               Stage primaryStage)
    {
        this.service=service;
        this.primaryStage=primaryStage;

    }
}
