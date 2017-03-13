package personal.louchen.fastapi.services.JService;

import personal.louchen.fastapi.interfaces.HelloworldService;
import personal.louchen.fastapi.services.abstracts.AbstractServiceImpl;
import personal.louchen.fastapi.vos.HelloWorldWrapper;
import org.springframework.stereotype.Service;

/**
 * Created by louchen on 16/8/23.
 */
@Service("helloworldService")
public class HelloworldServiceImpl extends AbstractServiceImpl implements HelloworldService {



    @Override
    public HelloWorldWrapper generateWrapper(String who) throws Exception {
        HelloWorldWrapper wrapper = new HelloWorldWrapper();
        wrapper.setWho(who);
        wrapper.setName("helloworld");
        wrapper.setPlatform("api/hellowold");
        return wrapper;
    }

}
