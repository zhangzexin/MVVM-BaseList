package com.libmvvmlist

import android.support.annotation.DrawableRes
import android.view.View

/**
 * 描述：
 * @author zhangzexin
 * @time   2019/8/28
 */
interface IStatusView {

    fun showLoading()

    fun showError()

    fun showEmpty()

    fun showContent()

    fun setOnErrorClickListener(listener: View.OnClickListener)
}