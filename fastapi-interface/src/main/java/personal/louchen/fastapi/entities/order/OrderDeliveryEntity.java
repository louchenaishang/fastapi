package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单配送信息
 * Created by louchen on 16/9/8.
 */
@Entity
@Table(name = "ai_order_delivery")
public class OrderDeliveryEntity {
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
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private OrderEntity orderEntity;//订单id外键

    @Column(name = "IS_SUBSCRIPTION", nullable = false, columnDefinition = "int(1) comment'是否订阅类,0否、1是'")
    private int isSubscription;//是否订阅类

    @Column(name = "DISTRICT_ID", nullable = false, columnDefinition = "int(20) comment'省市区id'")
    private int districtId;

    @Column(name = "ADDRESS", nullable = false, columnDefinition = "varchar(255) comment'收货地址'")
    private String address;

    @Column(name = "ZIP_CODE", nullable = false, columnDefinition = "varchar(255) comment'邮编'")
    private String zipCode;

    @Column(name = "RECEIVER", nullable = false, columnDefinition = "varchar(255) comment'收货人'")
    private String receiver;//收货人

    @Column(name = "RECEIVER_PHONE", nullable = false, columnDefinition = "varchar(255) comment'收货人手机号码'")
    private String receiverPhone;//收货人手机号码

    @Column(name = "DELIVERY_TYPE", nullable = true, columnDefinition = "int(2) comment'快递类型,1顺发、2联邦、3万象'")
    private int deliveryType;//快递类型

    @Column(name = "DELIVERY_CODE", nullable = true, columnDefinition = "varchar(255) comment'快递单号'")
    private String deliveryCode;//快递单号

    @Column(name = "PEROID", nullable = true, columnDefinition = "int(3) comment'第几期配送'")
    private int period;//第几期

    @Column(name = "SEND_WEEK", nullable = true, columnDefinition = "int(1) comment'周几配送'")
    private int sendWeek;//周几配送

    @Column(name = "CREATE_TIME", nullable = true, columnDefinition = "datetime comment'创建时间'")
    private Date createTime;//创建时间

    @Column(name = "PLAN_RECEIPT_TIME", nullable = true, columnDefinition = "datetime comment'计划收货时间'")
    private Date planReceiptTime;//计划收货时间

    @Column(name = "PLAN_SEND_TIME", nullable = true, columnDefinition = "datetime comment'计划发货时间'")
    private Date planSendTime;//计划发货时间

    @Column(name = "DELIVERY_TIME", nullable = true, columnDefinition = "datetime comment'出库送货时间'")
    private Date deliveryTime;//出库送货时间

    @Column(name = "FINISH_TIME", nullable = true, columnDefinition = "datetime comment'完成时间'")
    private Date finishTime;//完成时间

}
