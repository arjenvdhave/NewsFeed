package com.newsfeed.dao;

import com.newsfeed.entities.RssFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by havea on 30/08/15.
 */

@Repository
public interface RssFeedDao extends JpaRepository<RssFeed, Long>{
}
