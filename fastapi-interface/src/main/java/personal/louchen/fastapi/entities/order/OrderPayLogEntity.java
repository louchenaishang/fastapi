package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单支付记录实体
 * Created by louchen on 16/9/7.
 */
@Entity
@Table(name = "ai_order_pay_log")
public class OrderPayLogEntity {
    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    protected String id;

    @Column(name = "DELETED", columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @JoinColumn(name = "ORDER_GROUP_ID", referencedColumnName = "ID", nullable = false)
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private OrderGroupEntity orderGroupEntity;//订单id外键

    @Column(name = "PAYMENT", nullable = false, updatable = false, columnDefinition = "int(1) comment'支付方式 1微信 2支付宝'")
    private int payment;//支付方式 1微信 2支付宝

    @Column(name = "PAY_TIME", nullable = false, updatable = false, columnDefinition = "datetime comment'支付时间'")
    private Date payTime;//支付时间

    @Column(name = "PAY_ACCOUNT", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'支付账户'")
    private String payAccount;//支付账户

    @Column(name = "PAY_AMOUNT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'支付金额'")
    private BigDecimal payAmount;//支付金额

    @Column(name = "OUT_TRADE_NO", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'外部系统交易单号,微信交易流水号、支付宝交易流水号'")
    private String outTradeNo;//外部系统交易单号,微信交易流水号、支付宝交易流水号

}
