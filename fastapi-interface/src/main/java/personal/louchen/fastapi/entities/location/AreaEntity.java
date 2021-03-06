package personal.louchen.fastapi.entities.location;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by louchen on 2017/3/7.
 */
@Entity
@Table(name = "ai_area")
public class AreaEntity {

    //#####################通用属性###########################
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    protected String id;

    @Column(name = "DELETED", columnDefinition = "int(1) default 0 comment'软删除标记'")
    protected Boolean deleted;

    @Column(name = "CREATE_TIME", columnDefinition = "datetime comment'创建时间'")
    protected Date createTime;//创建时间

    @Column(name = "UPDATE_TIME", columnDefinition = "datetime comment'更新时间'")
    protected Date updateTime;//更新时间

    @Column(name = "CREATE_USER", columnDefinition = "varchar(255) comment'创建人id或者名称'")
    protected String createUser;//创建人id或者名称

    @Column(name = "UPDATE_USER", columnDefinition = "varchar(255) comment'更新人id或者名称'")
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
    @Column(name = "VERSION", nullable = false)
    private long version = 0;//数据版本
    //########################################################


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PID", referencedColumnName = "id")
    private AreaEntity parentAreaEntity;

    @OneToMany
    @JoinColumn(name = "PID", referencedColumnName = "id")
    private Collection<AreaEntity> subAreaEntities = new ArrayList<>();

    @Column(name = "TEXT")
    private String text;

    @Column(name = "iconCls")
    private String iconCls;

    @Column(name = "SORTS")
    private Integer sorts;

    @Column(name = "DEEP")
    private Integer deep;

}