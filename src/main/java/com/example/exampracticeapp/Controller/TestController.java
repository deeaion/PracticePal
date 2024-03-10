package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TestController {

    @FXML
    private Button btnSetAsRight;

    @FXML
    private Button btnSetAsWrong;

    @FXML
    private Button btnShowAnswer;

    @FXML
    private Button btn_CheckAnswer;

    @FXML
    private Button btn_EndTest;

    @FXML
    private TitledPane dropDown_AnswerKey;

    @FXML
    private ImageView img_closeApp;

    @FXML
    private Label lbl_ChapterNumber;

    @FXML
    private Label lbl_nrOfQuestions;

    @FXML
    private Label lbl_question;

    @FXML
    private Label lbl_questionNr;

    @FXML
    private Label lbl_questiontype;

    @FXML
    private Label lbl_time_spent;

    @FXML
    private Label lbl_typeOfTest;

    @FXML
    private ListView<?> list_previousQuestions;

    @FXML
    private TextField txt_AnswerField;
    private Service service;

    @FXML
    void handleAnswerKey(MouseEvent event) {

    }

    @FXML
    void handleCheckAnswer(ActionEvent event) {

    }

    @FXML
    void handleClose(MouseEvent event) {

    }

    @FXML
    void handleEndTest(ActionEvent event) {

    }

    @FXML
    void handleSetAsRight(ActionEvent event) {

    }

    @FXML
    void handleSetWrong(ActionEvent event) {

    }

    @FXML
    void handleShowAnswer(ActionEvent event) {

    }
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    public void setTestController(Service service,
                                               Stage primaryStage)
    {
        this.service=service;
        this.primaryStage=primaryStage;

    }

}
