package com.tang.tensquare.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tang.tensquare.friend.model.NoFriend;

public interface NoFriendDao extends JpaRepository<NoFriend, String>, JpaSpecificationExecutor<NoFriend> {

	NoFriend findByUseridAndFriendid(String userid, String friendid);

}
