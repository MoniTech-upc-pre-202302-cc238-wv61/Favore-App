package com.monitech.favore_app.services

import com.monitech.favore_app.dto.UserCreateDTO
import com.monitech.favore_app.`interface`.UserHolderApi
import com.monitech.favore_app.models.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService {
    private val retrofit = ServiceBuilder.buildService(UserHolderApi::class.java)

    fun createUser(user: UserCreateDTO, onResult: (User?, String?) -> Unit){
        retrofit.createUser(user).enqueue(
            object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val addedUser = response.body()
                        onResult(addedUser,null)
                    } else {
                        val errorBodyString = response.errorBody()?.string()
                        // Parse the error JSON string to extract the "message" field
                        val errorMessage = try {
                            val jsonObject = JSONObject(errorBodyString)
                            val message = jsonObject.getString("message")
                            message
                        } catch (e: Exception) {
                            e.printStackTrace()
                            "Error parsing error body"
                        }

                        onResult(null, errorMessage)
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    t?.printStackTrace()
                }
            }
        )
    }
}