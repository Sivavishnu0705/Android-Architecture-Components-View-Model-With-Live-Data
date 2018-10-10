package com.sample.viewmodelwithlivedata.view_models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class TimerViewModel extends ViewModel {
    private Timer myTimer = new Timer();
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> getCountLiveData() {
        return countLiveData;
    }

    public void setCountLiveData(Integer count) {
        if (count == null) {
            this.countLiveData.setValue(0);
            startTimer();
        } else {
            this.countLiveData.setValue(count);//If the value is set from main thread use setValue().Otherwise use postValue() method.
        }
    }

    private void startTimer() {
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Integer count = countLiveData.getValue();
                countLiveData.postValue(count == null ? 0 : count + 1);//If the value is set from main thread use setValue().Otherwise use postValue() method.
            }
        }, 0, 1000);
    }
}