package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.product.ProductItemEntity;
import personal.louchen.fastapi.entities.sku.SkuEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单商品实体
 * Created by louchen on 16/9/8.
 */
@Entity
@Table(name = "ai_order_item")
public class OrderItemEntity {
    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    protected String id;

    @Column(name = "deleted", columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Version
    @Column(name = "version", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private OrderEntity orderEntity;//订单id外键

    @Column(name = "is_gift", nullable = false, updatable = false, columnDefinition = "int(1) comment'是否赠品'")
    private Boolean isGift = false;

    @Column(name = "sku_name", nullable = false, updatable = false, columnDefinition = "varchar(255) comment'冗余skuName'")
    private String skuName;//冗余sku名称

    @Column(name = "sku_properties", nullable = false, updatable = false, columnDefinition = "varchar(5000) comment'冗余sku属性'")
    private String skuProperties;//冗余sku属性

    @Column(name = "product_name", nullable = false, updatable = false, columnDefinition = "varchar(255) comment'冗余商品名称'")
    private String productName;//冗余商品名称

    @Column(name = "quantity", nullable = false, updatable = false, columnDefinition = "int(20) comment'购买数量'")
    private Integer quantity;//购买数量

    @Column(name = "unit_price", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'购买时单价'")
    private BigDecimal unitPrice;//购买时单价

    @Column(name = "unit_point_amount", nullable = false, scale = 2, columnDefinition = "decimal(19,2) comment'单个积分抵扣金额'")
    private BigDecimal unitPointAmount = BigDecimal.ZERO;//单个积分抵扣金额

    @Column(name = "unit_coupon_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'单个抵扣优惠券优惠码金额'")
    private BigDecimal unitCouponAmount = BigDecimal.ZERO;//单个抵扣优惠券优惠码金额

    @Column(name = "unit_need_to_pay_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'单个最终需支付金额'")
    private BigDecimal unitNeedToPayAmount;//单个最终需支付

    @Column(name = "refund_quantity", nullable = false, updatable = true, columnDefinition = "int(20) comment'已退款数量'")
    private Integer refundQuantity = 0;//已退款数量

    @Column(name = "refund_amount", nullable = false, scale = 2, updatable = true, columnDefinition = "decimal(19,2) comment'已退款金额'")
    private BigDecimal refundAmount = BigDecimal.ZERO;//已退款金额

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", referencedColumnName = "id")
    private SkuEntity skuEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_item_id", referencedColumnName = "id")
    private ProductItemEntity productItemEntity;
}
