package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsersController(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "getAll";
    }

    @GetMapping("/{id}")
    public String getUsersByIdController(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "getId";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") User user) {

        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "save";
    }

    @GetMapping("/{id}/update")
    public String update(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.userUpdate(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUsers(id);
        return "redirect:/";
    }
}
