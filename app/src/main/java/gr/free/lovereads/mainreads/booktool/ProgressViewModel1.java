package gr.free.lovereads.mainreads.booktool;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by zia on 2018/11/20.
 */
public class ProgressViewModel1 extends BaseViewModel {
    //    val dialogMessage = MutableLiveData<String>()
//    val dialogProgress = MutableLiveData<Int>()
    MutableLiveData<String> dialogMessage = new MutableLiveData();
    MutableLiveData<Integer> dialogProgress = new MutableLiveData();

}
