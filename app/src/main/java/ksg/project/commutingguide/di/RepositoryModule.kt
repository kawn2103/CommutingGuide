package ksg.project.commutingguide.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ksg.project.commutingguide.data.repository.BusStopsRepository
import ksg.project.commutingguide.data.repository.BusStopsRepositoryImpl
import javax.inject.Singleton

//di에 사용될 레파지토리 모듈 선언
//Module => 모듈 생성 선언
//InstallIn => di 타입에 맞게 생성(지금은 싱글톤 선언)
//Singleton => 싱글톤 선언
//Binds => di로 주입하기 위해 인터페이스를 생성해야 할 때 사용
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBookSearchRepository(
        busStopsRepositoryImpl: BusStopsRepositoryImpl,
    ): BusStopsRepository
}