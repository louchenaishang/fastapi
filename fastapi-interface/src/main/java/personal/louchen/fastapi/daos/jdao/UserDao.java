package personal.louchen.fastapi.daos.jdao;

import personal.louchen.fastapi.entities.user.UserEntity;
import personal.louchen.fastapi.daos.jdao.base.BaseRepository;

/**
 * Created by louchen on 16/8/25.
 */
public interface UserDao extends BaseRepository<UserEntity> {

    UserEntity findByNickName(String nickName);

}
