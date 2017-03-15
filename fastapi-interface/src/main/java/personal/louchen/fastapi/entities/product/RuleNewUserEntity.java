package personal.louchen.fastapi.entities.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 新用户奖励规则
 * Created by louchen on 2017/3/14.
 */
@Entity
@Table(name = "ai_rule_new_user")
public class RuleNewUserEntity {


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

    @Column(name = "DISCOUNT_PRICE", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) default 0.00 comment'折扣金额'")
    private BigDecimal discountPrice;//新用户奖励折扣

    @Column(name = "BEGIN_TIME", columnDefinition = "datetime comment'有效期开始时间'")
    protected Date beginTime;

    @Column(name = "END_TIME", columnDefinition = "datetime comment'有效期结束时间'")
    protected Date endTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GIFT_PRODUCT_ID", referencedColumnName = "id")
    private ProductEntity giftProductEntity;//新用户奖励赠品


}
