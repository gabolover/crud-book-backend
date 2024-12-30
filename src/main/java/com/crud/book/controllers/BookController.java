package com.crud.book.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crud.book.models.BookModel;
import com.crud.book.services.BookService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://127.0.0.1:5500")  
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public ArrayList<BookModel> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Optional<BookModel> getBook(@PathVariable Long id) {
       return bookService.getBook(id);
    }

    @PostMapping("/create")
    public BookModel addBookWithImage(@RequestParam String name,
                                                      @RequestParam int pagesQuantity,
                                                      @RequestParam String author,
                                                      @RequestParam MultipartFile image) throws IOException {
        // Crear el libro
        BookModel book = new BookModel();
        book.setName(name);
        book.setPagesQuantity(pagesQuantity);
        book.setAuthor(author);

        // Guardar el libro con la imagen en Base64
        BookModel savedBook = bookService.createBook(book, image);

        return savedBook;
    }

}
