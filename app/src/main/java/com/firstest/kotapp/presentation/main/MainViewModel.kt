package com.firstest.kotapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstest.kotapp.data.remote.DnDSpellApi
import com.firstest.kotapp.data.remote.RestDnDSpellAPIResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel() : ViewModel() {
    val apiLiveData: MutableLiveData<APICallStatus> = MutableLiveData()
    private val BASE_URL = "https://www.dnd5eapi.co/api/"

    fun makeAPICall(){
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val spellApi = retrofit.create(DnDSpellApi::class.java)
        val call: Call<RestDnDSpellAPIResponse>? = spellApi?.spellResponse
        if (call != null) {
            call.enqueue(object : Callback<RestDnDSpellAPIResponse?> {
                override fun onResponse(
                    call: Call<RestDnDSpellAPIResponse?>?,
                    response: Response<RestDnDSpellAPIResponse?>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val spellList = response.body()!!.results
                        apiLiveData.value = APICallSuccess(spellList)
                    }
                }
                override fun onFailure(call: Call<RestDnDSpellAPIResponse?>?, t: Throwable?) {
                    apiLiveData.value = APICallFailure
                }
            })
        }
    }
}