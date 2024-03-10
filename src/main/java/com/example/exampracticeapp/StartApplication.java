package com.example.exampracticeapp;

import com.example.exampracticeapp.Controller.MainController;
import com.example.exampracticeapp.Domain.Validators.ValidatorFactory;
import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Repository.DBRepositories.Factory.DataBaseRepositoryStrategy;
import com.example.exampracticeapp.Repository.DBRepositories.Factory.DatabaseRepoFactory;
import com.example.exampracticeapp.Repository.DBRepositories.Util.DataBaseAccess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class StartApplication extends Application {
    private DataBaseAccess data;
    //setting the database
    private void setDataBase()
    {
        String url="jdbc:postgresql://localhost:5432/ExamPracticeApplication";
        String username="postgres";
        String password="hello";
        this.data=new DataBaseAccess(url,password,username);
        try {
            data.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //setting all repositories
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private DBTestResultsRepository testResultsRepository;
    private void setDBRepositories(){
        DatabaseRepoFactory repoFactory=new DatabaseRepoFactory(data);
        setValidators();
        this.subjectRepository= (DBSubjectRepository) repoFactory.createRepository(DataBaseRepositoryStrategy.SUBJECT,null);
        this.chapterRepository= (DBChapterRepository) repoFactory.createRepository(DataBaseRepositoryStrategy.CHAPTER,null);
        this.questionRepository= (DBQuestionRepository) repoFactory.createRepository(DataBaseRepositoryStrategy.QUESTION,null);
        this.answerRepository= (DBAnswerRepository) repoFactory.createRepository(DataBaseRepositoryStrategy.ANSWER,null);
        this.answerIdeaRepository= (DBAnswerIdeaRepository) repoFactory.createRepository(DataBaseRepositoryStrategy.ANSWERIDEA,null);
        this.testAnswerRepository=new DBTestAnswerRepository(null,data,"testAnswer");
        this.testResultsRepository=new DBTestResultsRepository(null,data,"testResults");

    }

    private void setValidators() {
        ValidatorFactory factory=new ValidatorFactory();
    }

    private void testRepositories()
    {
        subjectRepository.findAll().forEach(System.out::println);
        chapterRepository.findAll().forEach(System.out::println);
        questionRepository.findAll().forEach(System.out::println);
        answerRepository.findAll().forEach(System.out::println);
        answerIdeaRepository.findAll().forEach(System.out::println);
    }
    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/main-view.fxml"));
        AnchorPane setLayout=stageLoader.load();
        primaryStage.setTitle("Exam Practice Application");
        Scene scene = new Scene(setLayout);
        primaryStage.setScene(scene);
        //loading the fxml file
        MainController mainViewController=stageLoader.getController();
        mainViewController.setMainViewController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);
    }
    public void start(Stage primaryStage) throws Exception {
        setDataBase();
        setDBRepositories();
        testRepositories();
        initView(primaryStage);
        primaryStage.show();
}

    public static void main(String[] args)
    {
        launch(args);
    }

}
