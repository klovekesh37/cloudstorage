package com.udacity.jwdnd.course1.cloudstorage.entity;

public class User {
    private Long userId;
    private String userName;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public Long getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getSalt() {
        return this.salt;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setSalt(final String salt) {
        this.salt = salt;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User other = (User)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$userId = this.getUserId();
                Object other$userId = other.getUserId();
                if (this$userId == null) {
                    if (other$userId != null) {
                        return false;
                    }
                } else if (!this$userId.equals(other$userId)) {
                    return false;
                }

                Object this$userName = this.getUserName();
                Object other$userName = other.getUserName();
                if (this$userName == null) {
                    if (other$userName != null) {
                        return false;
                    }
                } else if (!this$userName.equals(other$userName)) {
                    return false;
                }

                Object this$salt = this.getSalt();
                Object other$salt = other.getSalt();
                if (this$salt == null) {
                    if (other$salt != null) {
                        return false;
                    }
                } else if (!this$salt.equals(other$salt)) {
                    return false;
                }

                label62: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label62;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$firstName = this.getFirstName();
                    Object other$firstName = other.getFirstName();
                    if (this$firstName == null) {
                        if (other$firstName == null) {
                            break label55;
                        }
                    } else if (this$firstName.equals(other$firstName)) {
                        break label55;
                    }

                    return false;
                }

                Object this$lastName = this.getLastName();
                Object other$lastName = other.getLastName();
                if (this$lastName == null) {
                    return other$lastName == null;
                } else return this$lastName.equals(other$lastName);
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        Object $userName = this.getUserName();
        result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
        Object $salt = this.getSalt();
        result = result * 59 + ($salt == null ? 43 : $salt.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $firstName = this.getFirstName();
        result = result * 59 + ($firstName == null ? 43 : $firstName.hashCode());
        Object $lastName = this.getLastName();
        result = result * 59 + ($lastName == null ? 43 : $lastName.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getUserId();
        return "User(userId=" + var10000 + ", userName=" + this.getUserName() + ", salt=" + this.getSalt() + ", password=" + this.getPassword() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ")";
    }

    public User(final Long userId, final String userName, final String salt, final String password, final String firstName, final String lastName) {
        this.userId = userId;
        this.userName = userName;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
