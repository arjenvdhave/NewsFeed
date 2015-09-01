package com.newsfeed.controllers.views;

import com.newsfeed.security.NfUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by havea on 31/08/15.
 */
@Controller
public class NewsFeedViewController {

    @RequestMapping(value = {"/newsfeed","/"}, method = RequestMethod.GET)
    public ModelAndView getNewsFeedPage(Principal principal){
        NfUserDetails details = (NfUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        ModelAndView view = new ModelAndView("newsfeed");

        //add the dto to the model, since we dont want to expose the password
        view.addObject("auth", details.getUser().toDto() );


        return view;
    }
}
