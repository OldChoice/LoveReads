package gr.free.lovereads.mainreads.activity

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.zia.easybookmodule.bean.Book
import gr.free.grfastuitils.activitybase.BaseActivity
import gr.free.grfastuitils.tools.LoadUtils
import gr.free.lovereads.R
import gr.free.lovereads.mainreads.adapter.BookAdapter
import gr.free.lovereads.mainreads.booktool.SearchViewModel
import kotlinx.android.synthetic.main.activity_search_book_list.*
import kotlinx.android.synthetic.main.activity_topback.*

/**
 * Create by guorui on 2019/11/28
 * Last update 2019/11/28
 * Description:搜索书籍列表
 */
class SearchBookListActivity : BaseActivity(), View.OnClickListener, BookAdapter.BookSelectListener {

    private lateinit var viewModel: SearchViewModel
    //    private var adapter: BookListAdapter? = null
    var bookAdapter: BookAdapter? = null
    private var tvNone: TextView? = null
    private var loadingDialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book_list)
        initValues()
        findView()
        setView()
        setListener()

        initObservers()

    }

    override fun initValues() {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

    }

    override fun findView() {
        tvNone = findViewById(R.id.search_number_none)
        // adapter = BookListAdapter(search_number_recycle)
        bookAdapter = BookAdapter(this)
        search_number_recycle.layoutManager = LinearLayoutManager(this)
        search_number_recycle.adapter = bookAdapter
    }

    override fun setView() {
        activity_topback_titile.text = "搜索"
        // setRecycle()
    }

    override fun setListener() {
        activity_topback_back.setOnClickListener(this)
        //搜索触发事件
        search_view.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                search(search_view.text.toString())
                clearInputMethod()
                // CommClass.dateDiff()
            }
            false
        })


    }

    /**
     * 让软键盘消失
     */
    protected fun clearInputMethod() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

//    //快速设置列表
//    private fun setRecycle() {
//        val linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        search_number_recycle.layoutManager = linearLayoutManager
//        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        search_number_recycle.isHorizontalScrollBarEnabled = false
//        search_number_recycle.adapter = adapter
//        search_number_recycle.addItemDecoration(DividerDecoration(this, LinearLayoutManager.VERTICAL, Color.parseColor("#f4f4f4"), 1, 0, 0))
//    }

    //查询列表搜索后返回的数据
    private fun initObservers() {
        viewModel.loadBooks.observe(this, Observer<List<Book>> {
            if (it != null) {
                loadingDialog?.dismiss()
//                println(it.size)
                Toast.makeText(this, "搜索到${it.size}本书籍", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.partBooks.observe(this, Observer {
            if (it != null) {
//                println(it.size.toString())
//                adapter?.data=it
//                adapter?.addMoreData(it)
                search_number_recycle.post {
                    bookAdapter?.addBooks(search_view.text.toString(), it)
                    search_number_recycle?.scrollToPosition(0)
                }

            }
        })

        viewModel.error.observe(this, Observer {
            it?.printStackTrace()
//            Toast.makeText(this, it?.message, Toast.LENGTH_LONG).show()
//            println(it?.message+"------------")
        })

        viewModel.toast.observe(this, Observer {
            //            ToastUtil.onInfo(it)

        })

    }

    //搜索
    private fun search(name: String) {
        if (name != null && name.isNotEmpty()) {
            viewModel.shutDown()
            viewModel.search(name)
            loadingDialog = LoadUtils().showBaseDialog(this, loadingDialog, "正在搜索书籍")
        }
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.activity_topback_back -> finish()
//            R.id.activity_topback_back -> {
//                finish()
//            }


        }
    }

    override fun onBookSelect(itemView: View, book: Book) {
//        val intent = Intent(context, BookActivity::class.java)
//        intent.putExtra("book", book)
//        intent.putExtra("scroll", false)
//        startActivity(intent)
    }
}
