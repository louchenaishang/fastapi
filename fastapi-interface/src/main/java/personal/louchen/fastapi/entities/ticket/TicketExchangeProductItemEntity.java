package personal.louchen.fastapi.entities.ticket;


import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.product.ProductSkuEntity;

import javax.persistence.*;
import java.util.Date;


/**
 * 兑换码商品信息,一对多可兑换的商品
 * Created by louchen on 2017/3/15.
 */
@Entity
@Table(name = "ai_ticket_exchange_product_item")
public class TicketExchangeProductItemEntity implements java.io.Serializable {

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

    @Column(name = "GET_COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '已获取数量'")
    private Integer getCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_EXCHANGE_ID", referencedColumnName = "id")
    private TicketExchangeEntity ticketExchangeEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_PRODUCT_ITEM_ID", referencedColumnName = "id")
    private ProductSkuEntity saleProductItemEntity;
}