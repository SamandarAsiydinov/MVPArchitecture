package com.samsdk.androidmvp_b13.presenter

import android.widget.Toast
import com.samsdk.androidmvp_b13.model.Post
import com.samsdk.androidmvp_b13.network.RetrofitHttp
import com.samsdk.androidmvp_b13.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val mainView: MainView) : MainPresenterImpl {

    override fun apiPostList() {
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<Post>> {
            override fun onResponse(
                call: Call<ArrayList<Post>>,
                response: Response<ArrayList<Post>>
            ) {
                mainView.onPostListSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                mainView.onPostListFailure(t.message.toString())
            }
        })
    }

    override fun apiPostDelete(post: Post) {
        RetrofitHttp.postService.deletePost(post.id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                mainView.onPostDeleteSuccess(response.body())
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.message.toString())
            }
        })
    }
}