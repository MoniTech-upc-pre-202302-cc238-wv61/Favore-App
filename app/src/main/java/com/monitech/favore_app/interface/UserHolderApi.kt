package com.monitech.favore_app.`interface`

import com.monitech.favore_app.dto.UserCreateDTO
import com.monitech.favore_app.dto.UserLoginDTO
import com.monitech.favore_app.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserHolderApi {

    @POST("users")
    fun createUser(@Body userCreateDTO: UserCreateDTO):Call<User>
}