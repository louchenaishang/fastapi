package personal.louchen.fastapi.interfaces;

import personal.louchen.fastapi.vos.HelloWorldWrapper;

/**
 * Created by louchen on 16/8/23.
 */
public interface HelloworldService {

    HelloWorldWrapper generateWrapper(String who) throws Exception;

}
