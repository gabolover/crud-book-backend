package com.crud.book.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.book.models.BookModel;
import com.crud.book.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public ArrayList<BookModel> getBooks() {
        return (ArrayList<BookModel>) bookRepository.findAll();
    }

    public Optional<BookModel> getBook(Long id) {
        return  bookRepository.findById(id);
    }
    

    public BookModel createBook(BookModel book, MultipartFile imageFile) throws IOException {
        String base64Image = encodeImageToBase64(imageFile);
        book.setImage(base64Image);
        return bookRepository.save(book);
    }

    // Método para convertir la imagen a Base64
    private String encodeImageToBase64(MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
