package personal.louchen.fastapi.entities.ticket;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.product.ProductEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 优惠券行信息
 * Created by louchen on 2017/3/14.
 */
@Entity
@Table(name = "ai_ticket_coupon_product")
public class TicketCouponProductEntity {

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
    @Column(name = "USE_COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '已使用数量'")
    private Integer useCount;

    @Column(name = "GET_COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '已领取的数量'")
    private Integer getCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_COUPON_ID", referencedColumnName = "id")
    private TicketCouponEntity ticketCouponEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_PRODUCT_ID", referencedColumnName = "id")
    private ProductEntity saleProductEntity;
}
