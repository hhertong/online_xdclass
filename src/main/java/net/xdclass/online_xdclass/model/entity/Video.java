package net.xdclass.online_xdclass.model.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *  小滴课堂 视频对象
 *
 *  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `title` varchar(524) DEFAULT NULL COMMENT '视频标题',
 *   `summary` varchar(1026) DEFAULT NULL COMMENT '概述',
 *   `cover_img` varchar(524) DEFAULT NULL COMMENT '封面图',
 *   `price` int(11) DEFAULT NULL COMMENT '价格,分',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   `point` double(11,2) DEFAULT '8.70' COMMENT '默认8.7，最高10分',
 */
@Data
public class Video {


    private Integer id;

    private String title;


    private String summary;


    private String coverImg;

    private Integer  price;


    private Date createTime;


    private Double point;


    private List<Chapter> chapterList;

}
