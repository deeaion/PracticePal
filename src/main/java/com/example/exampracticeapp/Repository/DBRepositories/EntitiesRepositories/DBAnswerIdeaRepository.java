package com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories;

import com.example.exampracticeapp.Domain.AnswerIdea;
import com.example.exampracticeapp.Domain.Chapter;
import com.example.exampracticeapp.Domain.Enum.Difficulty;
import com.example.exampracticeapp.Domain.Enum.QuestionType;
import com.example.exampracticeapp.Domain.Question;
import com.example.exampracticeapp.Domain.Subject;
import com.example.exampracticeapp.Domain.Validators.Validator;
import com.example.exampracticeapp.Exception.RepositoryException;
import com.example.exampracticeapp.Repository.DBRepositories.Util.AbstractDataBaseRepository;
import com.example.exampracticeapp.Repository.DBRepositories.Util.DataBaseAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;

public class DBAnswerIdeaRepository extends AbstractDataBaseRepository<Long, AnswerIdea> {
    public DBAnswerIdeaRepository(Validator validator, DataBaseAccess data, String table) {
        super(validator, data, table);
    }
    private Subject getSubject(ResultSet resultSet) throws SQLException {
        Long id=resultSet.getLong("SubjectId");
        String name=resultSet.getString("SubjectName");
        String description=resultSet.getString("SubjectDescription");
        int year=resultSet.getInt("SubjectYear");
        int semester=resultSet.getInt("SubjectSemester");
        Subject newSubject=new Subject(name,description,year,semester);
        newSubject.setId(id);
        return newSubject;
    }
    private Chapter getChapter(ResultSet resultSet) throws SQLException {
        Long id=resultSet.getLong("ChapterId");
        int ChapterNumber=resultSet.getInt("ChapterNumber");
        String ChapterTitle=resultSet.getString("ChapterTitle");
        String ChapterDifficulty=resultSet.getString("ChapterDifficulty");

        Subject subject=getSubject(resultSet);
        Chapter chapter=new Chapter(subject,ChapterNumber,ChapterTitle, Difficulty.valueOf(ChapterDifficulty.toUpperCase()));
        chapter.setId(id);
        return chapter;
    }
    private Question getQuestion(ResultSet resultSet) throws SQLException {
        Long id=resultSet.getLong("questionid");
        String title=resultSet.getString("QuestionTitle");
        String type=resultSet.getString("QuestionType");
        Chapter chapter=getChapter(resultSet);
        Question question=new Question(chapter,title, QuestionType.valueOf(type.toUpperCase()));
        question.setId(id);
        return question;

    }
    private AnswerIdea getAnswerIdea(ResultSet resultSet) throws SQLException {
        Long id=resultSet.getLong("answerideaid");
        String idea=resultSet.getString("idea");
        Question question=getQuestion(resultSet);
        AnswerIdea answerIdea=new AnswerIdea(idea,question);
        answerIdea.setId(id);
        return answerIdea;
    }
    @Override
    public Optional<AnswerIdea> findOne(Long id) {
        if (id==null)
        {
            throw new IllegalArgumentException("Id provided is null!\n");
        }
        String findStatement="select * from get_all_answer_ideas() where AnswerIdeaId= ?";
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                AnswerIdea answerIdea=getAnswerIdea(resultSet);
                data.closeConnection();
                return Optional.of(answerIdea);
            }
            data.closeConnection();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<AnswerIdea> findAll() {
        String findStatement="select * from get_all_answer_ideas()";
        HashSet<AnswerIdea> answerIdeas=new HashSet<>();
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next())
            {
                AnswerIdea answerIdea=getAnswerIdea(resultSet);
                data.closeConnection();
                answerIdeas.add(answerIdea);
            }
            data.closeConnection();
            return answerIdeas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<AnswerIdea> save(AnswerIdea entity) {
        String insertSQL=
                "INSERT INTO AnswerIdea(AnswerIdeaId,Idea) values (?,?)";
        try {
            PreparedStatement statement= data.createStatement(insertSQL);
            statement.setLong(1,entity.getId());
            statement.setString(2,entity.getIdea());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.of(entity):Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<AnswerIdea> delete(Long id) {
        if(id==null) {
            throw new IllegalArgumentException("Id cannot be null!\n");
        }
        Optional<AnswerIdea> answerIdea=findOne(id);
        if(answerIdea.isEmpty())
        {
            throw new RepositoryException("AnswerIdea does not exist with this certain id "+id+"!\n" );
        }
        try {
            String deleteStatement="DELETE FROM AnswerIdea where AnswerIdeaId= ?";
            PreparedStatement statement= data.createStatement(deleteStatement);
            statement.setLong(1,id);
            int response= statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.empty() : answerIdea;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<AnswerIdea> update(AnswerIdea entity) {
        if (entity==null)
        {
            throw new RuntimeException("AnswerIdea must not be null!");
        }
        //validator.vaildate(entity);
        String updateStatement="UPDATE AnswerIdea set Idea=? where AnswerIdeaId=?";
        try {
            PreparedStatement statement= data.createStatement(updateStatement);
            statement.setLong(2,entity.getId());
            statement.setString(1,entity.getIdea());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
