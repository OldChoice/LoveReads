package gr.free.lovereads.mainreads.booktool;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.engine.EasyBook;
import com.zia.easybookmodule.rx.Disposable;
import com.zia.easybookmodule.rx.StepSubscriber;

import java.util.List;

/**
 * Create by guorui on 2019/11/26
 * Last update 2019/11/26
 * Description:
 */
public class SearchViewModel1 extends ProgressViewModel1 {

    MutableLiveData<List<Book>> loadBooks = new MutableLiveData<>();
    MutableLiveData<List<Book>> partBooks = new MutableLiveData<>();
    private Disposable searchDisposable = null;

    public void search(String bookName) {
        searchDisposable = EasyBook.search(bookName)
                .subscribe(new StepSubscriber<List<Book>>() {
                    @Override
                    public void onFinish(@NonNull List<Book> books) {
                        loadBooks.postValue(books);
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                        error.postValue(e);
                    }

                    @Override
                    public void onMessage(@NonNull String message) {
                        dialogMessage.postValue(message);
                    }

                    @Override
                    public void onProgress(int progress) {
                        dialogProgress.postValue(progress);
                    }

                    @Override
                    public void onPart(@NonNull List<Book> books) {
                        partBooks.postValue(books);
                    }
                });
    }

    public MutableLiveData<List<Book>> getLoadBooks() {
        return loadBooks;
    }

    public void setLoadBooks(MutableLiveData<List<Book>> loadBooks) {
        this.loadBooks = loadBooks;
    }

    public MutableLiveData<List<Book>> getPartBooks() {
        return partBooks;
    }

    public void setPartBooks(MutableLiveData<List<Book>> partBooks) {
        this.partBooks = partBooks;
    }

    @Override
    protected void onCleared() {
        shutDown();
        super.onCleared();
    }

    public void shutDown() {
        searchDisposable.dispose();
    }
}
