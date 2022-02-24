package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String getAllUsersController(Model model) {
        model.addAttribute("users", userDao.getListUsers());
        return "getAll";
    }

    @GetMapping("/{id}")
    public String getUsersByIdController(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "getId";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") User user) {

        userDao.add(user);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "save";
    }

    @GetMapping("/{id}/update")
    public String update(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDao.getUserById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDao.userUpdate(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userDao.deleteUsers(id);
        return "redirect:/";
    }
}








//    @PostMapping()
//    public String save(@RequestParam("firstName") String firstName,
//                       @RequestParam("lastName") String lastName,
//                       @RequestParam("email") String email, Model model) {
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//
//        userDao.add(user);
//        model.addAttribute("user", user);
//        return "save";
//    }