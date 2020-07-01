package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.exception.CredentialNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.IdGenerationException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource(value = "classpath:message.properties")
public class CredentialService {

    private final Logger logger = LoggerFactory.getLogger(CredentialService.class);

    @Autowired
    private CredentialMapper credentialMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;

    @Value("${resource.error}")
    String resourceError;
    @Value("${credential.id.error}")
    String credentialIdError;
    @Value("${credential.delete.error}")
    String deleteError;
    @Value("${credential.update.error}")
    String updateError;
    @Value("${credential.available.error}")
    String notAvailableError;

    public Credential encryptPassword(Credential credential) {
        String encryptionKey = RandomStringUtils.random(16, true, true);
        credential.setKey(encryptionKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encryptionKey));
        return credential;
    }

    public String decryptPassword(Credential credential){
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public Boolean isPresent(Long credentialId) throws ResourceNotAvailableException{
        Credential credential ;
        try {
            credential = credentialMapper.findCredentialByCredentialIdAndUserId(credentialId, userService.getUserId());
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if (credential != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void deleteCredential(Long credentialId) throws CredentialNotAvailableException, ResourceNotAvailableException {
        boolean isCredentialAvailable = isPresent(credentialId);
        if(!isCredentialAvailable)
            throw new CredentialNotAvailableException(notAvailableError);
        Integer noOfRowsDeleted;
        try {
            noOfRowsDeleted = credentialMapper.deleteCredentialById(credentialId);
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(noOfRowsDeleted != 1)
            throw new ResourceNotAvailableException(deleteError);
    }

    public void createCredential(Credential credential) throws ResourceNotAvailableException, IdGenerationException {
        credential.setUserId(userService.getUserId());
        Long credentialId;
        try {
            credentialId = credentialMapper.createCredential(encryptPassword(credential));
        }catch(DataAccessException dataAccessException) {
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(credentialId <= 0)
            throw new IdGenerationException(credentialIdError);
    }

    public void updateCredential(Credential credential) throws ResourceNotAvailableException {
        credential.setUserId(userService.getUserId());
        Integer noOfRowsUpdated;
        try {
            noOfRowsUpdated = credentialMapper.updateCredential(encryptPassword(credential));
        }catch(DataAccessException dataAccessException) {
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(noOfRowsUpdated != 1)
            throw new ResourceNotAvailableException(updateError);
    }

    public List<Credential> getAllCredentials() throws ResourceNotAvailableException {
        List<Credential> credentials ;
        try{
            credentials = credentialMapper.findAll(userService.getUserId());
        }catch(DataAccessException dataAccessException) {
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(credentials == null || credentials.isEmpty()) {
            return new ArrayList<>();
        }
        for(Credential credential : credentials) {
            credential.setDecodedPassword(decryptPassword(credential));
        }
        return credentials;
    }

    public void updateOrCreateCredential(Credential credential) throws IdGenerationException, ResourceNotAvailableException {
        Long credentialId = credential.getCredentialId();
        if (credentialId == null) {
            createCredential(credential);
        } else {
            updateCredential(credential);
        }
    }
}
