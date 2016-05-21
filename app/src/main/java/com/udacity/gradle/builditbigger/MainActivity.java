package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.my.myapplication.jokegcm.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.nguyen.activitylibrary.JokeActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String mJoke = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask().execute();
        if (mJoke == null) {
            Toast.makeText(this, "Jokes are not available at this moment. Please try again in a few seconds.", Toast.LENGTH_LONG);
        } else {
            Intent intent = JokeActivity.newIntent(this, mJoke);
            startActivity(intent);
        }
    }

    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;

        @Override
        protected String doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                /*
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                      // options for running against local devappserver
                      // - 10.0.2.2 is localhost's IP address in Android emulator
                      // - turn off compression when running against local devappserver
                      .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                      // .setRootUrl("http://192.168.0.3:8080/_ah/api/")
                      .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                          @Override
                          public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                              abstractGoogleClientRequest.setDisableGZipContent(true);
                          }
                      });
                      */
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                      .setRootUrl("https://udacity-4-1318.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            try {
                return myApiService.sayHi("NGUYEN").execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            assert result != null && !result.isEmpty();
            mJoke = result;
        }
    }
}
