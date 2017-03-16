package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商品周期购规则
 * Created by louchen on 2017/3/10.
 */
@Entity
@Table(name = "ai_product_rule_period")
public class ProductRulePeriodEntity extends BaseManagementEntity {


    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'中文名称'")
    private String name;

    @Column(name = "period_interval", nullable = false, columnDefinition = "int(10) default 7 comment'周期间隔单位是天计算订单发货日期'")
    private Integer periodInterval;

    @Column(name = "abort_week", nullable = false, columnDefinition = "int(10) comment'截单周几计算首次发货日期'")
    private Integer abortWeek;

    @Column(name = "abort_hour", nullable = false, columnDefinition = "int(10) default 11 comment'截单24小时制的小时计算首次发货日期'")
    private Integer abortHour;


}
