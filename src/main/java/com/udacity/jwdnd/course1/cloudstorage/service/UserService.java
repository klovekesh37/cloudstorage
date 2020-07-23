package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.exception.IdGenerationException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.UserDetailsNotValidException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.exception.UserNameNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:message.properties")
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${resource.error}")
    String resourceError;
    @Value("${user.id.error}")
    String userIdError;
    @Value("${user.details.error}")
    String invalidDetailsError;
    @Value("${user.exists.error}")
    String userExistsError;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HashService hashService;

//    @Autowired
//    public UserService( String resourceError,   String userIdError, String invalidDetailsError,  String userExistsError, UserMapper userMapper, HashService hashService) {
//        this.resourceError = resourceError;
//        this.userIdError = userIdError;
//        this.invalidDetailsError = invalidDetailsError;
//        this.userExistsError = userExistsError;
//        this.userMapper = userMapper;
//        this.hashService = hashService;
//    }

    public boolean isNull(User user) throws UserDetailsNotValidException {
        if(user == null)
            throw new UserDetailsNotValidException(invalidDetailsError);
        return false;
    }

    public boolean isPresent(String userName) throws ResourceNotAvailableException {
        User user;
        try {
            user = userMapper.findByUserName(userName);
        }catch (DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        return user != null;
    }

    public void createUser(User user) throws ResourceNotAvailableException, UserNameNotAvailableException, IdGenerationException, UserDetailsNotValidException {
        boolean canCreateUser = !isNull(user);
        boolean isUserNameAvailable = !isPresent(user.getUserName());
        if(isUserNameAvailable && canCreateUser) {
            String encodedSalt = hashService.getEncodedSalt();
            String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
            user.setSalt(encodedSalt);
            user.setPassword(hashedPassword);
            Long userId;
            try {
                userId = userMapper.createUser(new User(null, user.getUserName(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
            } catch (DataAccessException dataAccessException) {
                logger.error(dataAccessException.getMessage());
                throw new ResourceNotAvailableException(resourceError);
            }
            if(userId <= 0)
                throw new IdGenerationException(userIdError);
        }else
            throw new UserNameNotAvailableException(userExistsError);
    }

    public Long getUserId() throws ResourceNotAvailableException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            return userMapper.findByUserName(userName).getUserId();
        }catch (DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
    }
}
