package kyrpap.githubrepos.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kyrpap.githubrepos.BuildConfig
import kyrpap.githubrepos.data.network.GithubApi
import kyrpap.githubrepos.utils.Statics.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL).build()
    }


    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideNetworkService(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    fun getTimeOut(): Int {
        return 30
    }


    @Provides
    protected fun provideOkHttpClientDefault(interceptor: HttpLoggingInterceptor, timeout: Int): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(interceptor)
        okBuilder.addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            chain.proceed(builder.build())
        }

        okBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)

        return okBuilder.build()
    }
}