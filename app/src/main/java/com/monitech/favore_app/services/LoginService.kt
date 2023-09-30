package com.monitech.favore_app.services
import com.monitech.favore_app.`interface`.LoginHolderApi
import com.monitech.favore_app.dto.UserLoginDTO
import com.monitech.favore_app.models.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService {
    private val retrofit = ServiceBuilder.buildService(LoginHolderApi::class.java)

    fun login(userLoginDTO: UserLoginDTO, onResult: (User?, String?) -> Unit){
        retrofit.login(userLoginDTO).enqueue(
            object: Callback<User> {
                override fun onResponse( call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        onResult(user, null)
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