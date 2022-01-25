package com.bftcom.LearnTask.services;

import com.bftcom.LearnTask.models.books;
import com.bftcom.LearnTask.Repo.booksrepository;
import com.bftcom.LearnTask.utils.HibernateSessionFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    booksrepository booksrepo;

    private books bks = new books();

    public BooksService() {
    }

    public books findBookByID(Long id) {
        bks=HibernateSessionFactoryUtil.getSessionFactory().openSession().get(books.class, id);
        return bks;
    }

}
