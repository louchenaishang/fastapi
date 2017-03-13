package personal.louchen.fastapi.services.parameter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Parameter {

    @Value("${api.key}")
    private String apiKey;//api秘钥

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

}
