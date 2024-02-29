package com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories;

import com.example.exampracticeapp.Domain.Answer;
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

public class DBAnswerRepository extends AbstractDataBaseRepository<Long, Answer> {
    public DBAnswerRepository(Validator validator, DataBaseAccess data, String table) {
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
        String ChapterDifficulty=resultSet.getString("ChapterDifficulty").toUpperCase();

        Subject subject=getSubject(resultSet);
        Chapter chapter=new Chapter(subject,ChapterNumber,ChapterTitle, Difficulty.valueOf(ChapterDifficulty));
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
    private Answer getAnswer(ResultSet resultSet) throws SQLException {
        Long id= resultSet.getLong("answerid");
        String answ=resultSet.getString("AnswerForQuestion");
        Question question=getQuestion(resultSet);
        Answer answer=new Answer(answ,question);
        answer.setId(id);
        return answer;
    }
    @Override
    public Optional<Answer> findOne(Long id) {
        if (id==null)
        {
            throw new IllegalArgumentException("Id provided is null!\n");
        }
        String findStatement="select * from get_all_answers() where AnswerId= ?";
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                Answer answer=getAnswer(resultSet);
                data.closeConnection();
                return Optional.of(answer);
            }
            data.closeConnection();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Answer> findAll() {
        String findStatement="select * from get_all_answers()";
        HashSet<Answer> answers=new HashSet<>();
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next())
            {
                Answer answer=getAnswer(resultSet);
                data.closeConnection();
                answers.add(answer);
            }
            data.closeConnection();
            return answers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Answer> save(Answer entity) {
        String insertSQL=
                "INSERT INTO Answer(AnswerId,AnswerForQuestion) values (?,?)";
        try {
            PreparedStatement statement= data.createStatement(insertSQL);
            statement.setLong(1,entity.getId());
            statement.setString(2,entity.getAnswerForQuestion());

            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.of(entity):Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Answer> delete(Long id) {
        if(id==null) {
            throw new IllegalArgumentException("Id cannot be null!\n");
        }
        Optional<Answer> answer=findOne(id);
        if(answer.isEmpty())
        {
            throw new RepositoryException("Answer does not exist with this certain id "+id+"!\n" );
        }
        try {
            String deleteStatement="DELETE FROM Answer where AnswerId= ?";
            PreparedStatement statement= data.createStatement(deleteStatement);
            statement.setLong(1,id);
            int response= statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.empty() : answer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Answer> update(Answer entity) {
        if (entity==null)
        {
            throw new RuntimeException("Answer must not be null!");
        }
        //validator.vaildate(entity);
        String updateStatement="UPDATE Answer set AnswerForQuestion=? where AnswerId=?";
        try {
            PreparedStatement statement= data.createStatement(updateStatement);
            statement.setString(1,entity.getAnswerForQuestion());
            statement.setLong(2,entity.getId());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
