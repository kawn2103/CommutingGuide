package ksg.project.commutingguide.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ksg.project.commutingguide.data.repository.BusStopsRepository
import ksg.project.commutingguide.data.repository.BusStopsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBookSearchRepository(
        busStopsRepositoryImpl: BusStopsRepositoryImpl,
    ): BusStopsRepository
}