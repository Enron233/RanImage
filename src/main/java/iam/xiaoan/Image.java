package iam.xiaoan;

import com.alibaba.fastjson.JSONObject;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.utils.ExternalResource;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class Image {
    public static void main(String[] args) throws IOException {
        System.out.println(getSeUrl());
    }
    private static String getSeUrl() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.dmoe.cc/random.php?return=json");
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(),"utf8");
            JSONObject jsonObject = JSONObject.parseObject(content);
            String result = jsonObject.getString("imgurl");
            response.close();
            httpClient.close();
            return result;
        }
        return null;
    }
    public static net.mamoe.mirai.message.data.Image getSeImage(Group group) throws IOException {
        URL url = new URL(Objects.requireNonNull(getSeUrl()));
        URLConnection uc = url.openConnection();
        InputStream in = uc.getInputStream();
        byte[] bytes = in.readAllBytes();
        ExternalResource ex = ExternalResource.create(bytes);
        net.mamoe.mirai.message.data.Image image = group.uploadImage(ex);
        ex.close();
        return image;
    }
}

