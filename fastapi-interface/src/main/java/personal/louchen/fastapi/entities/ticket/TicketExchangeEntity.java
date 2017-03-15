package personal.louchen.fastapi.entities.ticket;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * 兑换码信息
 * Created by louchen on 2017/3/15.
 */
@Entity
@Table(name = "ai_ticket_exchange")
public class TicketExchangeEntity implements java.io.Serializable {

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

    @Column(name = "COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '数量'")
    private Integer count;

    @Column(name = "USE_COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '已使用数量'")
    private Integer useCount;

    @Column(name = "GET_COUNT", columnDefinition = "int(10) DEFAULT 0 COMMENT '已获取数量'")
    private Integer getCount;

    @Column(name = "BEGIN_DATE", columnDefinition = "datetime comment'有效期开始时间'")
    protected Date beginDate;

    @Column(name = "END_DATE", columnDefinition = "datetime comment'有效期结束时间'")
    protected Date endDate;

    @Column(name = "BATCH_CODE", columnDefinition = "varchar(255) DEFAULT 0 COMMENT '批次前缀'")
    private String batchCode;

}