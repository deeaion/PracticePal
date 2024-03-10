package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RandomizeController {

    @FXML
    private Button btn_startTest;

    @FXML
    private CheckBox check_noRepeat;

    @FXML
    private TextField txt_ChapterFrom;

    @FXML
    private TextField txt_ChapterTo;

    @FXML
    private TextField txt_subject;
    private Service service;

    @FXML
    void handleStartingTest(ActionEvent event) {

    }
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;

    public void setRandomizeMenuController(Service service,
                                               Stage primaryStage)
    {
        this.service=service;
        this.primaryStage=primaryStage;

    }


}
