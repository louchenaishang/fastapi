package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品sku的item
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_sku_item")
public class ProductSkuItemEntity extends BaseManagementEntity {

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

    @Column(name = "period", nullable = true, columnDefinition = "int(10) default 1 comment'周期属性,计算订单拆分次数'")
    private Integer period;

    @Column(name = "period_text", nullable = true, columnDefinition = "varchar(255) comment'周期属性中文描述'")
    private String periodText;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sku_id", referencedColumnName = "id")
    private ProductSkuEntity productSkuEntity;

}
