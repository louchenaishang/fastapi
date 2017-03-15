package personal.louchen.fastapi.entities.ticket;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.product.ProductEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券行信息
 * Created by louchen on 2017/3/14.
 */
@Entity
@Table(name = "ai_ticket_coupon_item")
public class TicketCouponItemEntity {

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
    @Column(name = "COUPON_CODE", nullable = false,unique = true, columnDefinition = "varchar(255) comment'优惠码code'")
    private String name;

    @Column(name = "GET_USER", nullable = false,unique = true, columnDefinition = "varchar(255) comment'领取用户id'")
    private String getUser;

    @Column(name = "GET_TIME", columnDefinition = "datetime comment'领取时间'")
    protected Date getTime;

    @Column(name = "STATE",  columnDefinition = "int(2) DEFAULT 0 COMMENT '0：生成 1：已下载 2：已使用 3:使用中（占用）'")
    private Integer state;

    @Column(name = "GET_STATE",  columnDefinition = "int(2) DEFAULT 0 COMMENT '获取状态 0 ：未领取 1 ：已领取'")
    private Integer getState;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_COUPON_ID", referencedColumnName = "id")
    private TicketCouponEntity ticketCouponEntity;

}
