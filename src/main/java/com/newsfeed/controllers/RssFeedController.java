package com.newsfeed.controllers;

import com.newsfeed.dao.RssFeedDao;
import com.newsfeed.entities.RssFeed;
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

    @RequestMapping(value = "/api/rssfeed", method = RequestMethod.GET)
    public @ResponseBody List<RssFeed> findAll() {
        return rssFeedDao.findAll();
    }

    @RequestMapping(value = "/api/rssfeed", method = RequestMethod.POST)
    public @ResponseBody Long createRssFeed(
            @RequestBody RssFeed rssFeed
    ) {
        return rssFeedDao.saveAndFlush( rssFeed ).getId();
    }




    @RequestMapping(value = "/api/rssfeed/mock", method = RequestMethod.GET)
    public @ResponseBody List<RssFeed> createMockData(){
        RssFeed nu = new RssFeed("nu.nl", "http://www.nu.nl/rss");
        RssFeed ad = new RssFeed("AD", "http://www.ad.nl/rss.xml");
        RssFeed spitsnieuws = new RssFeed("RTL Nieuws", "http://www.rtlnieuws.nl/service/rss/nieuws/index.xml");

        rssFeedDao.saveAndFlush( nu );
        rssFeedDao.saveAndFlush( ad );
        rssFeedDao.saveAndFlush( spitsnieuws );

        return rssFeedDao.findAll();
    }
}
