package gr.free.lovereads.mainreads.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.zia.easybookmodule.bean.Book;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import gr.free.lovereads.R;

/**
 * Create by guorui on 2019/11/8
 * Last update 2019/11/8
 * Description:
 */
public class BookListAdapter extends BGARecyclerViewAdapter<Book> {

    public BookListAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.listitme_booklist);
    }

    @Override
    protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, Book book) {
        viewHolderHelper.setText(R.id.item_book_name, book.getBookName());
        viewHolderHelper.setText(R.id.item_book_author, book.getAuthor());
        viewHolderHelper.setText(R.id.item_book_lastUpdateChapter, "最新：" + book.getLastChapterName());
        viewHolderHelper.setText(R.id.item_book_site, book.getBookName());
        viewHolderHelper.setText(R.id.item_book_lastUpdateTime, "更新：" + book.getLastUpdateTime());

    }

    @Override
    public void setItemChildListener(BGAViewHolderHelper viewHolderHelper, int viewType) {
//        viewHolderHelper.setItemChildClickListener(R.id.etdtu_item_tv_name);
    }

}