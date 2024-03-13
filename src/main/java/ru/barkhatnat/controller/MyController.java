//package ru.barkhatnat.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.barkhatnat.dao.UserDao;
//import ru.barkhatnat.entity.User;
//import ru.barkhatnat.service.UserService;
//
//import java.util.List;
//
//@Controller
//public class MyController {
//    @Autowired
//    private UserService userService;
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public String showAllUsers(Model model ){
//        List<User> allUsers = userService.getAllUsers();
//        model.addAttribute("allUsers", allUsers);
//        return "all-users";
//    }
//    @RequestMapping("/addNewUser")
//    public String addNewUser(Model model){
//        User user = new User();
//        model.addAttribute("user", user);
//        return "user-info";
//
//    }
//    @RequestMapping("/saveUser")
//
//    public String saveUser(@ModelAttribute("user") User user){
//
//        userService.saveUser(user);
//        return "redirect:/users";
//    }
//    @RequestMapping("/updateInfo")
//    public String updateInfo(@RequestParam("userId") int id, Model model){
//        User user = userService.getUser(id);
//        model.addAttribute("user", user);
//        return "user-info";
//    }
//    @RequestMapping("/deleteUser")
//    public String deleteUser(@RequestParam("userId") int id){
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }
//
//}
