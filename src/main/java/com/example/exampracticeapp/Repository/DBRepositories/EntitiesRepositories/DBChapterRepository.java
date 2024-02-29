package com.example.exampracticeapp.Repository.DBRepositories.EntitiesRepositories;

import com.example.exampracticeapp.Domain.Chapter;
import com.example.exampracticeapp.Domain.Enum.Difficulty;
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

public class DBChapterRepository extends AbstractDataBaseRepository<Long, Chapter> {
    public DBChapterRepository(Validator validator, DataBaseAccess data, String table) {
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
    @Override
    public Optional<Chapter> findOne(Long id) {
        if (id==null)
        {
            throw new IllegalArgumentException("Id provided is null!\n");
        }
        String findStatement="SELECT * FROM get_all_chapters() where ChapterId= ?";
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                Chapter chapter=getChapter(resultSet);
                data.closeConnection();
                return Optional.of(chapter);
            }
            data.closeConnection();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Chapter> findAll() {
        String findStatement="SELECT * FROM get_all_chapters()";
        HashSet<Chapter> chapters=new HashSet<>();
        try {
            PreparedStatement statement= data.createStatement(findStatement);
            ResultSet resultSet= statement.executeQuery();
            while (resultSet.next())
            {
                Chapter chapter=getChapter(resultSet);
                data.closeConnection();
                chapters.add(chapter);
            }
            data.closeConnection();
            return chapters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Chapter> save(Chapter entity) {
        String insertSQL="INSERT INTO Chapter(SubjectId,ChapterNumber,ChapterTitle,ChapterDifficulty) values (?,?,?,?)";
        try {
            PreparedStatement statement= data.createStatement(insertSQL);
            statement.setLong(1,entity.getChapterSubject().getId());
            statement.setInt(2,entity.getChapterNumber());
            statement.setString(3,entity.getChapterTitle());
            statement.setString(4,entity.getChapterDifficulty().toString());

            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.of(entity):Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Chapter> delete(Long id) {
        if(id==null) {
            throw new IllegalArgumentException("Id cannot be null!\n");
        }
        Optional<Chapter> chapter=findOne(id);
        if(chapter.isEmpty())
        {
            throw new RepositoryException("Chapter does not exist with this certain id "+id+"!\n" );
        }
        try {
            String deleteStatement="DELETE FROM Chapter where ChapterId= ?";
            PreparedStatement statement= data.createStatement(deleteStatement);
            statement.setLong(1,id);
            int response= statement.executeUpdate();
            data.closeConnection();
            return response==0?Optional.empty() : chapter;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Chapter> update(Chapter entity) {
        if (entity==null)
        {
            throw new RuntimeException("Chapter must not be null!");
        }
        //validator.vaildate(entity);
        String updateStatement="UPDATE Chapter set ChapterNumber=?, ChapterTitle=?,ChapterDifficulty=? where ChapterId=?";
        try {
            PreparedStatement statement= data.createStatement(updateStatement);
            statement.setInt(1,entity.getChapterNumber());
            statement.setString(2,entity.getChapterTitle());
            statement.setString(3,entity.getChapterDifficulty().toString());
            statement.setLong(4,entity.getId());
            int response=statement.executeUpdate();
            data.closeConnection();
            return response==0? Optional.of(entity) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
