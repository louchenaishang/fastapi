package personal.louchen.fastapi.services.JService;

import com.google.common.collect.Lists;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import personal.louchen.fastapi.entities.user.UserEntity;
import personal.louchen.fastapi.entities.user.UserLoginEntity;
import personal.louchen.fastapi.entities.user.UserWealthEntity;
import personal.louchen.fastapi.enums.LoginType;
import personal.louchen.fastapi.interfaces.UserService;
import personal.louchen.fastapi.services.abstracts.AbstractBusinessServiceImpl;

/**
 * Created by louchen on 16/8/25.
 */
@Service("userService")
public class UserServiceImpl extends AbstractBusinessServiceImpl implements UserService {

    @Override
    public UserEntity create(String nickName) {
        UserEntity ue = new UserEntity();
        ue.setNickName(nickName);
        userDao.saveAndFlush(ue);

        //set userLoginEntity
        UserLoginEntity ule = new UserLoginEntity();
        ule.setType(LoginType.EMAIL);
        ule.setLoginName(ue.getNickName());
        ule.setUserEntity(ue);
        userLoginDao.saveAndFlush(ule);

        //set userWealthEntity
        UserWealthEntity uwe = new UserWealthEntity();
        uwe.setUserEntity(ue);
        userWealthDao.saveAndFlush(uwe);

        entityManager.detach(ue);
        entityManager.detach(ule);
        entityManager.detach(uwe);

        ue.setUserLoginEntities(Lists.newArrayList(ule));
        ue.setUserWealthEntity(uwe);

        return ue;
    }

    @Override
    public UserEntity query(String nickName) {
        Session currentSession = hibernateDao.getCurrentSession();
        UserEntity ue = userDao.findByNickName(nickName);

        return ue;
    }

}
