package personal.louchen.fastapi.services.abstracts;

import personal.louchen.fastapi.daos.hdao.HibernateDao;
import personal.louchen.fastapi.daos.hdao.HibernateDaoImpl;
import personal.louchen.fastapi.daos.rdao.RedisDao;
import personal.louchen.fastapi.daos.rdao.RedisDaoImpl;
import personal.louchen.fastapi.utils.BeanFactoryUtil;
import org.springframework.beans.factory.InitializingBean;

import javax.persistence.EntityManager;

/**
 * Created by louchen on 16/8/23.
 */
public abstract class AbstractServiceImpl implements InitializingBean{

    protected RedisDao redisDao;
    protected HibernateDao hibernateDao;
    protected EntityManager entityManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisDao = BeanFactoryUtil.getBean(RedisDaoImpl.class);
        hibernateDao =  BeanFactoryUtil.getBean(HibernateDaoImpl.class);
        entityManager = BeanFactoryUtil.getBean(EntityManager.class);
    }

}
