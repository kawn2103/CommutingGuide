package ksg.project.commutingguide.di

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ksg.project.commutingguide.data.api.BusApi
import ksg.project.commutingguide.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Retrofit
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideTikXml():TikXml{
        return TikXml
            .Builder()
            .exceptionOnUnreadXml(false)
            .build()
    }

    @Singleton
    @Provides
    fun provideXmlRetrofit(okHttpClient: OkHttpClient,tikXml: TikXml): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(tikXml))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()

    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BusApi {
        return retrofit.create(BusApi::class.java)
    }
}