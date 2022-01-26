package com.bftcom.LearnTask.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

@Entity
public class books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title, author, genre, text;

    private byte[] img;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImg(MultipartFile img)  throws IOException {
        byte[] byteArr = img.getBytes();
        this.img = byteArr;
    }

    public books() {
    }

    public books(Long id, String title, String author, String genre, String text, byte[] img)  {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.text = text;
        this.img=img;
    }

    public books(Long id, String title, String author, String genre, String text)  {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.text = text;
    }

    public books(String title, String author, String genre, String text, MultipartFile img) throws IOException {
        byte[] byteArr = img.getBytes();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.text = text;
        this.img = byteArr;
    }

    public books(String title, String author, String genre, String text) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.text = text;
    }

}
