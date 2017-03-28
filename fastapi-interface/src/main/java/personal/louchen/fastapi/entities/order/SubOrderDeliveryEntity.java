package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.order.enums.SubOrderStatus;
import personal.louchen.fastapi.entities.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 子订单的发货单实体
 * Created by louchen on 16/9/7.
 */
@Entity
@Table(name = "ai_sub_order_delivery", indexes = {
        @Index(columnList = "OUT_TRADE_CODE", name = "UK_OTC", unique = true)
})
public class SubOrderDeliveryEntity {
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
    @JoinColumn(name = "sub_order_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private SubOrderEntity subOrderEntity;//子订单id外键

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private UserEntity userEntity;//用户 id外键

    @Column(name = "sub_order_delivery_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'发货单编码'")
    private String subOrderDeliveryCode;

    @Column(name = "sub_order_status", nullable = false, columnDefinition = "varchar(255) comment'子订单状态,详细见枚举类SubOrderStatus'")
    @Enumerated(EnumType.STRING)
    private SubOrderStatus subOrderStatus;//子订单状态

    //子订单信息

    @Column(name = "create_time", nullable = false, updatable = false, columnDefinition = "datetime comment'子订单创建时间'")
    private Date createTime;//订单创建时间

    @Column(name = "cancel_time", nullable = true, columnDefinition = "datetime comment'子订单取消时间'")
    private Date cancelTime;//订单关闭时间

    @Column(name = "total_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'订单金额,未折扣、未积分抵扣、未优惠券抵扣'")
    private BigDecimal totalAmount;//订单金额,未折扣、未积分抵扣、未优惠券抵扣,mock订单金额100元

    @Column(name = "discount_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'订单折扣金额'")
    private BigDecimal discountAmount = BigDecimal.ZERO;//订单折扣金额,mock订单打9折,100-100*0.9折这个字段记录10元

    @Column(name = "point", nullable = false, updatable = false, columnDefinition = "int(20) comment'抵扣的积分,这个字段只是记录抵扣的积分,并不代表抵扣多少金额'")
    private int point = 0;//抵扣的积分,这个字段只是记录抵扣的积分,并不代表抵扣多少金额

    @Column(name = "point_coefficient", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'抵扣积分的系数'")
    private BigDecimal pointCoefficient = BigDecimal.ONE;//抵扣积分的系数,mock系数是1,积分是10,则抵扣10元,mock系数是0.1,积分是10,则抵扣1元

    @Column(name = "point_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'抵扣金额,抵扣积分*系数'")
    private BigDecimal pointAmount = BigDecimal.ZERO;//抵扣金额,抵扣积分*系数

    @Column(name = "transportation_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'物流金额'")
    private BigDecimal transportationAmount = BigDecimal.ZERO;//物流金额

    @Column(name = "coupon_id", nullable = true, updatable = false, columnDefinition = "varchar(255) comment'优惠券优惠码外键'")
    private String couponId;//优惠券优惠码外键

    @Column(name = "coupon_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'优惠券优惠码抵扣金额'")
    private BigDecimal couponAmount = BigDecimal.ZERO;//优惠券优惠码金额

    @Column(name = "need_to_pay_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment'最终需支付的金额=订单金额-订单折扣金额-积分抵扣金额-优惠抵扣金额+物流金额'")
    private BigDecimal needToPayAmount;//最终需支付的金额=订单金额-订单折扣金额-积分抵扣金额-优惠抵扣金额+物流金额

    @Column(name = "refund_amount", nullable = false, scale = 2, columnDefinition = "decimal(19,2) comment'已退款金额'")
    private BigDecimal refundAmount = BigDecimal.ZERO;//已退款金额

    @Column(name = "user_remark", nullable = true, columnDefinition = "varchar(255) comment'买家备注'")
    private String userRemark;//购买者备注

    @Column(name = "operation_remark", nullable = true, columnDefinition = "varchar(255) comment'运营备注'")
    private String operationRemark;//运营备注

    @Column(name = "cs_remark", nullable = true, columnDefinition = "varchar(255) comment'客服备注'")
    private String csRemark;//客服备注

    //收货信息

    @Column(name = "district_id", nullable = false, columnDefinition = "int(20) comment'省市区id'")
    private int districtId;

    @Column(name = "address", nullable = false, columnDefinition = "varchar(255) comment'收货地址'")
    private String address;

    @Column(name = "zip_code", nullable = false, columnDefinition = "varchar(255) comment'邮编'")
    private String zipCode;

    @Column(name = "receiver", nullable = false, columnDefinition = "varchar(255) comment'收货人'")
    private String receiver;//收货人

    @Column(name = "receiver_phone", nullable = false, columnDefinition = "varchar(255) comment'收货人手机号码'")
    private String receiverPhone;//收货人手机号码

    @Column(name = "delivery_company_code", nullable = true, columnDefinition = "varchar(255) comment'快递公司code,SF=顺丰'")
    private int deliveryCompanyCode;//快递类型

    @Column(name = "delivery_company_name", nullable = true, columnDefinition = "varchar(255) comment'快递公司名称'")
    private int deliveryCompanyType;//快递类型

    @Column(name = "delivery_code", nullable = true, columnDefinition = "varchar(255) comment'快递物流单号'")
    private String deliveryCode;//快递物流单号

    @Column(name = "peroid", nullable = true, columnDefinition = "int(3) comment'第几期配送'")
    private int period;//第几期

    @Column(name = "plan_receipt_time", nullable = true, columnDefinition = "datetime comment'计划收货时间'")
    private Date planReceiptTime;//计划收货时间

    @Column(name = "plan_send_time", nullable = true, columnDefinition = "datetime comment'计划发货时间'")
    private Date planSendTime;//计划发货时间

    @Column(name = "delivery_time", nullable = true, columnDefinition = "datetime comment'出库送货时间'")
    private Date deliveryTime;//出库送货时间

    @Column(name = "finish_time", nullable = true, columnDefinition = "datetime comment'完成时间'")
    private Date finishTime;//完成时间

}
