package com.iware.lottery.admin.api;

import com.iware.lottery.admin.auth.AuthValidate;
import com.iware.lottery.admin.model.*;
import com.iware.lottery.admin.Constants;
import com.iware.lottery.admin.exception.InvalidRequestException;
import com.iware.lottery.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by johnma on 2016/11/2.
 */
@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_USERS)
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value ="")
    @ResponseBody
    public ResponseEntity<ResponseMessage>  create(@RequestBody @Valid RegistrationForm form, BindingResult errResult) {
        logger.info("create a new user");

        if (errResult.hasErrors()) {

            throw new InvalidRequestException(errResult);
        }

        UserDetails saved = userService.saveUser(form);

        logger.debug("saved user id is @" + saved.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(Constants.URI_API + Constants.URI_USERS + "/{id}")
                .buildAndExpand(saved.getId())
                .toUri()
        );

        return new ResponseEntity<>(ResponseMessage.success("user.created"), headers, HttpStatus.OK);
    }

    //@AuthValidate
    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseBody
    public ResponseEntity<Page<UserDetails>> getAllUsers(
            @RequestParam(value = "q", required = false) String keyword, //
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable page ){
        logger.debug("get all users of q@" + keyword  + ", page@" + page);

        Page<UserDetails> users = userService.searchUsersByCriteria(keyword, page);

        logger.debug("get users size @" + users.getTotalElements());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @AuthValidate
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public ResponseEntity<UserDetails> getUser(@PathVariable("id") Long id){
        logger.debug("get user's info by id @" + id);

        UserDetails user = userService.findUserById(id);

        logger.debug("get user @" + user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
/*
    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    @ResponseBody
    public ResponseEntity<UserDetails> getUser(@PathVariable("name") String name){
        logger.debug("get user's info by name @" + name);

        UserDetails user = userService.findUserByName(name);

        logger.debug("get user @" + user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
*/
    @AuthValidate
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable("id") Long id,@RequestBody @Valid UserForm form, BindingResult errResult){
        logger.info("update user's info by id@" + id);

        if (errResult.hasErrors()) {

            throw new InvalidRequestException(errResult);
        }

        form.setId(id);

        UserDetails saved = userService.updateUser(form);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path(Constants.URI_API + Constants.URI_USERS + "/{id}")
                        .buildAndExpand(saved.getId())
                        .toUri()
        );

        return new ResponseEntity<>(ResponseMessage.success("user.updated"), headers, HttpStatus.OK);
    }

    @AuthValidate
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable("id") Long id){
        logger.info("delete user by id@" + id);

        userService.deleteUserById(id);

        return new ResponseEntity<>(ResponseMessage.success("user.deleted"), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseBody
    public ResponseEntity<UserDetails>  login(@RequestBody @Valid LoginForm form, BindingResult errResult){
        if (errResult.hasErrors()) {
            throw new InvalidRequestException(errResult);
        }

        logger.info("login user by name@" + form.getName());
        logger.info("login user by password@" + form.getPassword());
        UserDetails user = userService.login(form.getName(), form.getPassword());

        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
