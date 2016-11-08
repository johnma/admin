package com.iware.lottery.admin.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iware.lottery.admin.common.DateKit;
import com.iware.lottery.admin.common.OutKit;
import com.iware.lottery.admin.model.ResponseMessage;
import com.iware.lottery.admin.model.UserDetails;
import com.iware.lottery.admin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mahone Wu on 16/11/5.
 *
 *
 */
public class AuthInterceptor  extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    private static final int TOKEN_TIME = 2;//hours

    @Inject
    private UserService userService;

    @Inject
    private ObjectMapper objectMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){

            //需要做验证的接口加上注解@AuthValidate,
            AuthValidate authValidate = ((HandlerMethod)handler).getMethodAnnotation(AuthValidate.class);

            if(null == authValidate ||authValidate.validate() == false) return true;
            else {
                //TODO 做相应的token验证
                ResponseEntity tokenValidate = this.validateToken(request);
                if(tokenValidate.getStatusCode() == HttpStatus.OK ){
                    logger.info("哈哈,验证通过");
                    return  true;
                }else{
                    ObjectMapper mapper = new ObjectMapper();
                    String jsr = mapper.writeValueAsString(tokenValidate);
                    OutKit.writeJson(response, jsr);
                    return  false;
                }
            }
        }
        return true;
    }


    /**
     * 这里返回值可以修改,比如token过期,token验证错误,灵活变通
     * @param request
     * @return
     */
    public ResponseEntity validateToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){//10001 report invalid
            return new ResponseEntity<>(ResponseMessage.success("token.validated"), HttpStatus.NO_CONTENT);
        }
        UserDetails userDetails = userService.findUserByToken(token);
        if(DateKit.compareTime(userDetails.getTokenDate(),TOKEN_TIME)){//有效期内
            return new ResponseEntity<>(ResponseMessage.success("token.validated"), HttpStatus.OK);
        }else{//token过期  10002代表过期
            return new ResponseEntity<>(ResponseMessage.success("token.validated"), HttpStatus.OK);
        }
    }
}
