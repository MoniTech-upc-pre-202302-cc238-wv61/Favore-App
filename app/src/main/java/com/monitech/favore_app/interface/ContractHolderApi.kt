import com.monitech.favore_app.models.Contract
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ContractHolderApi {

    @GET("contracts")
    fun getAllContracts(): Call<List<Contract>>

    @GET("contracts/{id}")
    fun getContractById(@Path("id")id:Int):Call<Contract>

    @POST("contracts")
    fun createContract(@Body contract:Contract):Call<Contract>

    @PUT("contracts/{id}")
    fun updateContract(@Path("id") id: Int, @Body updatedContract:Contract):Call<Contract>

    @DELETE("contracts/{id}")
    fun deleteContract(@Path("id")id:Int):Call<Contract>
}