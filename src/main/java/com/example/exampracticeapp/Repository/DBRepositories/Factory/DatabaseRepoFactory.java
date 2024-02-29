package com.example.exampracticeapp.Repository.DBRepositories.Factory;


//import com.example.map_socialnetworkvt.Repository.DataBaseRepository.Util.ReplyMessageDatabaseRepository;


import com.example.exampracticeapp.Domain.Validators.Validator;
import com.example.exampracticeapp.Exception.RepositoryException;
import com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories.*;
import com.example.exampracticeapp.Repository.DBRepositories.Util.AbstractDataBaseRepository;
import com.example.exampracticeapp.Repository.DBRepositories.Util.DataBaseAccess;

public class DatabaseRepoFactory implements DataBaseFactory {
    private final DataBaseAccess data;

    public DatabaseRepoFactory(DataBaseAccess data) {
        this.data = data;
    }

    @Override
    public AbstractDataBaseRepository createRepository(DataBaseRepositoryStrategy strategy, Validator validator) throws RepositoryException {
        switch (strategy) {
            case SUBJECT -> {return new DBSubjectRepository(validator, data, "subject");}
            case CHAPTER -> {
                return new DBChapterRepository(validator, data, "chapter");}
            case ANSWER -> {return new DBAnswerRepository(validator, data, "answer");}
            case QUESTION -> {return new DBQuestionRepository(validator, data, "question");}
            case ANSWERIDEA -> {return new DBAnswerIdeaRepository(validator, data, "answerIdea");}
            default -> throw new RepositoryException("Invalid Strategy");
        }
    }
}



