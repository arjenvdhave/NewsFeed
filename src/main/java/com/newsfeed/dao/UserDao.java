package com.newsfeed.dao;

import com.newsfeed.entities.RssFeed;
import com.newsfeed.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by havea on 01/09/15.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail( String email );
}
