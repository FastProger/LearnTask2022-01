package com.bftcom.LearnTask.services;

import com.bftcom.LearnTask.Config.SpringJdbcConfig;
import com.bftcom.LearnTask.models.books;
import com.bftcom.LearnTask.Repo.booksrepository;
import com.bftcom.LearnTask.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BooksService {

    @Autowired
    booksrepository booksrepo;

    private books bks = new books();

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.getDataSource());;

    public List<books> getallbooks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<books> b=session.createSQLQuery("select id, title, author, genre, text, img from books").addEntity("books",books.class).list();
        session.close();
        return b;
    }

    public List<books>  getrandombooks() {
        List<books> b = jdbcTemplate.query("select id, title, author, genre, text, img from books order by random() limit 10",booksrepository.ROW_MAPPER);
        return b;
    }

    public List<books> getsearchedbooks(String searchtitle,String searchauthor,String searchgenre,String searchtext){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String sql="select id, title, author, genre, text, img from books where lower(text) like lower('%"+searchtext+"%')";
        if (!searchtitle.equals("")) sql=sql+" and lower(title) like lower('%" +searchtitle + "%')";
        if (!searchauthor.equals("-")) sql=sql+" and author='"+ searchauthor+"'";
        if (!searchgenre.equals("-")) sql=sql+" and  ' ' || genre || ' ' like '% "+ searchgenre +" %'";

        List<books> b=session.createSQLQuery(sql).addEntity("books",books.class).list();
        session.close();
        return b;
    }

    public books findBookByID(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(books.class, id);
    }

    public static void addBook(books book) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(book);
        tx1.commit();
        session.close();
    }

    public static void deleteBookById(Long id)
    {   books b = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(books.class, id);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(b);
        tx1.commit();
        session.close();
    }
    public static void updateBook(books b)
    {   Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.update(b);
        tx1.commit();
        session.close();
    }

}
