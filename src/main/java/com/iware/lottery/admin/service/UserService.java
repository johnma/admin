package com.iware.lottery.admin.service;

import com.iware.lottery.admin.DTOUtils;
import com.iware.lottery.admin.domain.User;
import com.iware.lottery.admin.model.RegistrationForm;
import com.iware.lottery.admin.model.UserDetails;
import com.iware.lottery.admin.model.UserForm;

import com.iware.lottery.admin.repository.UserRepository;
import com.iware.lottery.admin.repository.UserSpecifications;
import com.iware.lottery.admin.exception.ResourceNotFoundException;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import javax.inject.Inject;

/**
 * Created by johnma on 2016/11/2.
 */
@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.logger(UserService.class);

    private UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDetails saveUser(RegistrationForm form){
        logger.debug("save user @" + form);

        User user = DTOUtils.map(form, User.class);

        User saved = userRepository.save(user);

        logger.debug("saved user is @" + saved);

        return DTOUtils.map(saved, UserDetails.class);
    }

    public Page<UserDetails> searchUsersByCriteria(String q, Pageable page){

        logger.debug("search users by keyword@" + q + ", page @" + page);

        Page<User> users = userRepository.findAll(UserSpecifications.filterByKeywordAndStatus(q), page);

        logger.debug("get users size @" + users.getTotalElements());

        return DTOUtils.mapPage(users, UserDetails.class);
    }

    public UserDetails findUserById(Long id){
        Assert.notNull(id, "user id can not be null");

        logger.debug("find user by id@" + id);

        User user = userRepository.findOne(id);

        if (user == null){
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(user, UserDetails.class);
    }

    public UserDetails updateUser(UserForm form){
        Assert.notNull(form.getId(), "user id can not be null");

        logger.debug("update user@" + form);

        User user = userRepository.findOne(form.getId());

        DTOUtils.mapTo(form, user);

        User saved = userRepository.save(user);

        logger.debug("updated user@" + saved);

        return DTOUtils.map(saved, UserDetails.class);
    }

    public boolean deleteUserById(Long id){
        Assert.notNull(id, "user id can not be null");

        logger.debug("delete user by id@" + id);

        User user = userRepository.findOne(id);

        if (null == user){
            throw new  ResourceNotFoundException(id);
        }

        userRepository.delete(id);

        return true;
    }
}