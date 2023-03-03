package com.anapedra.blogbackend.entities.enuns;

import com.anapedra.blogbackend.entities.User;

public enum UserStatus {
    ACTIVE(1),
    BLOCKED(2);

    private int code;

    private UserStatus(int code){
        this.code=code;
    }



    public int getCode(){
        return code;
    }
    public static UserStatus valueOf(int code){
       for (UserStatus status : UserStatus.values()){
           if (status.getCode()==code){
               return status;
           }
       }
       throw new IllegalArgumentException("Invalid UserStatus!");
    }
}
