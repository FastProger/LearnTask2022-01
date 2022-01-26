package com.bftcom.LearnTask.Repo;

import com.bftcom.LearnTask.models.books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public interface booksrepository  extends CrudRepository<books, Long> {
    RowMapper<books> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new books(resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("genre"),
                resultSet.getString("text"),
                resultSet.getBytes("img")
        );
        //id, title, author, genre, text, img
    };
}

