package me.ashutoshkk.stonks.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.ashutoshkk.stonks.data.repository.HomeRepositoryImpl
import me.ashutoshkk.stonks.domain.repository.HomeRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(repository: HomeRepositoryImpl): HomeRepository

}