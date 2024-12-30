package com.crud.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.book.models.BookModel;

@Repository
public interface BookRepository extends JpaRepository <BookModel, Long> {

}
