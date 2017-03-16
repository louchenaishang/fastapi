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

    @Column(name = "abort_week", nullable = false, columnDefinition = "int(10) comment'截单周几计算首次发货日期'")
    private Integer abortWeek;

    @Column(name = "abort_hour", nullable = false, columnDefinition = "int(10) default 11 comment'截单24小时制的小时计算首次发货日期'")
    private Integer abortHour;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_rule_period_id", referencedColumnName = "id")
    private ProductRulePeriodEntity productRulePeriodEntity;
}
