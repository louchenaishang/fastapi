package personal.louchen.fastapi.entities.ticket;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.product.ProductEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券信息
 * Created by louchen on 2017/3/14.
 */
@Entity
@Table(name = "ai_ticket_coupon")
public class TicketCouponEntity {

    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    protected String id;

    @Column(name = "DELETED", columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Column(name = "CREATE_TIME", columnDefinition = "datetime comment'创建时间'")
    protected Date createTime;//创建时间

    @Column(name = "UPDATE_TIME", columnDefinition = "datetime comment'更新时间'")
    protected Date updateTime;//更新时间

    @Column(name = "CREATE_USER", columnDefinition = "varchar(255) comment'创建人id或者名称'")
    protected String createUser;//创建人id或者名称

    @Column(name = "UPDATE_USER", columnDefinition = "varchar(255) comment'更新人id或者名称'")
    protected String updateUser;//更新人id或者名称

    @PrePersist
    public void prePersist() {
        createTime = updateTime = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updateTime = new Date();
    }

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(255) comment'名称'")
    private String name;

    @Column(name = "TYPE", columnDefinition = "int(2) DEFAULT NULL COMMENT '优惠码类型（1一卡一码2.通用码)'")
    private Integer type;

    @Column(name = "BUILD_TYPE", columnDefinition = "int(2) DEFAULT NULL COMMENT '生成方式(1自动生成2.倒码入库)'")
    private Integer buildType;

    @Column(name = "COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '数量'")
    private Integer count;

    @Column(name = "USE_COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '已使用数量'")
    private Integer useCount;

    @Column(name = "GET_COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '已领取的数量'")
    private Integer getCount;

    @Column(name = "PRICE", scale = 2, updatable = false, columnDefinition = "decimal(19,2) default 0.00 comment'抵扣金额'")
    private BigDecimal price;

    @Column(name = "USE_TYPE", columnDefinition = "int(2) DEFAULT NULL COMMENT '是否有最低使用限额(1有0.没有)'")
    private Boolean useType;

    @Column(name = "USE_MONEY", scale = 2, updatable = false, columnDefinition = "decimal(19,2) default 0.00 comment'使用最低满多少钱'")
    private BigDecimal useMoney;

    @Column(name = "GET_LIMIT", columnDefinition = "int(10) DEFAULT NULL COMMENT '每人领取张数'")
    private Integer getLimit;

    @Column(name = "ALIDITY_TYPE", columnDefinition = "int(10) DEFAULT NULL COMMENT '有效期类型（1.领取后多少天2.指定日期）'")
    private Integer alidityType;

    @Column(name = "ALIDITY_DAY", columnDefinition = "int(10) DEFAULT NULL COMMENT '领取后多少天后生效'")
    private Integer alidityDay;

    @Column(name = "BEGIN_DATE", columnDefinition = "datetime comment'有效期开始时间'")
    protected Date beginDate;

    @Column(name = "END_DATE", columnDefinition = "datetime comment'有效期结束时间'")
    protected Date endDate;

    @Column(name = "SYNC_FLAG", columnDefinition = "varchar(255) comment'同步打标签'")
    private String syncFlag;

    @Column(name = "IS_ALL", columnDefinition = "int(2) DEFAULT NULL COMMENT '是否全场通用(1.是0.不是)'")
    private Boolean isAll;

    @Column(name = "NOTE", columnDefinition = "text comment'使用说明'")
    private String note;

    @Column(name = "CREAT_STATE", columnDefinition = "int(2) DEFAULT NULL COMMENT '0。生成中1.已生成2生成失败)'")
    private Integer creatState;

    @Column(name = "BATCH", columnDefinition = "int(5) DEFAULT 0 COMMENT '批次前缀'")
    private Integer batch;

    @Column(name = "IS_SHOW", columnDefinition = "int(2) DEFAULT NULL COMMENT '是否显示名称(1.是0.不是)'")
    private Boolean isShow;

    @Column(name = "DISPLAY_NAME", nullable = false, columnDefinition = "varchar(255) comment'显示名称'")
    private String displayName;


}
