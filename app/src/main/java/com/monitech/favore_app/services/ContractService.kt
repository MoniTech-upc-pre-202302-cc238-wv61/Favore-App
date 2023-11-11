package com.monitech.favore_app.services

import ContractHolderApi
import android.util.Log
import com.monitech.favore_app.models.Contract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContractService {
    private val retrofit = ServiceBuilder.buildService(ContractHolderApi::class.java)

    fun createContract(contract: Contract?, onResult: (Contract?) -> Unit){
        if (contract != null) {
            retrofit.createContract(contract).enqueue(
                object: Callback<Contract> {
                    override fun onResponse(call: Call<Contract>, response: Response<Contract>) {
                        val addedContract = response.body()
                        onResult(addedContract)
                    }
                    override fun onFailure(call: Call<Contract>, t: Throwable) {
                        t?.printStackTrace()
                    }
                }
            )
        } else {
            onResult(null)
        }
    }

    fun getAllContracts(onResult: (List<Contract>) -> Unit){
        retrofit.getAllContracts().enqueue(
            object : Callback<List<Contract>> {
                override fun onResponse(call: Call<List<Contract>>, response: Response<List<Contract>>) {
                    val contracts = response?.body()
                    if (contracts != null) {
                        onResult(contracts)
                    }
                }

                override fun onFailure(call: Call<List<Contract>>, t: Throwable) {
                    t?.printStackTrace()
                }
            }
        )
    }

    fun updateContract(contractId: Int, updatedContract: Contract, onResult: (Contract?) -> Unit){
        retrofit.updateContract(contractId, updatedContract).enqueue(
            object: Callback<Contract> {
                override fun onResponse( call: Call<Contract>, response: Response<Contract>) {
                    if (response.isSuccessful) {
                        val updatedContract = response.body()
                        onResult(updatedContract)
                    } else {
                        val statusCode = response.code()
                        Log.e("UpdateContractError", "HTTP Error: $statusCode")
                        onResult(null)
                    }
                }
                override fun onFailure(call: Call<Contract>, t: Throwable) {
                    t?.printStackTrace()
                    Log.e("UpdateContractError", "Network request failed", t)
                    onResult(null)
                }
            }
        )
    }

}