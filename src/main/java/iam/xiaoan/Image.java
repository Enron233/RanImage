package iam.xiaoan;

import com.alibaba.fastjson.JSONArray;
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

public class Image {
    public static net.mamoe.mirai.message.data.Image getImage(Group group, String dz, String key) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(dz);
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(),"utf8");
            JSONObject jsonObject = JSONObject.parseObject(content);
            String result = jsonObject.getString(key);
            URL url = new URL(result);
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            byte[] bytes = in.readAllBytes();
            ExternalResource ex = ExternalResource.create(bytes);
            net.mamoe.mirai.message.data.Image image = group.uploadImage(ex);
            ex.close();
            response.close();
            httpClient.close();
            return image;

        }
        return null;
    }
    public static net.mamoe.mirai.message.data.Image getSex(Group group) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.lolicon.app/setu/v2?r18=1");
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "utf8");
            JSONObject jsonObject = JSONObject.parseObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            JSONObject jsonObject2 = jsonObject1.getJSONObject("urls");
            URL url  = new URL(jsonObject2.getString("original"));
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            ExternalResource externalResource = ExternalResource.create(bytes);
            net.mamoe.mirai.message.data.Image image = group.uploadImage(externalResource);
            inputStream.close();
            httpClient.close();
            response.close();
            return image;
        }
        return null;
    }
}

