package com.sample.viewmodelwithlivedata.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sample.viewmodelwithlivedata.R;
import com.sample.viewmodelwithlivedata.view_models.TimerViewModel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvCount = findViewById(R.id.tv_count);
        TimerViewModel timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        timerViewModel.setCountLiveData(timerViewModel.getCountLiveData().getValue());
        timerViewModel.getCountLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvCount.setText(String.valueOf(integer));
            }
        });
    }
}