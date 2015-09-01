package com.newsfeed.controllers.views;

import com.newsfeed.dao.UserDao;
import com.newsfeed.dto.NewUserDto;
import com.newsfeed.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by havea on 31/08/15.
 */
@Controller
public class LoginViewController {

    @Autowired
    UserDao userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getNewsFeedPage(){
        ModelAndView model = new ModelAndView("login");
        model.addObject("newUser", new NewUserDto());

        return model;
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signupNewUser(@ModelAttribute NewUserDto newUser, Model model, BindingResult bindingResult) {
        if( !newUser.getPassword().equals( newUser.getPasswordRepeat() )){
            bindingResult.reject("passwords.notequal", "De wachtwoorden zijn niet gelijk");
            return "redirect:login";
        }

        User u = new User();

        u.setAvatar("http://www.binarytradingforum.com/core/image.php?userid=9&dateline=1355299254");
        u.setFullname(newUser.getFullname());
        u.setEmail(newUser.getEmail());
        u.setPassword(newUser.getPassword());

        u = userDao.saveAndFlush(u);


        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }catch (Exception e ){
            bindingResult.reject("unknown.error", "Er is een serverfout opgetreden.");
            return "redirect:login";
        }

        return "redirect:newsfeed";
    }
}
