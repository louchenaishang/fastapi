package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;
import personal.louchen.fastapi.entities.sku.SkuEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品行信息
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_item")
public class ProductItemEntity extends BaseManagementEntity {

    @Column(name = "product_item_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'商品行编码'")
    private String productItemCode;

    @Column(name = "out_code", nullable = false, columnDefinition = "varchar(255) comment'外部编码'")
    private String outCode;

    @Column(name = "price", nullable = false, scale = 2, updatable = false, columnDefinition = "decimal(19,2) default 0.00 comment'销售单价'")
    private BigDecimal price;

    @Column(name = "stock", nullable = false, columnDefinition = "int(10) default 0 comment'商品库存'")
    private Integer stock;

    @Column(name = "stock_occupy", nullable = false, columnDefinition = "int(10) default 0 comment'占用库存'")
    private Integer stockOccupy;

    @Column(name = "stock_remain", nullable = false, columnDefinition = "int(10) default 0 comment'剩余库存'")
    private Integer stockRemain;

    @Column(name = "saled_count", nullable = false, columnDefinition = "int(10) default 0 comment'已销售数量'")
    private Integer saledCount;

    @Column(name = "description", nullable = true, columnDefinition = "varchar(255) comment'描述'")
    private String description;

    @Column(name = "period", nullable = true, columnDefinition = "int(10) default 1 comment'周期属性,计算订单拆分次数'")
    private Integer period;

    @Column(name = "period_text", nullable = true, columnDefinition = "varchar(255) comment'周期属性中文描述'")
    private String periodText;

    @Column(name = "strategy_price_check", nullable = false, columnDefinition = "int(10) default 1 comment'价格策略,是否进行价格检查,商品保存时候检查商品价格是否大于sku成本,公式:商品价格/周期期数>sku成本'")
    private boolean strategyPriceCheck;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", referencedColumnName = "id")
    private SkuEntity skuEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

}
