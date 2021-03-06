package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.channel.ChannelEntity;
import personal.louchen.fastapi.entities.order.enums.OrderSplitStatus;
import personal.louchen.fastapi.entities.order.enums.OrderStatus;
import personal.louchen.fastapi.entities.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 * Created by louchen on 16/9/8.
 */
@Entity
@Table(name = "ai_order")
public class OrderEntity {
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
    @Column(name = "order_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'订单编码'")
    private String orderCode;

    @Column(name = "out_trade_code", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'外部订单号,如天猫、京东、一号店,这样不会重复导入外部订单'")
    private String outTradeCode;//外部订单号,如天猫、京东、一号店,这样不会重复导入外部订单

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, columnDefinition = "varchar(255) comment '用户id,外键'")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private UserEntity userEntity;//用户 id外键

    @Column(name = "order_split_status", nullable = false, columnDefinition = "varchar(255) comment'订单拆分状态,详细见枚举类OrderSplitStatus'")
    @Enumerated(EnumType.STRING)
    private OrderSplitStatus orderSplitStatus;//订单拆分状态

    @Column(name = "create_time", nullable = true, columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @Column(name = "pay_time", nullable = true, columnDefinition = "datetime comment '付款时间'")
    private Date payTime;

    @Column(name = "cancel_time", nullable = true, columnDefinition = "datetime comment '取消时间'")
    private Date cancelTime;

    @Column(name = "order_status", nullable = false, columnDefinition = "varchar(255) comment'订单状态,详细见枚举类OrderStatus'")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "total_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment '冗余所有订单金额'")
    private BigDecimal totalAmount;//冗余所有 订单金额

    @Column(name = "need_to_pay_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment '冗余所有订单最终需支付金额'")
    private BigDecimal needToPayAmount;//冗余所有子订单最终需支付的金额

    @Column(name = "refund_amount", nullable = false, scale = 2, updatable = true, columnDefinition = "decimal(19,2) comment'已退款金额'")
    private BigDecimal refundAmount = BigDecimal.ZERO;//冗余已退款金额

    @Column(name = "district_id", nullable = false, columnDefinition = "int(20) comment'省市区id'")
    private int districtId;//冗余收货地址省市区

    @Column(name = "address", nullable = false, columnDefinition = "varchar(255) comment'收货地址'")
    private String address;//冗余收货地址

    @Column(name = "receiver", nullable = false, columnDefinition = "varchar(255) comment'收货人'")
    private String receiver;//冗余收货人

    @Column(name = "receiver_phone", nullable = false, columnDefinition = "varchar(255) comment'收货人手机号码'")
    private String receiverPhone;//冗余收货人手机号码

    @Column(name = "buyer_phone", nullable = false, columnDefinition = "varchar(255) comment'订货人手机号'")
    private String buyerPhone;//订货人手机号码

    @Column(name = "buyer_remark", nullable = true, columnDefinition = "varchar(255) comment'买家备注'")
    private String buyerRemark;//买家备注

    @Column(name = "internal_remark", nullable = true, columnDefinition = "varchar(255) comment'内部备注'")
    private String internalRemark;//内部备注

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private ChannelEntity channelEntity;

}
