package pl.digitaldream.justynamk.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Homepage {

    @RequestMapping("/test")
    String index() {
        return "error";
    }
	

}
