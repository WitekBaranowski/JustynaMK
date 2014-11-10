package pl.digitaldream.justynamk.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class HaProxyCheck {

    @RequestMapping(value = "/health", method = RequestMethod.HEAD)
    @ResponseStatus(value = HttpStatus.OK)
    public void health(){}
	

}
