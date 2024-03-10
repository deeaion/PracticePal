package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
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
    public void setDataPracticeTestsController(DBSubjectRepository subjectRepository, DBChapterRepository chapterRepository,
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
