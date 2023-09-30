package com.monitech.favore_app.services

import com.monitech.favore_app.dto.UserCreateDTO
import com.monitech.favore_app.`interface`.UserHolderApi
import com.monitech.favore_app.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService {
    private val retrofit = ServiceBuilder.buildService(UserHolderApi::class.java)

    fun createUser(user: UserCreateDTO, onResult: (User?) -> Unit){
        retrofit.createUser(user).enqueue(
            object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    t?.printStackTrace()
                }
            }
        )
    }
}