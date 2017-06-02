package com.bipiinchowdhury.retrofit2demo;

import android.drm.DrmUtils;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.bipiinchowdhury.retrofit2demo.model.Example;
import com.bipiinchowdhury.retrofit2demo.rest.APIClient;
import com.bipiinchowdhury.retrofit2demo.rest.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "want to fetch webservice? ", Snackbar.LENGTH_LONG)
                        .setAction("FETCH", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d(TAG,"snack bar button clicked");
                                //TODO fetch webservice using retro fit
                                fetchDemoURL();;
                            }
                        });
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
                Log.d(TAG, "Number of movies received: " + example.toString());
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
