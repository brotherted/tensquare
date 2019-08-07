package com.tang.tensquare.friend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tang.tensquare.friend.client.UserClient;
import com.tang.tensquare.friend.dao.FriendDao;
import com.tang.tensquare.friend.dao.NoFriendDao;
import com.tang.tensquare.friend.model.Friend;
import com.tang.tensquare.friend.model.NoFriend;

@Service
@Transactional
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private NoFriendDao nofriendDao;
	
	@Autowired
	private UserClient userClient;
	
	public int addFriend(String userid, String friendid) {
		Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
		if(friend != null) {
			return 0;
		}else {
			Friend myFriend = friendDao.findByUseridAndFriendid(friendid, userid);
			if(myFriend == null) {
				friend = new Friend(userid, friendid, "0");
				friendDao.save(friend);
			}else {
				friend = new Friend(userid, friendid, "1");
				friendDao.save(friend);
				friendDao.updateIslike("1", friendid, userid);
			}
			userClient.updateFanscountAndFollowcount(userid, friendid, 1);
			return 1;
		}
		
	}

	public int addNoFriend(String userid, String friendid) {
		NoFriend noFriend = nofriendDao.findByUseridAndFriendid(userid, friendid);
		if(noFriend!=null) {
			return 0;
		}else {
			noFriend = new NoFriend(userid, friendid);
			nofriendDao.save(noFriend);
			return 1;
		}
	}

	public void deleteFriend(String userid, String friendid) {
		friendDao.deleteByUseridAndFriendid(userid, friendid);
		userClient.updateFanscountAndFollowcount(userid, friendid, -1);
		Friend friend = friendDao.findByUseridAndFriendid(friendid, userid);
		if(friend != null) {
			friendDao.updateIslike("0", friendid, userid);
		}
	}

}
