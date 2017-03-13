package personal.louchen.fastapi.entities.user;

import org.hibernate.annotations.GenericGenerator;
import personal.louchen.fastapi.enums.LoginType;

import javax.persistence.*;

/**
 * Created by louchen on 16/8/25.
 */
@Entity
@Table(name = "ai_user_login", indexes = {
        @Index(columnList = "LOGIN_NAME", name="UK_LN", unique = true)
})
public class UserLoginEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;
    @Version
    @Column(name = "VERSION")
    private long version;
    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType type;
    @Column(name = "LOGIN_NAME", nullable = false)
    private String loginName;
    @Column(name = "LOGIN_VALUE", nullable = true)
    private String loginValue;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    private UserEntity userEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoginType getType() {
        return type;
    }

    public void setType(LoginType type) {
        this.type = type;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginValue() {
        return loginValue;
    }

    public void setLoginValue(String loginValue) {
        this.loginValue = loginValue;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
