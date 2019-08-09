package iona.service;


import iona.exception.IonaException;
import iona.pojo.Follow;
import iona.pojo.Message;

import java.util.List;

public interface IFollowService extends BaseService<Follow> {
    List<Follow> getFollowers(int crewId);
    List<Follow> getFollowingList(int crewId);
    Follow getByFollowIdAndFollowingId(int followId,int followingId) throws IonaException;
}
