package com.samsdk.androidmvp_b13.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsdk.androidmvp_b13.activity.MainActivity
import com.samsdk.androidmvp_b13.databinding.ItemPostListBinding
import com.samsdk.androidmvp_b13.model.Post
import com.samsdk.androidmvp_b13.utils.Utils

class PostAdapter(
    private val activity: MainActivity,
    private val items: ArrayList<Post>
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = items[position]

        holder.binding.apply {
            textTitle.text = post.title
            textBody.text = post.body
            linearLayout.setOnLongClickListener {
                deletePostDialog(post)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostViewHolder(val binding: ItemPostListBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun deletePostDialog(post: Post) {
        val title = "Delete"
        val body = "Do you want to delete?"
        Utils.customDialog(activity, title, body, object : Utils.DialogListener {
            override fun onPositiveClick() {
                activity.mainPresenter.apiPostDelete(post)
            }

            override fun onNegativeClick() {}
        })
    }
}