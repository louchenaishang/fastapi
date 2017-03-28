package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 子订单
 * Created by louchen on 2017/3/28.
 */
@Entity
@Table(name = "ai_sub_order")
public class SubOrderEntity {

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

    @Column(name = "sub_order_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'子订单编码'")
    private String subOrderCode;

    @Column(name = "sorts", nullable = false, columnDefinition = "int(10) comment'排序值'")
    private Integer sorts;

    @Column(name = "need_to_pay_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment '冗余所有订单最终需支付金额'")
    private BigDecimal needToPayAmount;//子订单最终需支付的金额

    @Column(name = "refund_amount", nullable = false, scale = 2, updatable = true, columnDefinition = "decimal(19,2) comment'已退款金额'")
    private BigDecimal refundAmount = BigDecimal.ZERO;//子订单已退款金额


}
