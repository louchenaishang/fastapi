package personal.louchen.fastapi.entities.sku;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_sku_brand")
public class SkuBrandEntity extends BaseManagementEntity {

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'中文名称'")
    private String name;

}
