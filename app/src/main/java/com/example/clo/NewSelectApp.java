package com.example.clo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewSelectApp extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
     List<String> typeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myselect);

        listView = findViewById(R.id.listView);
        typeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, typeList);
        listView.setAdapter(adapter);

        fetchTypesFromServer();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = typeList.get(position);
                Intent intent = new Intent(NewSelectApp.this, Select_Names.class);
                intent.putExtra("SELECTED_TYPE", selectedType);
                startActivity(intent);
            }
        });
    }


    private void fetchTypesFromServer() {
        String url = "https://lamp.ms.wits.ac.za/home/s2651487/Select.php";

        RequestQueue queue = Volley.newRequestQueue(NewSelectApp.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] types = response.split("\n");
                        typeList.clear();
                        typeList.addAll(Arrays.asList(types));
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewSelectApp.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(stringRequest);
    }
}
