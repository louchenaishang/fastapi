package personal.louchen.fastapi.entities.order;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column(name = "sorts", nullable = false, columnDefinition = "int(10) comment'排序值'")
    private Integer sorts;

    @Column(name = "sub_order_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'子订单组编码'")
    private String subOrderCode;


}
