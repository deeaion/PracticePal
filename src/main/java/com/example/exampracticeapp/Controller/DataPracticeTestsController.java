package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataPracticeTestsController {

    @FXML
    private ComboBox<?> cbx_veracity;

    @FXML
    private Label lbl_test_name;

    @FXML
    private Label lbl_test_type;

    @FXML
    private ListView<?> list_tests;

    @FXML
    private TableColumn<?, ?> tblCol_answer;

    @FXML
    private TableColumn<?, ?> tblCol_question;

    @FXML
    private TableColumn<?, ?> tblCol_veracity;

    @FXML
    private TextField txt_date;

    @FXML
    private TextField txt_subject;

    @FXML
    private TextField txt_type;
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    private Service service;

    public void setDataPracticeTestsController(Service service,
                                     Stage primaryStage)
    {
        this.service=service;
        this.primaryStage=primaryStage;

    }
}
