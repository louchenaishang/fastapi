package personal.louchen.fastapi.entities.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 周期购规则
 * Created by louchen on 2017/3/10.
 */
@Entity
@Table(name = "ai_rule_period")
public class RulePeriodEntity {

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

    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(255) comment'中文名称'")
    private String name;

    @Column(name = "PERIOD_INTERVAL", nullable = false, columnDefinition = "int(10) default 7 comment'周期间隔单位是天计算订单发货日期'")
    private Integer periodInterval;

    @Column(name = "ABORT_WEEK", nullable = false, columnDefinition = "int(10) comment'截单周几计算首次发货日期'")
    private Integer abortWeek;

    @Column(name = "ABORT_HOUR", nullable = false, columnDefinition = "int(10) default 11 comment'截单24小时制的小时计算首次发货日期'")
    private Integer abortHour;


}
