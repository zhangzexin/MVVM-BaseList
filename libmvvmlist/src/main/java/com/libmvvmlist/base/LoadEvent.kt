package com.libmvvmlist.base

/**
 * 描述：加载数据时的事件
 * @author zhangzexin
 * @time   2019/8/30
 */
enum class LoadEvent {
    EVENT_DEFAULT,
    EVENT_LOAD_INIT_OR_RETRY,
    EVENT_PULL_TO_REFRESH,
    EVENT_LOAD_MORE
}