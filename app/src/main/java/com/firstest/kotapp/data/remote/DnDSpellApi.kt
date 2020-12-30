package com.firstest.kotapp.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface DnDSpellApi {
    @get:GET("spells")
    val spellResponse: Call<RestDnDSpellAPIResponse>
}