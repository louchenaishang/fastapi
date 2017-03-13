package personal.louchen.fastapi.utils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by louchen on 16/9/6.
 */
public class MapUtil {

    /**
     * key1=value1&key2=value2 转map
     * @param query
     * @return map
     */
    public static SortedMap<Object, Object> queryStringToMap(String query)
    {
        String[] params = query.split("&");
        SortedMap<Object, Object> map = new TreeMap<>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

}
