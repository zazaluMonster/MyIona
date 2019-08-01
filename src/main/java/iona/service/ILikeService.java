package iona.service;


import iona.exception.IonaException;
import iona.pojo.Like;

public interface ILikeService extends BaseService<Like> {
    Like selectByLikerIdAndMessageId(int likerId,int messageId) throws IonaException;
}
