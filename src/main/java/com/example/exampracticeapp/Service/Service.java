package com.example.exampracticeapp.Service;//package com.example.exampracticeapplication.Service;


import com.example.exampracticeapp.Exception.ServiceException;
import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Repository.Repository;
import com.example.exampracticeapp.Utils.ChangeEvent;
import com.example.exampracticeapp.Utils.ChangeEventType;
import com.example.exampracticeapp.Utils.Observer.Observable;
import com.example.exampracticeapp.Utils.Observer.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class Service implements Observable<ChangeEvent> {
    private Repository subjectRepository;
    private Repository chapterRepository;
    private Repository questionRepository;
    private Repository answerRepository;
    private Repository testAnswerRepository;
    private Repository testResultsRepository;

    public Service(Repository subjectRepository, Repository chapterRepository, Repository questionRepository, Repository answerRepository, Repository answerIdeaRepository
    ,Repository testAnswerRepository,Repository testResultsRepository) {
        this.subjectRepository = subjectRepository;
        this.chapterRepository = chapterRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.answerIdeaRepository = answerIdeaRepository;
        this.testAnswerRepository=testAnswerRepository;
        this.testResultsRepository=testResultsRepository;
    }

    private Repository answerIdeaRepository;
    @Override
    public void addObserver(Observer<ChangeEvent> e) {

    }

    @Override
    public void removeObserver(Observer<ChangeEvent> e) {

    }

    @Override
    public void notifyObservers(ChangeEvent t) {

    }
    private List<Observer<ChangeEvent>> observers=new ArrayList<>();


}
