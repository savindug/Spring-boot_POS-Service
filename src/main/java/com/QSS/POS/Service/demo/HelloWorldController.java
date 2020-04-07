//package com.springboot.REST.demo;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.websocket.server.PathParam;
//import java.util.ArrayList;
//
//@RestController
//public class HelloWorldController {
//
//    @RequestMapping(method = RequestMethod.GET, path = "/hello")
//    public String hello(@PathParam("name") String name, @PathParam("msg") String msg){
//        HelloWorld hello = new HelloWorld(name, msg);
//        ArrayList<String> arr = new ArrayList<>();
//        arr.add(hello.getMsg());
//        arr.add(hello.getName());
//
//        return String.format("Hello! \n"+ hello.getMsg()+", "+ hello.getName());
//    }
//
//
//
//}

////