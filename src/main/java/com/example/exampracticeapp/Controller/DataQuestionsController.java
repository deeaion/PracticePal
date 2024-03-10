package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DataQuestionsController {

    @FXML
    private Button btn_Search;

    @FXML
    private Label img_add;

    @FXML
    private ImageView img_addAnswer;

    @FXML
    private ImageView img_addHint;

    @FXML
    private ImageView lbl_addQuestion;

    @FXML
    private Label lbl_answer;

    @FXML
    private ImageView lbl_deleteQuestion;

    @FXML
    private Label lbl_hint;

    @FXML
    private TableColumn<?, ?> tblCol_question;

    @FXML
    private TableColumn<?, ?> tblCol_type;

    @FXML
    private TableView<?> tbl_questions;

    @FXML
    private TextField txt_chapter;

    @FXML
    private TextField txt_subject;
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    public void setDataQuestionsController(DBSubjectRepository subjectRepository, DBChapterRepository chapterRepository,
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
