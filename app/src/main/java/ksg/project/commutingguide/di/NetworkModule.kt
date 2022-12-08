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

//di에 사용될 네트워크 모듈 선언
//Module => 모듈 생성 선언
//InstallIn => di 타입에 맞게 생성(지금은 싱글톤 선언)
//Singleton => 싱글톤 선언
//Provides => di로 주입하기 위해 객체를 생성해서 사용할 때 사용
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // okhttp 클라이언트 생성
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)    // 네트워크 통신 인터셉터
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)     // 연결 타임아웃
            .readTimeout(30, TimeUnit.SECONDS)       // 리딩 타임아웃
            .writeTimeout(15, TimeUnit.SECONDS)      // 라이트 타임아웃
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    //xml 파싱데이터 컨버트 팩토리
    @Singleton
    @Provides
    fun provideTikXml():TikXml{
        return TikXml
            .Builder()
            .exceptionOnUnreadXml(false)
            .build()
    }

    //xml데이터 파싱용 retrofit build
    @Singleton
    @Provides
    fun provideXmlRetrofit(okHttpClient: OkHttpClient,tikXml: TikXml): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(tikXml))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()

    }

    //BusApi 서비스를 기반으로 레트로핏 생성
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BusApi {
        return retrofit.create(BusApi::class.java)
    }
}