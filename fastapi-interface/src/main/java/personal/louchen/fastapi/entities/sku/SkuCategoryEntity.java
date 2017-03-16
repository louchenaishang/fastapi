package personal.louchen.fastapi.entities.sku;

import personal.louchen.fastapi.entities.BaseManagementEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_sku_category")
public class SkuCategoryEntity extends BaseManagementEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", referencedColumnName = "id")
    private SkuCategoryEntity parentSkuCategoryEntity;

    @OneToMany
    @JoinColumn(name = "pid", referencedColumnName = "id")
    private Collection<SkuCategoryEntity> subSkuCategoryEntities = new ArrayList<>();

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'中文名称'")
    private String name;

}
