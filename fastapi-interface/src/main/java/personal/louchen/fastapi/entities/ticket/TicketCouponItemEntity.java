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
    @Column(name = "STATE",columnDefinition = "int(2) DEFAULT 0 COMMENT '状态(0.未领取1.已领取2.使用中3.已使用)'")
    private Integer state;

    @Column(name = "COUPON_CODE", nullable = false,unique = true, columnDefinition = "varchar(255) comment'优惠码code'")
    private String name;

    @Column(name = "GET_USER_ID", columnDefinition = "varchar(255) comment'获取用户id'")
    private String getUserId;

    @Column(name = "GET_TIME", columnDefinition = "datetime comment'领取时间'")
    protected Date getTime;

    @Column(name = "USE_TIME", columnDefinition = "datetime comment'使用时间'")
    protected Date useTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_COUPON_ID", referencedColumnName = "id")
    private TicketCouponEntity ticketCouponEntity;

}
