package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 子订单配送信息
 * Created by louchen on 16/9/8.
 */
@Entity
@Table(name = "ai_sub_order_delivery")
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
    @ManyToOne(fetch = FetchType.EAGER)
    private SubOrderEntity subOrderEntity;//子订单id外键

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
