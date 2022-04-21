package com.samsdk.androidmvp_b13.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsdk.androidmvp_b13.R
import com.samsdk.androidmvp_b13.adapter.PostAdapter
import com.samsdk.androidmvp_b13.databinding.ActivityMainBinding
import com.samsdk.androidmvp_b13.model.Post
import com.samsdk.androidmvp_b13.presenter.MainPresenter
import com.samsdk.androidmvp_b13.utils.Utils
import com.samsdk.androidmvp_b13.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mainPresenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter(this)
        initViews()

    }

    private fun initViews() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        mainPresenter.apiPostList()
    }

    private fun refreshAdapter(posters: ArrayList<Post>) {
        val adapter = PostAdapter(this,posters)
        binding.recyclerView.adapter = adapter
    }

    override fun onPostListSuccess(posts: ArrayList<Post>) {
        refreshAdapter(posts)
    }

    override fun onPostListFailure(error: String) {
        toast(error)
    }

    override fun onPostDeleteSuccess(post: Post?) {
        mainPresenter.apiPostList()
    }

    override fun onPostDeleteFailure(error: String) {
        toast(error)
    }
    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}