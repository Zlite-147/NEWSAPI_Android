 package com.example.newsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapi.Adapter.CustomRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    RequestQueue mQueue;

     RecyclerView recyclerView;
     RecyclerView.Adapter mAdapter;
     List<Model> modelList;



     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

         recyclerView.setLayoutManager(new LinearLayoutManager(this));


        modelList = new ArrayList<>();
        jsonParse();
    }

     private void jsonParse() {
      //Replace ur API Key
         String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=f3a591da3bb2428eb71***********";


         JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                 new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                         try {

                             JSONArray jsonArray = response.getJSONArray("articles");
                             for (int i = 1; i <=jsonArray.length(); i++) {
                                 Model modelUtils = new Model();
                                 JSONObject articles = jsonArray.getJSONObject(i);
                                 modelUtils.setTitle(articles.getString("title"));
                                 modelUtils.setDescription(articles.getString("description"));
                                 modelUtils.setUrlToImage(articles.getString("urlToImage"));
                                 modelUtils.setUrl(articles.getString("url"));
                                 modelList.add(modelUtils);
                             }
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                         mAdapter = new CustomRecyclerAdapter(MainActivity.this, modelList);

                         recyclerView.setAdapter(mAdapter);
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
             }
         });
         mQueue.add(request);

     }
 }
