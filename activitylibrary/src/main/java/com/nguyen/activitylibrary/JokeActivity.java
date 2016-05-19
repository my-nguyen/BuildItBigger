package com.nguyen.activitylibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by My on 5/18/2016.
 */
public class JokeActivity extends AppCompatActivity {
   public static Intent newIntent(Context context, String joke) {
      Intent intent = new Intent(context, JokeActivity.class);
      intent.putExtra("JOKE_IN", joke);
      return intent;
   }

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_joke);
      String joke = getIntent().getStringExtra("JOKE_IN");
      TextView jokeText = (TextView)findViewById(R.id.text);
      Button dismiss = (Button)findViewById(R.id.dismiss);
      jokeText.setText(joke);
      dismiss.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            finish();
         }
      });
   }
}
