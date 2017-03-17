package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;
import personal.louchen.fastapi.entities.location.ShippingTemplateEntity;
import personal.louchen.fastapi.entities.product.enums.EvaluationType;
import personal.louchen.fastapi.entities.product.enums.PeriodType;
import personal.louchen.fastapi.entities.product.enums.ProductType;
import personal.louchen.fastapi.entities.product.enums.ShelvesType;
import personal.louchen.fastapi.entities.sku.SkuCategoryEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品头信息
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product")
public class ProductEntity extends BaseManagementEntity {

    @Column(name = "product_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'商品头编码'")
    private String productCode;

    @Column(name = "out_code", nullable = false, columnDefinition = "varchar(255) comment'外部编码'")
    private String outCode;

    @Column(name = "product_type", nullable = false, columnDefinition = "varchar(255) comment'商品类型,详细见枚举类ProductType'")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "evaluation_type", nullable = false, columnDefinition = "varchar(255) comment'评价类型,详细见枚举类EvaluationType'")
    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;

    @Column(name = "shelves_type", nullable = false, columnDefinition = "varchar(255) comment'上下架类型,详细见枚举类ShelvesType'")
    @Enumerated(EnumType.STRING)
    private ShelvesType shelvesType;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'中文名称'")
    private String name;

    @Column(name = "sub_name", nullable = true, columnDefinition = "varchar(255) comment'副标题'")
    private String subName;

    @Column(name = "description", nullable = true, columnDefinition = "varchar(255) comment'描述'")
    private String description;

    @Column(name = "html", nullable = true, columnDefinition = "varchar(5000) comment'商品详情普通屏幕'")
    private String html;

    @Column(name = "html_small", nullable = true, columnDefinition = "varchar(5000) comment'商品详情小屏幕'")
    private String htmlSmall;

    @Column(name = "buy_memberlevel", nullable = true, columnDefinition = "varchar(255) comment'购买会员等级限制'")
    private String buyMemberLevel;

    @Column(name = "buy_limit", nullable = true, columnDefinition = "int(10) default 999 comment'购买数量上限'")
    private Integer buyLimit;

    @Column(name = "enable_memberlevel_discount", nullable = true, columnDefinition = "int(1) default 0 comment'启用会员等级折扣'")
    private boolean enableMemberLevelDiscount;

    @Column(name = "enable_memberpoint_deduction", nullable = true, columnDefinition = "int(1) default 0 comment'启用会员积分抵扣'")
    private boolean enableMemberPointDeduction;

    @Column(name = "enable_couponcode_deduction", nullable = true, columnDefinition = "int(1) default 0 comment'启用使用优惠码抵扣'")
    private boolean enableCouponCodeDeduction;

    @Column(name = "enable_turn", nullable = true, columnDefinition = "int(1) default 0 comment'是否转单'")
    private boolean enableTurn;

    @Column(name = "period_type", nullable = false, columnDefinition = "varchar(255) comment'周期类型,详细见枚举类PeriodType'")
    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    @Column(name = "unfixed_period_formula", nullable = true, columnDefinition = "varchar(255) comment'不固定周期购计算公式'")
    private String unfixedPeriodFormula;

    @Column(name = "plan_send_time", nullable = true, columnDefinition = "datetime comment'计划发货时间'")
    private Date planSendTime;

    @Column(name = "shelves_schedule_time", columnDefinition = "datetime comment'定时上下架时间'")
    protected Date shelvesScheduleTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_id", referencedColumnName = "id")
    private SkuCategoryEntity skuCategoryEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_template_id", referencedColumnName = "id")
    private ShippingTemplateEntity shippingTemplateEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_rule_period_id", referencedColumnName = "id")
    private ProductRulePeriodEntity productRulePeriodEntity;

}
