package pl.digitaldream.justynamk.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.digitaldream.justynamk.domain.User;
import pl.digitaldream.justynamk.repository.UserRepository;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = GET, value = "{login}")
    public ResponseEntity<User> get(@PathVariable String login) {
        return new ResponseEntity<>(userRepository.getOne(login), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        user = userRepository.save(user);
        return new ResponseEntity<>(user, CREATED);
    }

    @RequestMapping(method = DELETE, value = "{login}")
    public ResponseEntity<String> delete(@PathVariable String login) {

        userRepository.delete(login);
        return new ResponseEntity<>(String.format("%s has been deleted", login), OK);
    }

}