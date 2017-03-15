package personal.louchen.fastapi.entities.ticket;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * 兑换码行信息
 * Created by louchen on 2017/3/15.
 */
@Entity
@Table(name = "ai_ticket_exchange_item")
public class TicketExchangeItemEntity implements java.io.Serializable {

    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    protected String id;

    @Column(name = "DELETED", columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Column(name = "CREATE_TIME", columnDefinition = "datetime comment'创建时间'")
    protected Date createTime;//创建时间

    @Column(name = "UPDATE_TIME", columnDefinition = "datetime comment'更新时间'")
    protected Date updateTime;//更新时间

    @PrePersist
    public void prePersist() {
        createTime = updateTime = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = new Date();
    }

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @Column(name = "STATE", columnDefinition = "int(2) DEFAULT 0 COMMENT '状态(0.未领取1.已领取2.使用中3.已使用)'")
    private Integer state;

    @Column(name = "EXCHANGE_CODE", columnDefinition = "varchar(255) comment'兑换码code'")
    private String exchangeCode;

    @Column(name = "PASSWORD", columnDefinition = "varchar(255) comment'兑换码密码'")
    private String password;

    @Column(name = "GET_USER_ID", columnDefinition = "varchar(255) comment'获取用户id'")
    private String getUserId;

    @Column(name = "GET_TIME", columnDefinition = "datetime comment'获取时间'")
    protected Date getTime;

    @Column(name = "USE_TIME", columnDefinition = "datetime comment'使用时间'")
    protected Date useTime;

    @Column(name = "ORDER_GROUP_ID")
    private String orderGroupId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_EXCHANGE_ID", referencedColumnName = "id")
    private TicketExchangeEntity ticketExchangeEntity;


}