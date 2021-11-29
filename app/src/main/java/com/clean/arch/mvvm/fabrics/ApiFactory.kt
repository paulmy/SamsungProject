package com.clean.arch.mvvm.fabrics

import com.clean.arch.mvvm.net.MovieApi
import com.clean.arch.mvvm.utils.API_KEY
import com.clean.arch.mvvm.utils.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object ApiFactory {

    private var instance: MovieApi? = null

    fun getApi() : MovieApi {
        var api = instance
        if (api == null) {
            api = getRetrofitInstance().create(MovieApi::class.java)
            instance = api
        }
        return api!!
    }

    private fun getRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
        .client(getClientInstance())
        .build()

    private fun getGsonInstance() =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

    private fun getClientInstance() = OkHttpClient.Builder().cache(getCacheInstance()).apply {
            interceptors().add {
                val request = it.request()
                val url = request.url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .addQueryParameter("language", Locale.getDefault().toLanguageTag())
                    .build()
                it.proceed(request.newBuilder().url(url).build())
            }
        }.build()

    private fun getCacheInstance() = Cache(AppFactory.getInstance().cacheDir, 10L * 1024 * 1024)
}