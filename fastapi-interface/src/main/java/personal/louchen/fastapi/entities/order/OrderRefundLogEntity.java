package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.order.enums.Payment;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退款记录实体
 * Created by louchen on 2017/3/7.
 */
@Entity
@Table(name = "ai_order_refund_log")
public class OrderRefundLogEntity {
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
    @JoinColumn(name = "order_id", referencedColumnName = "ID", nullable = false)
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private OrderEntity orderEntity;//订单id外键

    @Column(name = "payment", nullable = false, updatable = false, columnDefinition = "varchar(255) comment'付款方式,详细见枚举类Payment'")
    @Enumerated(EnumType.STRING)
    private Payment payment;//支付方式

    @Column(name = "refund_time", nullable = false, updatable = false, columnDefinition = "datetime comment'支付时间'")
    private Date refundTime;//退款时间

    @Column(name = "refund_account", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'支付账户'")
    private String refundAccount;//退款账户

    @Column(name = "refund_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'支付金额'")
    private BigDecimal refundAmount;//退款金额

    @Column(name = "trade_no", nullable = true, updatable = false, unique = true,columnDefinition = "varchar(255) comment'内部系统交易流水号'")
    private String tradeNo;//内部系统交易流水号

    @Column(name = "out_trade_no", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'外部系统交易单号,微信交易流水号、支付宝交易流水号'")
    private String outTradeNo;//外部系统交易单号,微信交易流水号、支付宝交易流水号

}
