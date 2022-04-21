package com.samsdk.androidmvp_b13.presenter

import com.samsdk.androidmvp_b13.model.Post

interface MainPresenterImpl {
    fun apiPostList()
    fun apiPostDelete(post: Post)
}