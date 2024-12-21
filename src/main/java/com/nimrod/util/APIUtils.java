package com.nimrod.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.util.List;


public class APIUtils {
    public static String API_BASE_URL = "http://207.246.73.89:5029";

    public static List<Kit> getKits() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(API_BASE_URL + "/kits");
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);

                Gson gson = new Gson();
                Type userListType = new TypeToken<List<Kit>>() {}.getType();
                List<Kit> kits = gson.fromJson(responseString, userListType);
                return kits;
            }
        }
    }

    public static boolean updateKitStock(String id, boolean inStock) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet getRequest = new HttpGet(API_BASE_URL + "/kits");
            try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);

                Gson gson = new Gson();
                Type userListType = new TypeToken<List<Kit>>() {}.getType();
                List<Kit> kits = gson.fromJson(responseString, userListType);

                Kit kit = null;
                for (Kit iter : kits) {
                    if (iter._id().equals(id)) {
                        kit = iter;
                        break;
                    }
                } 

                if (kit == null) {
                    return false;
                }

                HttpPut putRequest = new HttpPut(API_BASE_URL + "/kits/" + kit._id());
                putRequest.setHeader("Content-Type", "application/json");

                JsonObject body = new JsonObject();
                body.addProperty("inStock", inStock);

                putRequest.setEntity(new StringEntity(body.toString()));

                try (CloseableHttpResponse response2 = httpClient.execute(putRequest)) {
                    int statusCode = response2.getStatusLine().getStatusCode();
                    if (statusCode >= 400) {
                        throw new Exception();
                    }
                }

                return true;
            }
        }
    }

    public record Kit(String _id, String name, String description, String kitId, boolean inStock) {}
}
