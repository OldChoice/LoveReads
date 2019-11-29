package gr.free.lovereads.mainreads.activity;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.zia.easybookmodule.bean.Book;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import gr.free.grfastuitils.activitybase.BaseActivity;
import gr.free.grfastuitils.myview.DeletableEditText;
import gr.free.grfastuitils.myview.DividerDecoration;
import gr.free.grfastuitils.tools.MyToast;
import gr.free.lovereads.R;
import gr.free.lovereads.mainreads.adapter.BookListAdapter;
import gr.free.lovereads.mainreads.booktool.SearchViewModel1;

/**
 * Create by guorui on 2019/11/28
 * Last update 2019/11/28
 * Description:测试的Java代码模块
 */
public class SearchBookListActivity1 extends BaseActivity implements View.OnClickListener, BGAOnItemChildClickListener, BGAOnRVItemClickListener {
    private TextView tvTitle;
    private ImageView ivBack;
    private DeletableEditText etNumber;
    private RecyclerView recyclerView;
    private BookListAdapter adapter;
    private TextView tvNone;

    public SearchViewModel1 viewModel1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book_list);

        initValues();
        findView();
        setView();
        setListener();
        initObservers();
        search("天行");

        MyToast.showShort("aaaaa");


    }

    private void initObservers() {

        viewModel1.getLoadBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                if (books != null) {
                    System.out.println(books.size());
                    System.out.println((books.get(1).getBookName()));
                    MyToast.showShort("搜索到" + books.size() + "本书籍");
                }
            }
        });

        viewModel1.getPartBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                if (books != null) {
                    System.out.println(books.size() + "----" + books.get(0).getBookName());
                }
            }
        });
        viewModel1.error.observe(this, new Observer<Exception>() {
            @Override
            public void onChanged(Exception e) {
                MyToast.showShort(e.getMessage());
            }
        });
        viewModel1.toast.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                MyToast.showShort(s);
            }
        });

    }

    @Override
    public void initValues() {

        viewModel1 = ViewModelProviders.of(this).get(SearchViewModel1.class);
    }

    @Override
    public void findView() {
        tvTitle = findViewById(R.id.activity_topback_titile);
        ivBack = findViewById(R.id.activity_topback_back);
        etNumber = findViewById(R.id.search_view);
        tvNone = findViewById(R.id.search_number_none);
        recyclerView = findViewById(R.id.search_number_recycle);
        adapter = new BookListAdapter(recyclerView);


    }

    private void setRecycle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchBookListActivity1.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHorizontalScrollBarEnabled(false);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerDecoration(SearchBookListActivity1.this, LinearLayoutManager.VERTICAL, Color.parseColor("#f4f4f4"), 1, 0, 0));


    }

    @Override
    public void setView() {
        tvTitle.setText("搜索");
        setRecycle();

    }

    @Override
    public void setListener() {
        ivBack.setOnClickListener(this);
        etNumber.setOnKeyListener(keyListener);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnRVItemClickListener(this);
        etNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    adapter.clear();
                    tvNone.setVisibility(View.GONE);
                    return;
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_topback_back:
                finish();
                break;
        }

    }

    /**
     * 搜索触发事件
     */
    private View.OnKeyListener keyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
//                getNumberLists();
                clearInputMethod();
                MyToast.showShort(etNumber.getText().toString());
            }
            return false;
        }
    };

    /**
     * 让软键盘消失
     */
    protected void clearInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
    }

    private void search(String name) {
        viewModel1.shutDown();
        if (name != null && name.trim().equals("")) {
            viewModel1.search(name);
        }
    }


}