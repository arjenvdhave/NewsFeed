package com.newsfeed.controllers.api;

import com.newsfeed.dao.RssFeedDao;
import com.newsfeed.entities.RssFeed;
import com.newsfeed.tasks.RssReaderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by havea on 30/08/15.
 */
@Controller
public class RssFeedController {

    @Autowired
    RssFeedDao rssFeedDao;

    @Autowired
    RssReaderTask rssReaderTask;

    @RequestMapping(value = "/api/rssfeed", method = RequestMethod.GET)
    public @ResponseBody List<RssFeed> findAll() {
        return rssFeedDao.findAll();
    }

    @RequestMapping(value = "/api/rssfeed", method = RequestMethod.POST)
    public @ResponseBody Long createRssFeed(
            @RequestBody RssFeed rssFeed
    ) {
        rssFeed = rssFeedDao.saveAndFlush(rssFeed);
        rssReaderTask.readRssFeed( rssFeed );

        return rssFeed.getId();
    }
}
