package personal.louchen.fastapi.entities.sku;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by louchen on 2017/3/9.
 */
@Entity
@Table(name = "ai_sku_category_property_key")
public class SkuCategoryPropertyKeyEntity {

    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    protected String id;

    @Column(name = "deleted",columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Column(name = "create_time", columnDefinition = "datetime comment'创建时间'")
    protected Date createTime;//创建时间

    @Column(name = "update_time", columnDefinition = "datetime comment'更新时间'")
    protected Date updateTime;//更新时间

    @Column(name = "create_user", columnDefinition = "varchar(255) comment'创建人id或者名称'")
    protected String createUser;//创建人id或者名称

    @Column(name = "update_user", columnDefinition = "varchar(255) comment'更新人id或者名称'")
    protected String updateUser;//更新人id或者名称

    @PrePersist
    public void prePersist() {
        createTime = updateTime = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        updateTime = new Date();
    }

    @Version
    @Column(name = "version", nullable = false)
    private long version = 0;//数据版本
    //########################################################
    @Column(name = "sorts", nullable = false, columnDefinition = "int(10) comment'排序值'")
    private Integer sorts;

    @Column(name = "visible", nullable = false, columnDefinition = "int(10) comment'是否可见'")
    private boolean visible;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_property_key_id", referencedColumnName = "id")
    private SkuPropertyKeyEntity skuPropertyKeyEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_id", referencedColumnName = "id")
    private SkuCategoryEntity skuCategoryEntity;


}
