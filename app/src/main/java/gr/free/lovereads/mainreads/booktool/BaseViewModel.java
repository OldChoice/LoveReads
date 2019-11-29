package gr.free.lovereads.mainreads.booktool;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Create by guorui on 2019/11/26
 * Last update 2019/11/26
 * Description:
 */
public class BaseViewModel extends ViewModel {
    public MutableLiveData<String> toast = new MutableLiveData<>();
    public MutableLiveData<Exception> error = new MutableLiveData<>();

    protected void onError(Exception e) {
        error.postValue(e);
    }

    protected void toast(String msg) {
        toast.postValue(msg);
    }
}
