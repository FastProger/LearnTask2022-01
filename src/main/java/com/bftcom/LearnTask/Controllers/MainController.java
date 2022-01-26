package com.bftcom.LearnTask.Controllers;

import com.bftcom.LearnTask.models.books;
import com.bftcom.LearnTask.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    BooksService booksservice;

    @GetMapping("/")
    public String mainpage(Model model) {
        model.addAttribute("books", booksservice.getrandombooks());
        return "index";
    }

    @GetMapping("/search")
    public String searchpageget(Model model) {
        String genre = "-", author = "-", title = "", text = "";
        model = searchpage(title, author, genre, text, model);
        return "search";
    }

    @PostMapping("/search")
    public String searchpagepost(@RequestParam String title, @RequestParam String author, @RequestParam String genre, @RequestParam String text, Model model) {
        model = searchpage(title, author, genre, text, model);
        return "search";
    }

    public Model searchpage(String title, String author, String genre, String text, Model model) {
        List<books> bs = booksservice.getallbooks();
        String s;
        String[] w;
        //получаем полный список известных жанров из БД
        ArrayList<String> genres = new ArrayList<>();
        genres.add("-");
        for (books b : bs) {
            s = b.getGenre();
            w = s.split(" ");
            for (String t : w) {
                if (genres.indexOf(t) == -1) genres.add(t);
            }
        }
        genres.sort(Comparator.naturalOrder());
        model.addAttribute("genres", genres);

        //получаем список авторов
        ArrayList<String> authors = new ArrayList<>();
        authors.add("-");
        for (books b : bs) {
            s = b.getAuthor();
            if (authors.indexOf(s) == -1) authors.add(s);
        }
        authors.sort(Comparator.naturalOrder());
        model.addAttribute("authors", authors);

        model.addAttribute("text", text);
        model.addAttribute("author", author);
        model.addAttribute("genre", genre);
        model.addAttribute("title", title);

        //получаем список книг
        model.addAttribute("books", booksservice.getsearchedbooks(title, author, genre, text));

        return model;
    }

    @GetMapping("/login")
    public String loginpage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String postloginpage(Model model) {
        return "redirect:/";
    }

    @GetMapping("/{id}/read")
    public String readpage(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("books", booksservice.findBookByID(id));
        return "read";
    }


    @GetMapping("/{id}/whanttodelete")
    public String whanttodelete(@PathVariable("id") Long id, Model model) {
        return "whanttodelete";
    }

    @GetMapping("/{id}/delete")
    public String todelete(@PathVariable("id") Long id, Model model) {
        booksservice.deleteBookById(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addpage(Model model) {
        return "add";
    }

    @PostMapping("/add")
    public String doaddpage(@RequestParam String title, @RequestParam String genre, @RequestParam MultipartFile img, @RequestParam String text, Model model) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String author = authentication.getName();
        title = title.trim();
        if (title.equals("")) return "redirect:/";
        else {
            String oldg;
            genre = genre.trim().toLowerCase();
            do { //удаляем все лишние пробелы в списке жанров
                oldg = genre;
                genre = genre.replace("  ", " ");
            } while (!oldg.equals(genre));

            books b;
            if (img.isEmpty()) b = new books(title, author, genre, text);
            else b = new books(title, author, genre, text, img);
            booksservice.addBook(b);
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/edit")
    public String editpage(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("books", booksservice.findBookByID(id));
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String doupdatepage(@RequestParam String title, @RequestParam String genre, @RequestParam MultipartFile img, @RequestParam String text, @PathVariable(value = "id") Long id, Model model) throws IOException {
        title = title.trim();
        if (title.equals("")) return "redirect:/" + id.toString() + "/read";
        else {
            String oldg;
            genre = genre.trim().toLowerCase();
            do { //удаляем все лишние пробелы в списке жанров
                oldg = genre;
                genre = genre.replace("  ", " ");
            } while (!oldg.equals(genre));

            books b = booksservice.findBookByID(id);
            b.setGenre(genre);
            b.setText(text);
            b.setTitle(title);
            if (!img.isEmpty()) b.setImg(img);
            booksservice.updateBook(b);
            return "redirect:/";
        }
    }

}
