package com.mateuszmarcyk.walkthedog.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String displayIndexPage() {
        return "index";
    }

    @GetMapping("/about")
    public String displayAboutPage() {
        return "about";
    }

    @GetMapping("/contat")
    public String displayContactPage() {
        return "contact";
    }

}
