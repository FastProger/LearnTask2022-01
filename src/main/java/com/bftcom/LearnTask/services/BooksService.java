package com.bftcom.LearnTask.services;

import com.bftcom.LearnTask.models.books;
import com.bftcom.LearnTask.Repo.booksrepository;
import com.bftcom.LearnTask.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksService {

    @Autowired
    booksrepository booksrepo;

    private books bks = new books();

    public BooksService() {
    }

    public List<books>  getrandombooks() {
        List<books> b=new ArrayList<>();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        b=session.createSQLQuery("select id, title, author, genre, text, img "
                + "from books order by random() limit 10").addEntity("books",books.class).list();
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
