package sg.vinova.besttrip.dagger.module

import android.os.Handler
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sg.vinova.besttrip.BApplication
import sg.vinova.besttrip.BuildConfig
import sg.vinova.besttrip.library.BApi
import sg.vinova.besttrip.library.Constant.Companion.MAX_TRY_COUNT
import sg.vinova.besttrip.library.Constant.Companion.RETRY_BACKOFF_DELAY
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by hanah on 11/22/17.
 */
@Module
class BModule(private val app: BApplication) {
    @Provides
    @Singleton
    fun getApp() = app

    @Provides
    @Singleton
    fun getHandler() = Handler()

    @Provides
    fun getContext() = app.applicationContext!!

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    fun provideBApi(okHttpClient: OkHttpClient): BApi {
        synchronized(BApi::class.java) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(BApi::class.java)
        }
    }

    @Provides
    fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val request = chain.request()
            // try the request
            var response: Response? = null
            var tryCount = 1
            while (tryCount <= MAX_TRY_COUNT) {
                try {
                    response = chain.proceed(request)
                    break
                } catch (e: Exception) {
                    if ("Canceled".equals(e.message, ignoreCase = true)) {
                        // Request canceled, do not retry
                        throw e
                    }
                    if (tryCount >= MAX_TRY_COUNT) {
                        // max retry count reached, giving up
                        throw e
                    }

                    try {
                        // sleep delay * try count (e.g. 1st retry after 3000ms, 2nd after 6000ms, etc.)
                        Thread.sleep((RETRY_BACKOFF_DELAY * tryCount).toLong())
                    } catch (e1: InterruptedException) {
                        throw RuntimeException(e1)
                    }

                    tryCount++
                }

            }

            // otherwise just pass the original response on
            response
        })
        if (BuildConfig.isLog) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.interceptors().add(logging)
        }
        httpClient.writeTimeout((15 * 60 * 1000).toLong(), TimeUnit.MILLISECONDS)
        httpClient.readTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
        httpClient.connectTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
        return httpClient.build()
    }
}