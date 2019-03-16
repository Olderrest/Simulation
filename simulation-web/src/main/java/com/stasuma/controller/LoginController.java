package com.stasuma.controller;

import com.stasuma.model.User;
import com.stasuma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMainPage(@ModelAttribute User user){
        return "main";
    }

    @RequestMapping(value = "/go_login", method = RequestMethod.GET)
    public ModelAndView goLogin(){
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            return "login";
        }
        String loginError = userService.validateLogin(user);
        if (loginError != null) {
            model.addAttribute("loginError", loginError);
            model.addAttribute("user", new User());
            return "login";
        }
        User loggedInUser = userService.findByLogin(user.getLogin());
        model.addAttribute("user", loggedInUser);
        return "redirect:/main_page";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            return "login";
        }
        String registerError = userService.validateRegister(user);
        if (registerError != null) {
            model.addAttribute("registerError", registerError);
            model.addAttribute("user", new User());
            return "login";
        }
        userService.add(user);
        model.addAttribute("user", user);
        return "redirect:/main_page";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }
}
