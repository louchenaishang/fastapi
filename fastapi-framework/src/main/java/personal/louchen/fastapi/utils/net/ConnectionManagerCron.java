package personal.louchen.fastapi.utils.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * Created by louchen on 16/7/29.
 */

public class ConnectionManagerCron {

    private  static Logger logger = LoggerFactory.getLogger(ConnectionManagerCron.class);

    /**
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void close(){
        //HttpUtil.getConnectionManager().closeExpiredConnections();
        HttpUtil.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
    }

}
