package com.libmvvmlist

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * 描述：基础列表基类
 * @author zhangzexin
 * @time   2019/8/29
 */
abstract class BaseListFragment<VH:RecyclerView.ViewHolder>: BaseStatusFragment() {
    public var mAdapter: RecyclerView.Adapter<VH>? = null
    public var mRecycleListView: RecyclerView? = null

    override fun initViews(mRootView: View?) {
        initRecyclerView()
    }

    fun initRecyclerView() {
        mRecycleListView = createRecyclerView()
        mRecycleListView?.layoutManager = createLayoutManager()
        mAdapter = createAdapter()
        mRecycleListView?.adapter = mAdapter
    }

    abstract fun createAdapter(): RecyclerView.Adapter<VH>

    abstract fun createLayoutManager(): RecyclerView.LayoutManager?

    abstract fun createRecyclerView(): RecyclerView?

}