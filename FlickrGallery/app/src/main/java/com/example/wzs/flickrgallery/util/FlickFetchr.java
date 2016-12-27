package com.example.wzs.flickrgallery.util;

import android.util.Log;

import com.example.wzs.flickrgallery.data.PhotoItem;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZeshuang on 2016/12/22.
 */

public class FlickFetchr {
    private static final String TAG = "FlickFetchr";
    private static final String API_KEY = "REPLACE_ME_WITH_A_REAL_KEY";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public ArrayList<PhotoItem> fetchItems(){
        ArrayList<PhotoItem> items = new ArrayList<>();
        StringBuilder url = new StringBuilder();
        url.append("https://api.flickr.com/services/rest/?");
        url.append("method=flickr.photos.getRecent");
        url.append("&api_key=" + API_KEY);
        url.append("&extras=url_s");
        url.append("&format=json");
        url.append("&nojsoncallback=1");

        String jsonString;
        try {
            jsonString = getUrlString(url.toString());
            Log.i(TAG, "fetchItems: "+jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItemsGson(items,jsonBody);
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch items", e);
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse JSON", e);
        }
        return items;
    }
    private void parseItemsGson(List<PhotoItem> items, JSONObject json) throws JSONException {
        Gson gson = new Gson();
        JSONObject photosJsonObject = json.getJSONObject("photos");
        Log.i(TAG, "parseItemsGson: "+photosJsonObject);
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");
        Log.i(TAG, "parseItemsGson: "+photoJsonArray);
        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);
            PhotoItem item;
            item = gson.fromJson(String.valueOf(photoJsonObject),PhotoItem.class);
            items.add(item);
        }
    }
}
