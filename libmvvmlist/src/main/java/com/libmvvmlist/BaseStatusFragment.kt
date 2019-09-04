package com.libmvvmlist

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 描述：页面状态基类
 * @author zhangzexin
 * @time   2019/8/28
 */
abstract class BaseStatusFragment : Fragment(),IStatusView {

    var mStatusView: IStatusView? = null

    var mRootView: View? = null

    var isFragmentCreated = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(), null)
            initStatusView()
            initViews(mRootView)
            if (userVisibleHint) {
                onFragmentVisible()
            }
        } else{
            val parent = mRootView!!.getParent() as ViewGroup
            parent?.removeView(mRootView)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFragmentCreated = true
    }

    override fun onResume() {
        super.onResume()
        //getUserVisibleHint() 方法的mUserVisibleHint是由FragmentPagerAdapter管理的,activity中的getUserVisibleHint 为默认值true;
        if (userVisibleHint) {
            onFragmentVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            onFragmentInVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isFragmentCreated) {
            return
        }
        if (isVisibleToUser) {
            onFragmentVisible()
        } else {
            onFragmentInVisible()
        }
    }

    //调用逻辑:当页面onPause 或者 viewpager 切换导致的 setUserVisibleHint(false) 时调用
    private fun onFragmentInVisible() {

    }

    //调用逻辑:当页面onResume 或者 viewpager 切换导致的 setUserVisibleHint(true) 时调用
    public fun onFragmentVisible() {

    }

    private fun initStatusView() {
        mStatusView = createStatusView()
        mStatusView?.setOnErrorClickListener(View.OnClickListener { onErrorViewClicked() })
    }

    override fun showEmpty() {
        mStatusView?.showEmpty()
    }

    override fun showLoading() {
        mStatusView?.showLoading()
    }

    override fun showContent() {
        mStatusView?.showContent()
    }

    override fun showError() {
        mStatusView?.showError()
    }

    /**
     * error状态时的点击响应
     */
    abstract fun onErrorViewClicked()

    /**
     * 获取状态控件
     */
    abstract fun createStatusView(): IStatusView?

    /**
     * 初始化View
     */
    abstract fun initViews(mRootView: View?)

    /**
     * 获取根布局
     * @return 布局资源文件
     */
    abstract fun getLayoutRes(): Int
}