package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * 商品周期购截单规则,一堆多 一个周期购规则对应多个截单日期
 * Created by louchen on 2017/3/10.
 */
@Entity
@Table(name = "ai_product_rule_period_issue_time")
public class ProductRulePeriodIssueTimeEntity extends BaseManagementEntity {

    @Column(name = "abort_one_week", nullable = false, columnDefinition = "int(10) comment'截单周几计算首次发货日期'")
    private Integer abortOneWeek;

    @Column(name = "abort_one_hour", nullable = false, columnDefinition = "int(10) default 11 comment'截单24小时制的小时计算首次发货日期'")
    private Integer abortOneHour;

    @Column(name = "send_one_week", nullable = false, columnDefinition = "int(10) default 11 comment'周几可以发货,计算首次发货日期'")
    private Integer sendOneWeek;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_rule_period_id", referencedColumnName = "id")
    private ProductRulePeriodEntity productRulePeriodEntity;
}
