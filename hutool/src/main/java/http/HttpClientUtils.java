package http;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

public class HttpClientUtils {
    @Test
    public void test1(){
        String s = HttpUtil.get("http://test.wbp.woa.com/api/delivery/delivery-info?sign=7e64a387af4cc511c2b44eb512e6aac9&app_id=2VlJzSGqJqhoeL8wl5IPANZjzJ4E2y-M&start_date=2024-01-30&end_date=2024-02-01&page_index=0&page_size=2000&timestamp=1734523649702");
        System.out.println("====");
        System.out.println(s);
        System.out.println("====");
        String s1 = HttpUtil.get("www.baidu.com");
        System.out.println("====");
        System.out.println(s1);
        System.out.println("====");
    }
}
