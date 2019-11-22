package gr.free.lovereads.mainreads.activity;

import android.os.Bundle;

import gr.free.lovereads.R;
import gr.free.lovereads.mainreads.activitybase.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
