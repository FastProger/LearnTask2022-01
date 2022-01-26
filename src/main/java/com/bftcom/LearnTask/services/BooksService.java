package com.bftcom.LearnTask.services;

import com.bftcom.LearnTask.Config.SpringJdbcConfig;
import com.bftcom.LearnTask.models.books;
import com.bftcom.LearnTask.Repo.booksrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BooksService {

    @Autowired
    booksrepository booksrepo;

    private books bks = new books();

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.getDataSource());;

    public List<books> getallbooks() {
        List<books> b = jdbcTemplate.query("select id, title, author, genre, text, img from books",booksrepository.ROW_MAPPER);
        return b;
    }

    public List<books>  getrandombooks() {
        List<books> b = jdbcTemplate.query("select id, title, author, genre, text, img from books order by random() limit 10",booksrepository.ROW_MAPPER);
        return b;
    }

    public List<books> getsearchedbooks(String searchtitle,String searchauthor,String searchgenre,String searchtext){
        String sql="select id, title, author, genre, text, img from books where lower(text) like lower('%"+searchtext+"%')";
        if (!searchtitle.equals("")) sql=sql+" and lower(title) like lower('%" +searchtitle + "%')";
        if (!searchauthor.equals("-")) sql=sql+" and author='"+ searchauthor+"'";
        if (!searchgenre.equals("-")) sql=sql+" and  ' ' || genre || ' ' like '% "+ searchgenre +" %'";

        List<books> b = jdbcTemplate.query(sql,booksrepository.ROW_MAPPER);
        return b;
    }

    public books findBookByID(Long id) {
        return jdbcTemplate.queryForObject("select id, title, author, genre, text, img from books where id="+id.toString(),booksrepository.ROW_MAPPER);
    }
    public void updateBook(books book)
    {
        jdbcTemplate.update("update books set title=?, author=?, genre=?, text=?, img=? where id=?",book.getTitle(),book.getAuthor(),book.getGenre(),book.getText(),book.getImg(), book.getId());
    }

    public void addBook(books book) {
        jdbcTemplate.update("insert into books (title, author, genre, text, img) values(?, ?, ?, ?, ?)",book.getTitle(),book.getAuthor(),book.getGenre(),book.getText(),book.getImg());
    }

    public void deleteBookById(Long id) {
        jdbcTemplate.update("delete from books where id= ?",id);
    }


}
