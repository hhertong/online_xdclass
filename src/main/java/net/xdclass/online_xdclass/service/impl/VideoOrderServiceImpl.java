package net.xdclass.online_xdclass.service.impl;

import net.xdclass.online_xdclass.exception.XDException;
import net.xdclass.online_xdclass.mapper.*;
import net.xdclass.online_xdclass.model.entity.Episode;
import net.xdclass.online_xdclass.model.entity.PlayRecord;
import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoOrder;
import net.xdclass.online_xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {


    @Autowired
    private VideoOrderMapper videoOrderMapper;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;


    /**
     * 下单操作
     * @param userid
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int userid, int videoId) {

        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userid, videoId,1);

        if(videoOrder!=null){
            return 0;
        }

        Video video=videoMapper.findById(videoId);

        VideoOrder newVideoOrder=new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userid);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());


        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        /**
         * 生成播放记录
         */
        if (rows == 1){

            Episode firstEpisodeByVideoId = episodeMapper.findFirstEpisodeByVideoId(videoId);

            if (firstEpisodeByVideoId==null){

                throw new XDException(-1,"视频没有集信息");

            }
            PlayRecord playRecord=new PlayRecord();

            playRecord.setCreateTime(new Date());
            playRecord.setVideoId(videoId);
            playRecord.setEpisodeId(firstEpisodeByVideoId.getVideoId());
            playRecord.setCurrentNum(firstEpisodeByVideoId.getNum());
            playRecord.setUserId(userid);
            playRecordMapper.saveRecord(playRecord);

        }


        return rows;
    }

    /**
     * 查询订单列表
     * @param userId
     * @return
     */
    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {

        return videoOrderMapper.listOrderByUserId(userId);
    }
}
