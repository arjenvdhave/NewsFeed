package com.newsfeed.entities;

import com.newsfeed.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;

/**
 * Created by havea on 01/09/15.
 */
@Entity(name = "users")
public class User extends BaseEntity{


    private String email;


    private String password;
    private String fullname;
    private String avatar;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }




    public UserDto toDto(){
        UserDto u = new UserDto();
        u.setId( this.getId() );
        u.setEmail( this.email );
        u.setFullname( this.fullname );
        u.setAvatar( this.avatar );

        return u;
    }
}
