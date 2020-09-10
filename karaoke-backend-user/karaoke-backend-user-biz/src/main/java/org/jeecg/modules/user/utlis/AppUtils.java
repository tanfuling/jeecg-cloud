package org.jeecg.modules.user.utlis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 管理小程序信息
 */
@Data
@Component
public class AppUtils {

	public static final String appid = "tt771501a8e83f607f";

    public static final String secret = "8e2da02deeb5553f1e709227dd9b8195ccc6613b";



    public static String sendGet(String url, Map<String,String> param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?";//+param;
            for(Map.Entry<String,String> entry : param.entrySet()){
                urlNameString += entry.getKey() + "=" + entry.getValue() + "&";
            }
            urlNameString = urlNameString.substring(0, urlNameString.length() - 1);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "/*");
            connection.setRequestProperty("connection", "Keep - Alive");
            connection.setRequestProperty("user - agent",
                    "Mozilla / 4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "— >"+map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
