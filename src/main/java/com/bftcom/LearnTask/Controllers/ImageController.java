package com.bftcom.LearnTask.Controllers;

import com.bftcom.LearnTask.Repo.booksrepository;
import com.bftcom.LearnTask.models.books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Optional;

@RestController
public class ImageController {
    @Autowired
    private booksrepository booksrep;

    @GetMapping("/{id}/readimg")
    public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Long id) throws SQLException {

        Optional<books> bdimg = booksrep.findById(id);
        byte[] imageBytes = null;
        if (bdimg.isPresent()) {
            imageBytes = bdimg.get().getImg();
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<String> download(@PathVariable(value = "id") long id, Model model) {
        Optional<books> dbbook = booksrep.findById(id);
        String bk="Автор: " + dbbook.get().getAuthor() + "\nНазвание:"+dbbook.get().getTitle()+"\n"+dbbook.get().getText();
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(bk);
    }
}