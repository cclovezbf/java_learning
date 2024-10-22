package jwt;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
//https://blog.csdn.net/Innocence_0/article/details/139309957
public class JWTDemo {
    @Test
    public void test1() {
        //创建
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;

            {
                put("uid", Integer.parseInt("123"));
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
            }
        };
        JWTUtil.createToken(map, "1234".getBytes());
        //解析
        String rightToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwiYWRtaW4iOnRydWUsIm5hbWUiOiJsb29seSJ9." +
                "U2aQkC2THYV9L0fTN-yBBI7gmo5xhmvMhATtu8v0zEA";

        final JWT jwt = JWTUtil.parseToken(rightToken);

        Object header = jwt.getHeader(JWTHeader.TYPE);
        Object sub = jwt.getPayload("sub");
        System.out.println("header="+header+",sub="+sub);

        //验证
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2MjQwMDQ4MjIsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV_op5LoibLkuozlj7ciLCJzeXNfbWVudV8xIiwiUk9MRV_op5LoibLkuIDlj7ciLCJzeXNfbWVudV8yIl0sImp0aSI6ImQ0YzVlYjgwLTA5ZTctNGU0ZC1hZTg3LTVkNGI5M2FhNmFiNiIsImNsaWVudF9pZCI6ImhhbmR5LXNob3AifQ." +
                "aixF1eKlAKS_k3ynFnStE7-IRGiD5YaqznvK2xEjBew";

        JWTUtil.verify(token, "123456".getBytes());
    }
}
