package com.wdh.mainlibrary.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainFragViewModel extends ViewModel {

    //    final ObservableField<String> cityName =  new ObservableField<>();
// Create a LiveData with a String
    public MutableLiveData<String> cityName;

    public MutableLiveData<String> getCurrentName() {
        if (cityName == null) {
            cityName = new MutableLiveData<>();
        }
        return cityName;
    }

// Rest of the ViewModel...


}
