package com.example.exampracticeapp.Controller;

import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ImportDataController {
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    private Service service;


    public void setImportDataController(Service service,
                                               Stage primaryStage)
    {
        this.service=service;
        this.primaryStage=primaryStage;

    }
    @FXML
    private Button btn_ChooseFile;

    @FXML
    private Button btn_ShowExample;

    @FXML
    private Button btn_import;

    @FXML
    private TextArea txt_ToInsert;

    @FXML
    private TextField txt_chapter;

    @FXML
    private TextField txt_subject;

    @FXML
    void handleImport(ActionEvent event) {

    }

    @FXML
    void handleShowExample(ActionEvent event) {

    }

    @FXML
    void handleUploadingFile(ActionEvent event) {

    }
}
