package com.wdh.jetpack.ui.fragment.message;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;


public class MessageModel extends BaseObservable {

    private final String firstName;
    private final ObservableBoolean isAdult = new ObservableBoolean();
    final ObservableField<String> bottomTxt =  new ObservableField<>();
    final ObservableBoolean isVisibility = new ObservableBoolean();

    MessageModel(String firstName) {
        this.firstName = firstName;

    }

    //notifyPropertyChanged(BR.);//刷新

    public ObservableBoolean getIsAdult() {
        return isAdult;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public ObservableField<String> getBottomTxt() {
        return bottomTxt;
    }

    public ObservableBoolean getIsVisibility() {
        return isVisibility;
    }
}
