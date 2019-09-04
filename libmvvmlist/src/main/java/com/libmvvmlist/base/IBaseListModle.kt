package com.libmvvmlist.base

/**
 * 描述：
 * @author zhangzexin
 * @time   2019/8/30
 */
interface IBaseListModle<E> {

    fun getData():List<E>

    fun getEvent():LoadEvent

    fun getErrCode():Int
}