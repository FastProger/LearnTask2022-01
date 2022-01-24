package com.bftcom.LearnTask.Repo;

import com.bftcom.LearnTask.models.books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface booksrepository  extends CrudRepository<books, Long> {

    @Query("select new books(id, title, author, genre, text) "
            + "from books "
            + "where lower(title) like lower('%' || :title ||'%')"
            + "and (author=:author or '-'=:author) "
            + "and (' ' || genre || ' ' like '% ' || :genre ||' %' or '-'=:genre) "
            + "and lower(text) like lower('%' || :text ||'%')")
    List<books> findByCond(@Param("title") String searchtitle,@Param("author") String searchauthor, @Param("genre") String searchgenre, @Param("text") String searchtext);

    @Query("select new books(id, title, author, genre, text) "
            + "from books order by random()")
    List<books> findByRND();


}

