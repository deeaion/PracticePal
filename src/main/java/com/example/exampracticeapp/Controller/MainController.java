package com.example.exampracticeapp.Controller;

        import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.Button;
        import javafx.scene.control.MenuItem;
        import javafx.scene.control.SplitMenuButton;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.BorderPane;
        import javafx.stage.Stage;

        import java.io.IOException;

public class MainController {
    private DBSubjectRepository subjectRepository;
    private DBChapterRepository chapterRepository;
    private DBQuestionRepository questionRepository;
    private DBAnswerRepository answerRepository;
    private DBAnswerIdeaRepository answerIdeaRepository;
    private DBTestResultsRepository testResultsRepository;
    private DBTestAnswerRepository testAnswerRepository;
    private Stage primaryStage;
    public void setMainViewController(DBSubjectRepository subjectRepository, DBChapterRepository chapterRepository,
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
        handleSwitchToHome(new ActionEvent());
    }
    @FXML
    private SplitMenuButton btn_data;

    @FXML
    private MenuItem btn_data_previous;

    @FXML
    private MenuItem btn_data_question;

    @FXML
    private MenuItem btn_data_subject;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_import_data;

    @FXML
    private Button btn_practice;

    @FXML
    private Button btn_randomize;

    @FXML
    private Button btn_settings;

    @FXML
    private ImageView img_closeApp;

    @FXML
    private ImageView img_settings;

    @FXML
    void handleClose(MouseEvent event) {
        primaryStage.close();

    }
    @FXML
    private BorderPane borderPane;
    @FXML
    void handleSwitchToHome(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/welcome-view.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        WelcomeController welcomeController=stageLoader.getController();
        welcomeController.setWelcomeController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);
    }

    @FXML
    void handleSwitchToImportData(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/importData-view.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        ImportDataController importDataController=stageLoader.getController();
        importDataController.setImportDataController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);
    }

    @FXML
    void handleSwitchToPractice(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/practiceTests-view.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        ImportDataController importDataController=stageLoader.getController();
        importDataController.setImportDataController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);
    }

    @FXML
    void handleSwitchToQuestionData(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/data-view-questions.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        DataQuestionsController dataQuestionsController=stageLoader.getController();
        dataQuestionsController.setDataQuestionsController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);

    }
    @FXML
    void handleSwitchToSubjectData(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/data-view-subjects.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        DataSubjectsController dataSubjectsController=stageLoader.getController();
        dataSubjectsController.setDataSubjectsController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);

    }


    @FXML
    void handleSwitchToRandomize(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/randomize-view.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        RandomizeController randomizeController=stageLoader.getController();
        randomizeController.setRandomizeMenuController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);

    }

    @FXML
    void handleSwitchToSettings(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/settings-view.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        SettingsController settingsController=stageLoader.getController();
        settingsController.setSettingsController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);

    }

    @FXML
    void handleSwitchToTestsData(ActionEvent event) {
        FXMLLoader stageLoader=new FXMLLoader();
        stageLoader.setLocation(getClass().getResource("/com/example/exampracticeapp/fxml-files/data-view-tests.fxml"));
        AnchorPane view= null;
        try {
            view = stageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        borderPane.setCenter(view);
        DataPracticeTestsController dataPracticeTestsController=stageLoader.getController();
        dataPracticeTestsController.setDataPracticeTestsController(subjectRepository,chapterRepository,questionRepository,answerRepository,answerIdeaRepository,testAnswerRepository,testResultsRepository,primaryStage);

    }

}
