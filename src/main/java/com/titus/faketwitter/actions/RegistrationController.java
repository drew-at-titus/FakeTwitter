package com.titus.faketwitter.actions;

import com.titus.faketwitter.users.User;
import com.titus.faketwitter.users.UserCreationRequest;
import com.titus.faketwitter.users.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  private final UserService userService;

  @Autowired
  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/signup")
  public String registration(Model model) {
    model.addAttribute("user", new UserCreationRequest());
    return "registration";
  }

  @PostMapping(value = "/signup")
  public String createNewUser(@Valid UserCreationRequest user, BindingResult bindingResult,
      Model model, HttpServletRequest request) {
    User userExists = userService.findByUsername(user.getUsername());
    if (userExists != null) {
      bindingResult.rejectValue("username", "error.user", "Username is already taken");
    }
    if (!bindingResult.hasErrors()) {
      userService.saveNewUser(user);

      userService.logInUser(request, user.getUsername(), user.getPassword());
      
      return "redirect:/tweets";
    }
    model.addAttribute("user", new UserCreationRequest());
    return "registration";
  }

}
