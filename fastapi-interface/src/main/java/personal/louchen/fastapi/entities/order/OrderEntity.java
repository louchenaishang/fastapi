package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体
 * Created by louchen on 16/9/7.
 */
@Entity
@Table(name = "ai_order", indexes = {
        @Index(columnList = "OUT_TRADE_CODE", name = "UK_OTC", unique = true)
})
public class OrderEntity {
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private OrderGroupEntity orderGroupEntity;//订单组 id外键盘

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private UserEntity userEntity;//用户 id外键

    @Column(name = "OUT_TRADE_CODE", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'外部订单号,如天猫、京东、一号店,这样不会重复导入外部订单'")
    private String outTradeCode;//外部订单号,如天猫、京东、一号店,这样不会重复导入外部订单

    @Column(name = "STATUS", nullable = false, columnDefinition = "int(3) comment'订单状态,0取消、1未付款、2已付款、3审单状态、4审单挂起、5仓库处理中、6物流配送中、7已收货'")
    private int status;//订单状态,0取消、1未付款、2已付款、3审单状态、4审单挂起、5仓库处理中、6物流配送中、7已收货

    @Column(name = "TYPE", nullable = false, updatable = false, columnDefinition = "int(1) comment'订单类型,1订阅鲜花单品 2订阅鲜花混搭 3快递 4闪购'")
    private int type;//订单类型,1订阅鲜花单品 2订阅鲜花混搭 3快递 4闪购

//    @Column(name = "USER_ID", nullable = false, updatable = false)
//    private String userId;//用户id外键

    @Column(name = "CREATE_TIME", nullable = false, updatable = false, columnDefinition = "datetime comment'订单创建时间'")
    private Date createTime;//订单创建时间

    @Column(name = "CANCEL_TIME", nullable = true, columnDefinition = "datetime comment'订单取消时间'")
    private Date cancelTime;//订单取消时间

    @Column(name = "PAY_TIME", nullable = true, columnDefinition = "datetime comment'订单支付时间'")
    private Date payTime;//订单支付时间

    @Column(name = "PAYMENT", nullable = true, columnDefinition = "int(1) comment'订单支付方式,0未支付 1微信 2支付宝 3全额抵扣'")
    private int payment;//订单支付方式,0未支付 1微信 2支付宝 3全额抵扣

    @Column(name = "TOTAL_AMOUNT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'订单金额,未折扣、未积分抵扣、未优惠券抵扣'")
    private BigDecimal totalAmount;//订单金额,未折扣、未积分抵扣、未优惠券抵扣,mock订单金额100元

    @Column(name = "DISCOUNT_AMOUNT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'订单折扣金额'")
    private BigDecimal discountAmount = BigDecimal.ZERO;//订单折扣金额,mock订单打9折,100-100*0.9折这个字段记录10元

    @Column(name = "POINT", nullable = false, updatable = false, columnDefinition = "int(20) comment'抵扣的积分,这个字段只是记录抵扣的积分,并不代表抵扣多少金额'")
    private int point = 0;//抵扣的积分,这个字段只是记录抵扣的积分,并不代表抵扣多少金额

    @Column(name = "POINT_COEFFICIENT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'抵扣积分的系数'")
    private BigDecimal pointCoefficient = BigDecimal.ONE;//抵扣积分的系数,mock系数是1,积分是10,则抵扣10元,mock系数是0.1,积分是10,则抵扣1元

    @Column(name = "POINT_AMOUNT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'抵扣金额,抵扣积分*系数'")
    private BigDecimal pointAmount = BigDecimal.ZERO;//抵扣金额,抵扣积分*系数

    @Column(name = "TRANSPORTATION_AMOUNT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'物流金额'")
    private BigDecimal transportationAmount = BigDecimal.ZERO;//物流金额

    @Column(name = "COUPON_ID", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'优惠券优惠码外键'")
    private String couponId;//优惠券优惠码外键

    @Column(name = "COUPON_AMOUNT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'优惠券优惠码抵扣金额'")
    private BigDecimal couponAmount = BigDecimal.ZERO;//优惠券优惠码金额

    @Column(name = "NEED_TO_PAY_AMOUNT", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'最终需支付的金额=订单金额-订单折扣金额-积分抵扣金额-优惠抵扣金额+物流金额'")
    private BigDecimal needToPayAmount;//最终需支付的金额=订单金额-订单折扣金额-积分抵扣金额-优惠抵扣金额+物流金额

    @Column(name = "REFUND_AMOUNT", nullable = false, scale = 2, columnDefinition = "decimal(19,2) comment'已退款金额'")
    private BigDecimal refundAmount = BigDecimal.ZERO;//已退款金额

    @Column(name = "USER_REMARK", nullable = true, columnDefinition = "varchar(255) comment'买家备注'")
    private String userRemark;//购买者备注

    @Column(name = "OPERATION_REMARK", nullable = true, columnDefinition = "varchar(255) comment'运营备注'")
    private String operationRemark;//运营备注

    @Column(name = "CS_REMARK", nullable = true, columnDefinition = "varchar(255) comment'客服备注'")
    private String csRemark;//客服备注

}
