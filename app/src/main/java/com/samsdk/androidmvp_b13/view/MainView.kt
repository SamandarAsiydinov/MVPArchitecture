package com.samsdk.androidmvp_b13.view

import com.samsdk.androidmvp_b13.model.Post

interface MainView {

    fun onPostListSuccess(posts: ArrayList<Post>)
    fun onPostListFailure(error: String)

    fun onPostDeleteSuccess(post: Post?)
    fun onPostDeleteFailure(error: String)
}