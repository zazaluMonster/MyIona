package iona.mapper;

import iona.pojo.Message;
import iona.pojo.Pager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    List<Message> getFollowingMessage(int id, Pager pager);
    Message getMessageByIdAndCurUserId(int messageId,int curUserId);
}
