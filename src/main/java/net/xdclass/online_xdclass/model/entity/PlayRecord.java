package net.xdclass.online_xdclass.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PlayRecord {

    private Integer id;

    private Integer userId;

    private Integer videoId;

    private Integer currentNum;

    private Integer episodeId;

    private Date createTime;


}
