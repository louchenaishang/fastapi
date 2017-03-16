package personal.louchen.fastapi.entities.sku;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * Created by louchen on 2017/3/9.
 */
@Entity
@Table(name = "ai_sku_property_value")
public class SkuPropertyValueEntity extends BaseManagementEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_property_value_id", referencedColumnName = "id")
    private SkuCategoryPropertyValueEntity skuCategoryPropertyValueEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", referencedColumnName = "id")
    private SkuEntity skuEntity;


}
