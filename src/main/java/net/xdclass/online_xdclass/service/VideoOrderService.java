package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {

    int save(int userid, int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
