package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.channel.ChannelEntity;
import personal.louchen.fastapi.entities.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单组
 * Created by louchen on 16/9/8.
 */
@Entity
@Table(name = "ai_order_group")
public class OrderGroupEntity {
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
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, columnDefinition = "varchar(255) comment '用户id,外键'")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private UserEntity userEntity;//用户 id外键

    @Column(name = "split_status", nullable = false, columnDefinition = "int(1) default 0 comment '拆分状态'")
    private int splitStatus;//0未拆分,1已拆分

    @Column(name = "create_time", nullable = true, columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    @Column(name = "status", nullable = false, columnDefinition = "int(1) comment '订单组状态,0取消、1未付款、2已付款'")
    private int status;//订单组状态,0取消、1未付款、2已付款

    @Column(name = "total_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment '冗余所有订单金额'")
    private BigDecimal totalAmount;//冗余所有 订单金额

    @Column(name = "need_to_pay_amount", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) comment '冗余所有订单最终需支付金额'")
    private BigDecimal needToPayAmount;//冗余所有 订单最终需支付的金额

    @Column(name = "refund_amount", nullable = false, scale = 2, updatable = true, columnDefinition = "decimal(19,2) comment'已退款金额'")
    private BigDecimal refundAmount = BigDecimal.ZERO;//冗余已退款金额

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private ChannelEntity channelEntity;
}
