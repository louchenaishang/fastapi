package personal.louchen.fastapi.interfaces;

import personal.louchen.fastapi.entities.user.UserEntity;

/**
 * Created by louchen on 16/8/25.
 */
public interface UserService {

    UserEntity create(String nickName);

    UserEntity query(String nickName);
}
