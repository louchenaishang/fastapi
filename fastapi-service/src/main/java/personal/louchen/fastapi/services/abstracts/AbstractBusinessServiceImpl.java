package personal.louchen.fastapi.services.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import personal.louchen.fastapi.daos.jdao.UserDao;
import personal.louchen.fastapi.daos.jdao.UserLoginDao;
import personal.louchen.fastapi.daos.jdao.UserWealthDao;

/**
 * Created by louchen on 16/8/27.
 */
public abstract class AbstractBusinessServiceImpl extends AbstractServiceImpl {

    @Autowired
    protected UserDao userDao;
    @Autowired
    protected UserLoginDao userLoginDao;
    @Autowired
    protected UserWealthDao userWealthDao;

}
