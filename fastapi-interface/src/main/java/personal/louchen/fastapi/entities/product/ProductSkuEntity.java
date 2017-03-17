package personal.louchen.fastapi.entities.product;

import personal.louchen.fastapi.entities.BaseManagementEntity;
import personal.louchen.fastapi.entities.sku.SkuEntity;

import javax.persistence.*;

/**
 * 商品sku
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_product_sku")
public class ProductSkuEntity extends BaseManagementEntity {

    @Column(name = "product_sku_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'商品行编码'")
    private String productSkuCode;

    @Column(name = "out_code", nullable = false, columnDefinition = "varchar(255) comment'外部编码'")
    private String outCode;

    @Column(name = "description", nullable = true, columnDefinition = "varchar(255) comment'描述'")
    private String description;

    @Column(name = "strategy_price_check", nullable = false, columnDefinition = "int(10) default 1 comment'价格策略,是否进行价格检查,商品保存时候检查商品价格是否大于sku成本,公式:商品价格/周期期数>sku成本'")
    private boolean strategyPriceCheck;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", referencedColumnName = "id")
    private SkuEntity skuEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

}
