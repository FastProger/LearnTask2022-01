package com.bftcom.LearnTask.Controllers;

import com.bftcom.LearnTask.models.books;
import com.bftcom.LearnTask.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ImageController {
    @Autowired
    BooksService booksservice;

    @GetMapping("/{id}/readimg")
    public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Long id) throws SQLException {

        books b= booksservice.findBookByID(id);
        byte[] imageBytes = null;
        imageBytes = b.getImg();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<String> download(@PathVariable(value = "id") long id, Model model) {
        books b= booksservice.findBookByID(id);
        String bk="Автор: " + b.getAuthor() + "\nНазвание:"+b.getTitle()+"\n"+b.getText();
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(bk);
    }
}