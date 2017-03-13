package personal.louchen.fastapi.entities.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by louchen on 16/8/25.
 */
@Entity
@Table(name = "ai_user", indexes = {
        @Index(columnList = "NICKNAME", name="UK_NE", unique = true)
        })
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Column(name = "NICKNAME", nullable = false)
    private String nickName;
    @Version
    @Column(name = "VERSION")
    private long version;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private List<UserLoginEntity> userLoginEntities;

    @OneToOne(mappedBy = "userEntity", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private UserWealthEntity userWealthEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<UserLoginEntity> getUserLoginEntities() {
        return userLoginEntities;
    }

    public void setUserLoginEntities(List<UserLoginEntity> userLoginEntities) {
        this.userLoginEntities = userLoginEntities;
    }

    public UserWealthEntity getUserWealthEntity() {
        return userWealthEntity;
    }

    public void setUserWealthEntity(UserWealthEntity userWealthEntity) {
        this.userWealthEntity = userWealthEntity;
    }
}
