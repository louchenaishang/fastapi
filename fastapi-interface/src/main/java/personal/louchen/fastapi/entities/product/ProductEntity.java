package personal.louchen.fastapi.entities.product;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.entities.location.ShippingTemplateEntity;
import personal.louchen.fastapi.entities.sku.SkuCategoryEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品头信息
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product")
public class ProductEntity {

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
    @Column(name = "PRODUCT_CODE", nullable = false, unique = true, columnDefinition = "varchar(255) comment'商品头编码'")
    private String productCode;

    @Column(name = "PRODUCT_TYPE", nullable = false, columnDefinition = "varchar(255) comment'商品类型,详细见枚举类ProductType'")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "NAME", nullable = false, columnDefinition = "varchar(255) comment'中文名称'")
    private String name;

    @Column(name = "SUB_NAME", nullable = true, columnDefinition = "varchar(255) comment'副标题'")
    private String subName;

    @Column(name = "DESCRIPTION", nullable = true, columnDefinition = "varchar(255) comment'描述'")
    private String description;

    @Column(name = "HTML", nullable = true, columnDefinition = "varchar(5000) comment'商品详情普通屏幕'")
    private String html;

    @Column(name = "HTML_SMALL", nullable = true, columnDefinition = "varchar(5000) comment'商品详情小屏幕'")
    private String htmlSmall;

    @Column(name = "BUY_MEMBERLEVEL", nullable = true, columnDefinition = "varchar(255) comment'购买会员等级限制'")
    private String buyMemberLevel;

    @Column(name = "BUY_LIMIT", nullable = true, columnDefinition = "int(10) default 999 comment'购买数量上限'")
    private Integer buyLimit;

    @Column(name = "ENABLE_MEMBERLEVEL_DISCOUNT", nullable = true, columnDefinition = "int(1) default 0 comment'启用会员等级折扣'")
    private boolean enableMemberLevelDiscount;

    @Column(name = "ENABLE_MEMBERPOINT_DEDUCTION", nullable = true, columnDefinition = "int(1) default 0 comment'启用会员积分抵扣'")
    private boolean enableMemberPointDeduction;

    @Column(name = "ENABLE_PROMOTIONCODE_DEDUCTION", nullable = true, columnDefinition = "int(1) default 0 comment'启用使用优惠码抵扣'")
    private boolean enablePromotionCodeDeduction;

    @Column(name = "ENABLE_PERIOD", nullable = true, columnDefinition = "int(1) default 0 comment'允许选择周期购规则'")
    private boolean enablePeriod;

    @Column(name = "ENABLE_GIFT", nullable = true, columnDefinition = "int(1) default 0 comment'允许选择赠品规则'")
    private boolean enableGift;

    @Column(name = "ENABLE_ADDITION", nullable = true, columnDefinition = "int(1) default 0 comment'允许选择加价购规则'")
    private boolean enableAddition;

    @Column(name = "PLAN_SEND_TIME", nullable = true, columnDefinition = "datetime comment'计划发货时间'")
    private Date planSendTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKU_CATEGORY_ID", referencedColumnName = "id")
    private SkuCategoryEntity skuCategoryEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_GROUP_ID", referencedColumnName = "id")
    private ProductGroupEntity productGroupEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHIPPING_TEMPLATE_ID", referencedColumnName = "id")
    private ShippingTemplateEntity shippingTemplateEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_PERIOD_ID", referencedColumnName = "id")
    private RulePeriodEntity rulePeriodEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_GIFT_ID", referencedColumnName = "id")
    private RuleGiftEntity ruleGiftEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RULE_ADDITION_ID", referencedColumnName = "id")
    private RuleAdditionEntity ruleAdditionEntity;



}
