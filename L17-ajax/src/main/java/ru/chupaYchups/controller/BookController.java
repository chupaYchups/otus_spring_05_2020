package ru.chupaYchups.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BookController {

    @GetMapping({"/"})
    public String listPage() {
        return "bookList";
    }

    @GetMapping("/edit")
    public String editPage() {
        return "bookEdit";
    }
}
