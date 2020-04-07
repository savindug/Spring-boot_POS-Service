package com.QSS.POS.Service.Controller;

import com.QSS.POS.Service.Entity.Users;
import com.QSS.POS.Service.Util.JpaUserController;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerImpl {

    @Autowired
    JpaUserController jpa;

    public boolean isAlreadyExist(String username, String email){
        boolean state = false;

        Users usersList = jpa.findByUsernameOrEmail(username, email);

//        if(usersList.size() == 0){
//            state = true;
//        }else {
//            state = false;
//        }
        return state;
    }

    public Users saveUser(Users usr){
        boolean stat = this.isAlreadyExist(usr.getUsername(), usr.getEmail());

        if ( stat == true){
            usr.setStatus("User already exist!");
        }else {
            jpa.save(usr);
            usr.setStatus("User Successfully Saved!");
        }
        return usr;
    }

    public Users loginUser(String username, String pwd){

        Users users = jpa.findByUsernameOrEmailAndPwd(username, pwd);

        return  users;
    }

//    public Users getUsers(int uID){
//        Users users = new Users();
//
//        return users;
//    }
//
//    public ArrayList<String> getAllUsers(){
//        ArrayList<String> users = new ArrayList<>();
//
//        return users;
//    }
//
//    public boolean insert(){
//        return false;
//
//    }
//
//    public boolean editUser(int uID){
//        boolean state = false;
//
//        return state;
//    }
//
//    public boolean deleteUser(int uID){
//        boolean state = false;
//
//        return state;
//    }

}
