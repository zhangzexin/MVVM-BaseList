package com.libmvvmlist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.libmvvmlist.base.IBaseListModle
import com.libmvvmlist.base.LoadEvent
import com.libmvvmlist.viewmodel.BaseDataViewModel

/**
 * 描述：数据绑定页面
 * @author zhangzexin
 * @time   2019/8/30
 */
abstract class BaseListBindDataFragment<VH: RecyclerView.ViewHolder, DATA: IBaseListModle<*>> : BaseListFragment<VH>() {

    var mViewModel: BaseDataViewModel<DATA>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fristLoadData()
    }

    private fun fristLoadData() {
        if (mAdapter?.itemCount == 0) {
            mViewModel?.loadData(LoadEvent.EVENT_LOAD_INIT_OR_RETRY)
        }
    }

    public fun obtainViewModel(activity: FragmentActivity, clz: Class<BaseDataViewModel<DATA>>): BaseDataViewModel<DATA> {
        return  ViewModelProviders.of(activity).get(clz)
    }

    public fun obtainViewModel(fragment: Fragment, clz: Class<BaseDataViewModel<DATA>>): BaseDataViewModel<DATA> {
        return  ViewModelProviders.of(fragment).get(clz)
    }

    public fun loadData(data: DATA) {
        if (data.getEvent() == LoadEvent.EVENT_LOAD_INIT_OR_RETRY) {
            loadInitOrRetry(data)
        } else if (data.getEvent() == LoadEvent.EVENT_PULL_TO_REFRESH) {
            loadPullToRefresh(data)
        } else if (data.getEvent() == LoadEvent.EVENT_LOAD_MORE) {
            loadMore(data)
        }
        loadEnd(data)
    }

    public fun loadMore(data: DATA) {
        if (data.getErrCode() == 0 && data.getData() != null) {
            addData(data.getData())
        } else {
            onLoadMoreError()
        }
    }

    public fun loadPullToRefresh(data: DATA) {
        if (data.getErrCode() == 0  && !data.getData().isEmpty()) {
            setNewData(data.getData())
        } else if (data.getErrCode() == 0 && data.getData().isEmpty()){
            showEmpty()
        } else {
            onRefreshError()
        }
    }

    public fun loadEnd(data: DATA) {
        if (data?.getData()?.size > 0) {
            loadMoreComplete()
        } else {
            loadMoreEnd()
        }
    }

    fun loadInitOrRetry(data: DATA) {
        if (data.getErrCode() == 0 && data.getData() != null && !data.getData().isEmpty()) {
            setNewData(data.getData())
        } else if (data.getErrCode() == 0&& data.getData()!= null && data.getData().isEmpty()){
            showEmpty()
        } else {
            onLoadInitOrRetryError()
        }
    }

    abstract fun createViewModel()

    abstract fun setNewData(data: List<Any?>)
    abstract fun addData(data: List<Any?>)

    abstract fun loadMoreEnd()
    abstract fun loadMoreComplete()

    abstract fun onLoadMoreError()
    abstract fun onLoadInitOrRetryError()
    abstract fun onRefreshError()

}