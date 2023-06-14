package id.ac.unpas.elektronik7.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.elektronik7.networks.KomputerApi
import id.ac.unpas.elektronik7.networks.PeriferalApi
import id.ac.unpas.elektronik7.networks.SmartphoneApi
import id.ac.unpas.elektronik7.persistences.KomputerDao
import id.ac.unpas.elektronik7.persistences.PeriferalDao
import id.ac.unpas.elektronik7.persistences.SmartphoneDao
import id.ac.unpas.elektronik7.repositories.KomputerRepository
import id.ac.unpas.elektronik7.repositories.PeriferalRepository
import id.ac.unpas.elektronik7.repositories.SmartphoneRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideKomputerRepository(
        api: KomputerApi,
        dao: KomputerDao
    ): KomputerRepository {
        return KomputerRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun providePeriferalRepository(
        api: PeriferalApi,
        dao: PeriferalDao
    ): PeriferalRepository {
        return PeriferalRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun provideSmartphoneRepository(
        api: SmartphoneApi,
        dao: SmartphoneDao
    ): SmartphoneRepository {
        return SmartphoneRepository(api, dao)
    }
}