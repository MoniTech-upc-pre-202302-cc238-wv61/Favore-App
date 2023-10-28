package Interface


import com.monitech.favore_app.models.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostHolderApi {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id")id:Int):Call<Post>

    @POST("posts")
    fun createPost(@Body post:Post):Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: Int, @Body updatedPost:Post):Call<Post>

    @PUT("posts/{id}")
    fun deletePost(@Path("id")id:Int):Call<Post>
}