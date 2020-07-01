package com.udacity.jwdnd.course1.cloudstorage.entity;

import java.util.Objects;

public class Credential {
    private Long credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private Long userId;
    private String decodedPassword;

    public Credential(){}

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDecodedPassword() {
        return decodedPassword;
    }

    public void setDecodedPassword(String decodedPassword) {
        this.decodedPassword = decodedPassword;
    }

    public Credential(Long credentialId, String url, String userName, String key, String password, Long userId, String decodedPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userId = userId;
        this.decodedPassword = decodedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return Objects.equals(credentialId, that.credentialId) &&
                Objects.equals(url, that.url) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(key, that.key) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(decodedPassword, that.decodedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credentialId, url, userName, key, password, userId, decodedPassword);
    }

    @Override
    public String toString() {
        return "Credential{" +
                "credentialId=" + credentialId +
                ", url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", key='" + key + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                ", decodedPassword='" + decodedPassword + '\'' +
                '}';
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Credential;
    }


}
