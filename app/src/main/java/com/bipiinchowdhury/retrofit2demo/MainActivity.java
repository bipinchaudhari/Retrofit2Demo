package com.bipiinchowdhury.retrofit2demo;

import android.drm.DrmUtils;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.bipiinchowdhury.retrofit2demo.adapter.RecyclerViewAdapter;
import com.bipiinchowdhury.retrofit2demo.model.Example;
import com.bipiinchowdhury.retrofit2demo.model.Item;
import com.bipiinchowdhury.retrofit2demo.rest.APIClient;
import com.bipiinchowdhury.retrofit2demo.rest.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    TextView textViewResponse;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Item> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button buttonRequest = (Button) findViewById(R.id.btn_request);
        textViewResponse = (TextView) findViewById(R.id.tv_reponse);
        recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter = new RecyclerViewAdapter(items);
        recyclerView.setAdapter(recyclerViewAdapter);
        setSupportActionBar(toolbar);

        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDemoURL();
            }
        });
    }

    private void fetchDemoURL(){
        APIInterface apiService = APIClient.getClient().create(APIInterface.class);

        Call<Example> call = apiService.fetchDemoAPI();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example>call, Response<Example> response) {
                Example example = response.body();
                Log.d(TAG, "response received: " + example.getItems().toString());
                //textViewResponse.setText(example.toString());
                textViewResponse.setVisibility(View.GONE);
                items.addAll(example.getItems());
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Example>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
