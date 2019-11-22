package gr.free.lovereads.mainreads.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import gr.free.lovereads.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        startActivity(new Intent(this, MainActivity.class));
        startActivity(new Intent(this, SearchBookListActivity.class));
        finish();

    }
}
