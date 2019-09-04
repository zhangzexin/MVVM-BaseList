package com.libmvvmlist.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.libmvvmlist.base.IBaseListModle
import com.libmvvmlist.base.LoadEvent

/**
 * 描述：列表viewmodel基类
 * @author zhangzexin
 * @time   2019/9/3
 */
abstract class BaseDataViewModel<T : IBaseListModle<*>>(application: Application) :
    AndroidViewModel(application) {

    var mPage: Int = 0

    public fun loadData(loadEvent: LoadEvent) {
        if (loadEvent == LoadEvent.EVENT_LOAD_INIT_OR_RETRY) {
            mPage = 1
            doLoad(mPage,loadEvent)
        } else if (loadEvent == LoadEvent.EVENT_LOAD_MORE) {
            mPage++
            doLoad(mPage,loadEvent)
        } else if (loadEvent == LoadEvent.EVENT_PULL_TO_REFRESH) {
            mPage = 1
            doLoad(mPage,loadEvent)
        }
    }

    abstract fun doLoad(mPage: Int, loadEvent: LoadEvent)
}