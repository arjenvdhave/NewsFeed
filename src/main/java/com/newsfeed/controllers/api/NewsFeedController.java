package com.newsfeed.controllers.api;

import com.newsfeed.dao.NewsFeedDao;
import com.newsfeed.entities.NewsFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by havea on 29/08/15.
 */
@Controller
public class NewsFeedController {

    @Autowired
    NewsFeedDao newsFeedDao;

    @RequestMapping(value = "/api/newsfeed", method = RequestMethod.GET)
    public @ResponseBody List<NewsFeed> getNewsFeeds(){
        return newsFeedDao.findAllByOrderByPublishedDateDesc();
    }

    @RequestMapping(value = "/api/newsfeed/fromsource/{sourceid}", method = RequestMethod.GET)
    public @ResponseBody List<NewsFeed> getNewsFeedsFromSource(
            @PathVariable(value = "sourceid") Long sourceId
    ){
        return newsFeedDao.findBySourceOrderByPublishedDateDesc( sourceId );
    }

    @RequestMapping(value = "/api/newsfeed/{newsFeedId}", method = RequestMethod.DELETE)
    public @ResponseBody Long removeNewsFeed(
            @PathVariable(value = "newsFeedId") Long newsFeedId
    ){
        newsFeedDao.delete( newsFeedId );
        return newsFeedId;
    }
}
