package personal.louchen.fastapi.entities.sku;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;

/**
 * Created by louchen on 2017/3/9.
 */
@Entity
@Table(name = "ai_sku_category_property_value")
public class SkuCategoryPropertyValueEntity extends BaseManagementEntity {

    @Column(name = "value", nullable = false, columnDefinition = "varchar(255) comment'属性值'")
    private String value;

    @Column(name = "sorts", nullable = false, columnDefinition = "int(10) comment'排序值'")
    private Integer sorts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_property_key_id", referencedColumnName = "id")
    private SkuCategoryPropertyKeyEntity skuCategoryPropertyKeyEntity;

}
