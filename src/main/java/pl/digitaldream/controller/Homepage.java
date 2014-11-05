package pl.digitaldream.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Homepage {

    @RequestMapping("/")
    String index() {
        return "index";
    }
	

}
