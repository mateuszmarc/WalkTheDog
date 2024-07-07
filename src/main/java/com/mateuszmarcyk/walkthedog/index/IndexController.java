package com.mateuszmarcyk.walkthedog.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/walkthedog")
public class IndexController {

    @GetMapping
    public String displayIndexPage() {
        return "index";
    }
}
