package com.iware.lottery.admin.api;

import com.iware.lottery.admin.Constants;
import com.iware.lottery.admin.auth.AuthValidate;
import com.iware.lottery.admin.exception.InvalidRequestException;
import com.iware.lottery.admin.model.LoginForm;
import com.iware.lottery.admin.model.ResponseMessage;
import com.iware.lottery.admin.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by johnma on 2016/11/10.
 */
@RestController
@RequestMapping (value = Constants.URI_API + Constants.URI_IDENTITY)
public class IdentityController {
    private static final Logger logger = LoggerFactory.getLogger(IdentityController.class);

    private UserService userService;

    @Inject
    IdentityController(UserService userService){
        this.userService = userService;
    }

    @ApiOperation(value = "login")
    @RequestMapping(method = RequestMethod.POST, value = "")
    @ResponseBody
    public ResponseEntity<ResponseMessage> login(@RequestBody @Valid LoginForm form, BindingResult Result){
        if (Result.hasErrors()) {
            throw new InvalidRequestException(Result);
        }

        UsernamePasswordToken token = new UsernamePasswordToken(form.getName(), form.getPassword());

        // Remember Me built-in, just do this
        // TODO: Make this a user option instead of hard coded in.
        //token.setRememberMe(true);

        Subject currentUser = SecurityUtils.getSubject();
        try{
            currentUser.login(token);
        }
        catch (UnknownAccountException uae ) {
            //username wasn't in the system, show them an error message?
            System.out.println("the user name is invalid");
        } catch ( IncorrectCredentialsException ice ) {
            //password didn't match, try again?
            System.out.println("the password is invalid");
        } catch ( LockedAccountException lae ) {
            //account for that username is locked - can't login.  Show them a message?
            System.out.println("the user account is locked");
        }catch ( AuthenticationException ae ) {
            //unexpected condition - error?
            System.out.println("unexpect error"+ae);
        }

        //将用户token写入redis

        return new ResponseEntity<>(ResponseMessage.success("user.login"), HttpStatus.OK);
    }

    @ApiOperation(value = "logout")
    @AuthValidate
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> logout(@PathVariable("id") Long id){
        //将用户token从redis中删除
        return new ResponseEntity<>(ResponseMessage.success("user.logout"), HttpStatus.OK);
    }
}
