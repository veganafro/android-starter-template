package com.veganafro.networking

import com.veganafro.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @Headers("Key: Value")
    @GET("path/to/api/endpoint/{path_variable}")
    suspend fun getEndpoint(@Path("path_variable") pathVariable: String, @Query("query_parameter") queryParameter: String): DataModel
}
