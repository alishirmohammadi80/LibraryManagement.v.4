package com.rasha.libraryManagement.book;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.lowagie.text.DocumentException;
import com.rasha.libraryManagement.login.User;
import com.rasha.libraryManagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    EmailService emailService;

    @GetMapping("/book")
    public String listBook(Model model) {
        model.addAttribute("book", bookService.getAllBooks());

        return "book";
    }

    @GetMapping("/book/new")
    public String addBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "add_book";

    }

    @PostMapping("/book")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/book";
    }

    @GetMapping("/book/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "edite_book";
    }

    @PostMapping("/book/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        Book editebook = bookService.getBookById(id);
        editebook.setId(id);
        editebook.setBookName(book.getBookName());
        editebook.setNumberOfPage(book.getNumberOfPage());
        editebook.setAuthorName(book.getAuthorName());
        editebook.setPublisher(book.getPublisher());
        bookService.editeBook(editebook);
        return "redirect:/book";

    }

    @GetMapping("/book/{id}")
    public String delitBook(@PathVariable Long id) {
        bookService.delitBook(id);
        return "redirect:/book";
    }
@Autowired
BookService service;
    @Autowired
    BookRepository repository;
    @GetMapping("/book/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Books_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Book> listbooks = service.getAllBooks();

       BookPDFExporter exporter = new BookPDFExporter(listbooks);
        exporter.export(response);

    }

}
