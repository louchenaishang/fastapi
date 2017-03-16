package personal.louchen.fastapi.entities.channel;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 渠道
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_channel")
public class ChannelEntity extends BaseManagementEntity {

    @Column(name = "channel_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'渠道编码'")
    private String channelCode;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'名称'")
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "varchar(255) comment'描述'")
    private String description;

}
