package com.QSS.POS.Service.Controller;

import com.QSS.POS.Service.Entity.Users;
import com.QSS.POS.Service.ExceptionHanddle.ResourceNotFoundException;
import com.QSS.POS.Service.Util.JpaUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private JpaUserController userController;
    private UserControllerImpl userImpl = new UserControllerImpl();

    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello(){
        return String.format("Hello! \n");
    }
    //get all users
    @GetMapping("/users")
    public Iterable<Users> getAllUsers(){
        return userController.findAll();
    }


    @GetMapping(value = "users/login", produces = {"application/json"})
    public ResponseEntity<Users> loginUser(@PathParam("username") String username, @PathParam("pwd") String pwd) throws ResourceNotFoundException  {
        //Users user =  userController.findByUsernameAndPwd(username, pwd);
//        System.out.println(user.toString());
        Users user = userImpl.loginUser(username, pwd);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){

        Users usr = this.saveUser(user);
        System.out.println(usr);
        return ResponseEntity.ok().body(usr);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUser(@PathVariable(value="id") int id) throws ResourceNotFoundException {

            Users user = userController.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

            return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable(value="id") int id, @RequestBody Users userData) throws ResourceNotFoundException{

        Users user = userController.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        System.out.println(userData.toString());

        user.setUsername(userData.getUsername());
        user.setEmail(userData.getEmail());
        user.setPwd(userData.getPwd());
        user.setRole(userData.getRole());
        user.setRegDate(userData.getRegDate());

        userController.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public  ResponseEntity deleteUser(@PathVariable(value="id") int id) throws ResourceNotFoundException {
        userController.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userController.deleteById(id);

        return ResponseEntity.ok().build();
    }

    public boolean isAlreadyExist(String username, String email){
        boolean state = false;

        Users usersList = userController.findByUsernameOrEmail(username, email);

        if(usersList == null){
            state = false;
        }else {
            state = true;
        }
        return state;
    }

    public Users saveUser(Users usr){
        boolean stat = this.isAlreadyExist(usr.getUsername(), usr.getEmail());

        if ( stat == true){
            usr.setStatus("User already exist!");
        }else {
            userController.save(usr);
            usr.setStatus("User Successfully Saved!");
        }
        return usr;
    }

}
