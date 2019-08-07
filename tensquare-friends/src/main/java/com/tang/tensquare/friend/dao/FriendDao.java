package com.tang.tensquare.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tang.tensquare.friend.model.Friend;

public interface FriendDao extends JpaRepository<Friend, String>, JpaSpecificationExecutor<Friend> {

	Friend findByUseridAndFriendid(String userid, String friendid);

	@Modifying
	@Query(value = "delete from tb_friend where userid = ? and friendid = ?",nativeQuery = true)
	void deleteByUseridAndFriendid(String userid, String friendid);
	
	@Modifying
	@Query(value = "update tb_friend set islike=? where userid=? and friendid=?", nativeQuery = true)
	void updateIslike(String islike, String userid, String friendid);

}
