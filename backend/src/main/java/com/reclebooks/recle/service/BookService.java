package com.reclebooks.recle.service;

import com.reclebooks.recle.dto.bookdto.SearchBookDto;
import com.reclebooks.recle.dto.bookdto.SearchBookDtoByIsbn;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BookService {

    public SearchBookDto searchBook(String keyword);

    public SearchBookDtoByIsbn searchBookByIsbn(String isbn);

}
