package personal.louchen.fastapi.entities.sku;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * sku
 */
@Entity
@Table(name = "ai_sku")
public class SkuEntity extends BaseManagementEntity {

    @Column(name = "sku_code", nullable = false, unique = true, columnDefinition = "varchar(255) comment'sku编码和外部系统交互使用'")
    private String skuCode;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'名称'")
    private String name;

    @Column(name = "show_name", nullable = false, columnDefinition = "varchar(255) comment'前端显示名称'")
    private String showName;

    @Column(name = "image", nullable = true, columnDefinition = "varchar(255) comment'图片url'")
    private String image;

    @Column(name = "can_air", nullable = true, columnDefinition = "int(1) comment'是否可以被空运'")
    private boolean canAir;

    @Column(name = "can_land", nullable = true, columnDefinition = "int(1) comment'是否可以被陆运'")
    private boolean canLand;

    @Column(name = "cost", nullable = false, columnDefinition = "decimal(19,2) comment'成本'")
    private BigDecimal cost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_id", referencedColumnName = "id")
    private SkuCategoryEntity skuCategoryEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_brand_id", referencedColumnName = "id")
    private SkuBrandEntity skuBrandEntity;

}

