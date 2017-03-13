package personal.louchen.fastapi.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * Created by louchen on 16/7/22.
 * 全局bean工厂
 */
public class BeanFactoryUtil implements BeanFactoryAware {

    private static BeanFactory factory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory = beanFactory;
    }

    public static Object getBean(String beanName){
        return factory.getBean(beanName);
    }

    public static <T> T getBean(String beanName,Class<T> clazz){
        return factory.getBean(beanName,clazz);
    }

    public static <T> T getBean(Class<T> clazz){
        return factory.getBean(clazz);
    }

}
