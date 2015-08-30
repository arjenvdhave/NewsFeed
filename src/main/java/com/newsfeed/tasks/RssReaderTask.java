package com.newsfeed.tasks;

import com.newsfeed.dao.NewsFeedDao;
import com.newsfeed.dao.RssFeedDao;
import com.newsfeed.entities.NewsFeed;
import com.newsfeed.entities.RssFeed;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.jsoup.Jsoup;
import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.impl.FeedFetcherCache;
import org.rometools.fetcher.impl.HashMapFeedInfoCache;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by havea on 30/08/15.
 */
@Component
public class RssReaderTask {

    @Autowired
    RssFeedDao rssFeedDao;

    @Autowired
    NewsFeedDao newsFeedDao;

    @Scheduled(fixedDelay = 300000)
    @PostConstruct
    public void scheduleRssRead(){
        readRssFeeds();
    }

    public void readRssFeeds(){
        List<RssFeed> feeds = rssFeedDao.findAll();
        if( feeds == null ){
            return;
        }

        for( RssFeed feed : feeds ){
            try {
                FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
                FeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);

                SyndFeed rssFeed = feedFetcher.retrieveFeed(new URL(feed.getUrl()));
                if( rssFeed.getEntries() != null ){
                    List<SyndEntry> entries = rssFeed.getEntries();
                    List<NewsFeed> newsFeeds = new ArrayList<NewsFeed>();

                    for( SyndEntry entry : entries ){
                        NewsFeed newsFeed = new NewsFeed();

                        if( entry.getLink() == null ){
                            //Link is our key field, so no key we skip the feed
                            continue;
                        }
                        if( newsFeedDao.countByArticleUrl(entry.getLink()) > 0 ) {
                            //Check if we have this article
                            continue;
                        }

                        newsFeed.setArticleUrl( entry.getLink() );
                        newsFeed.setTitle(entry.getTitle());

                        SyndContent content = entry.getDescription();
                        if( content == null ){
                            continue;
                        }

                        //Strip any html code from the content
                        String contentString = Jsoup.parse( content.getValue() ).text();
                        newsFeed.setContent( contentString );

                        List<SyndEnclosure> enclosures = entry.getEnclosures();
                        if( enclosures != null && enclosures.size() > 0 ){
                            SyndEnclosure enclosure = enclosures.get(0);
                            newsFeed.setImageUrl( enclosure.getUrl() );
                        }

                        newsFeed.setPublishedDate(entry.getPublishedDate());

                        newsFeed.setSource(feed);
                        newsFeeds.add(newsFeed);
                    }

                    newsFeedDao.save( newsFeeds );
                }
            }catch (Exception e ){
                System.out.println("Something went wrong!");
            }
        }
    }
}
