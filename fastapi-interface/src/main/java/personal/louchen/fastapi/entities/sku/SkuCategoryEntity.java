package personal.louchen.fastapi.entities.sku;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by louchen on 2017/3/6.
 */
@Entity
@Table(name = "ai_sku_category")
public class SkuCategoryEntity {

    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    protected String id;

    @Column(name = "deleted", columnDefinition = "int(1) default 0 comment'软删除标记'")
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
    public void preUpdate() {
        updateTime = new Date();
    }

    @Version
    @Column(name = "version", nullable = false)
    private long version = 0;//数据版本
    //########################################################

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", referencedColumnName = "id")
    private SkuCategoryEntity parentSkuCategoryEntity;

    @OneToMany
    @JoinColumn(name = "pid", referencedColumnName = "id")
    private Collection<SkuCategoryEntity> subSkuCategoryEntities = new ArrayList<>();

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) comment'中文名称'")
    private String name;

}
