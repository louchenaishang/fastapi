package personal.louchen.fastapi.entities.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动销售商品信息
 * Created by louchen on 2017/3/13.
 */
@Entity
@Table(name = "ai_activity_product_item")
public class ActivityProductItemEntity {

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "id")
    private ActivityEntity activityEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_PRODUCT_SKU_ITEM_ID", referencedColumnName = "id")
    private ProductSkuItemEntity saleProductSkuItemEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_DISCOUNT_TIME_ID", referencedColumnName = "id")
    private RuleDiscountTimeEntity ruleDiscountTimeEntity;//限时折扣规则

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_DISCOUNT_CONDITION_ID", referencedColumnName = "id")
    private RuleDiscountConditionEntity ruleDiscountConditionEntity;//满减规则

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_ADDITION_ID", referencedColumnName = "id")
    private RuleAdditionEntity ruleAdditionEntity;//加价购规则

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_GIFT_ID", referencedColumnName = "id")
    private RuleGiftEntity ruleGiftEntity;//

}
