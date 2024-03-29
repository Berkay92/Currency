package com.berkayturkgeldi.currency.network

import android.util.Log
import com.berkayturkgeldi.currency.BuildConfig
import com.berkayturkgeldi.currency.network.error.ErrorResponse
import com.berkayturkgeldi.currency.network.model.response.ConvertResponse
import com.berkayturkgeldi.currency.network.model.response.LatestResponse
import com.berkayturkgeldi.currency.network.model.response.SymbolsResponse
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val RETROFIT_LOGGING_INTERCEPTOR_TAG = "API_Interceptor"

private const val BASE_URL = "http://data.fixer.io/api/"

private val loggingInterceptor = HttpLoggingInterceptor { message ->
    Log.d(RETROFIT_LOGGING_INTERCEPTOR_TAG, message)
}.apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
}

private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain.request()
        .newBuilder()
        .url(chain.request().url.newBuilder().addQueryParameter("access_key", BuildConfig.apiKey).build())
        .build()
)

private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor { apiKeyAsQuery(it) }
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(NetworkResponseAdapterFactory())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface FixerApiService {
    @GET("symbols")
    suspend fun getSymbols(): NetworkResponse<SymbolsResponse, ErrorResponse>

    @GET("latest")
    suspend fun latest(
        @Query("base") from: String? = null,
        @Query("symbols") to: List<String>? = null,
    ): NetworkResponse<LatestResponse, ErrorResponse>

    @GET("convert")
    suspend fun convert(
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("amount") amount: Double? = null,
    ): NetworkResponse<ConvertResponse, ErrorResponse>

    @GET("/{date}")
    suspend fun historic(
        @Path("date") date: String
    ): NetworkResponse<LatestResponse, ErrorResponse>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object FixerApi {
    val retrofitService: FixerApiService by lazy {
        retrofit.create(FixerApiService::class.java)
    }
}