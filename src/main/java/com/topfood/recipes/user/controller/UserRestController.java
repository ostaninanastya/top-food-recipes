package com.topfood.recipes.user.controller;

import com.topfood.recipes.user.model.User;
import com.topfood.recipes.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all users", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by id", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@ApiParam(value = "User id", required = true) @PathVariable String id) {
    	User user = userService.getByID(id);
    	return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a user", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(method = POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	userService.add(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a user", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
    	userService.update(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a user", produces = APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<User> deleteUser(@ApiParam(value = "User id", required = true) @PathVariable String id) {
    	userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

}
