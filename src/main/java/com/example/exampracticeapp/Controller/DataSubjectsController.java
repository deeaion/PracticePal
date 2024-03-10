package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DataSubjectsController {

    @FXML
    private ImageView btn_add_chapter;

    @FXML
    private ImageView btn_add_subject;

    @FXML
    private Label lbl_subject_name;

    @FXML
    private ListView<?> list_chapters;

    @FXML
    private TableColumn<?, ?> tblCol_description;

    @FXML
    private TableColumn<?, ?> tblCol_name;

    @FXML
    private TableColumn<?, ?> tblCol_semester;

    @FXML
    private TableColumn<?, ?> tblCol_year;

    @FXML
    private TableView<?> tbl_subjects;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_semester;

    @FXML
    private TextField txt_year;
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    private Service service;

    public void setDataSubjectsController(Service service,
                                               Stage primaryStage)
    {
        this.service=service;
        this.primaryStage=primaryStage;

    }
}
