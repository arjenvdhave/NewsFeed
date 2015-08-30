package com.newsfeed.dao;

import com.newsfeed.entities.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by havea on 29/08/15.
 */
@Repository
public interface NewsFeedDao extends JpaRepository<NewsFeed, Long> {

    Long countByArticleUrl( String articleUrl );
    List< NewsFeed > findAllByOrderByPublishedDateDesc();

    @Query("SELECT n FROM NewsFeed n WHERE n.source.id = :sourceId")
    List< NewsFeed > findBySourceOrderByPublishedDateDesc(@Param("sourceId")Long sourceId);
}
