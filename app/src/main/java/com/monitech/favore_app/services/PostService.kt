package com.monitech.favore_app.services

import Interface.PostHolderApi
import com.monitech.favore_app.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService {
    private val retrofit = ServiceBuilder.buildService(PostHolderApi::class.java)

    fun createPost(post: Post, onResult: (Post?) -> Unit){
        retrofit.createPost(post).enqueue(
            object: Callback<Post> {
                override fun onResponse( call: Call<Post>, response: Response<Post>) {
                    val addedPost = response.body()
                    onResult(addedPost)
                }
                override fun onFailure(call: Call<Post>, t: Throwable) {
                    t?.printStackTrace()
                }
            }
        )
    }
    fun getAllPosts(onResult: (List<Post>) -> Unit){
        retrofit.getAllPosts().enqueue(
            object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    val posts = response?.body()
                    if (posts != null) {
                        onResult(posts)
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    t?.printStackTrace()
                }

            }
        )
    }
    fun updatePost(postId: Int, updatedPost: Post, onResult: (Post?) -> Unit){
        retrofit.updatePost(postId, updatedPost).enqueue(
            object: Callback<Post> {
                override fun onResponse( call: Call<Post>, response: Response<Post>) {
                    val updatedPost = response.body()
                    onResult(updatedPost)
                }
                override fun onFailure(call: Call<Post>, t: Throwable) {
                    t?.printStackTrace()
                }
            }
        )
    }
}