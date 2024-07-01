package me.ashutoshkk.stonks.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.ashutoshkk.stonks.data.repository.CompanyRepositoryImpl
import me.ashutoshkk.stonks.data.repository.HomeRepositoryImpl
import me.ashutoshkk.stonks.data.repository.SearchRepositoryImpl
import me.ashutoshkk.stonks.domain.repository.CompanyRepository
import me.ashutoshkk.stonks.domain.repository.HomeRepository
import me.ashutoshkk.stonks.domain.repository.SearchRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindsCompanyRepository(repository: CompanyRepositoryImpl): CompanyRepository

    @Binds
    abstract fun bindsSearchRepository(repository: SearchRepositoryImpl): SearchRepository

}