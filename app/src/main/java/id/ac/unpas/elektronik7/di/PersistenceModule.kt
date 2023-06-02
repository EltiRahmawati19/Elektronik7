//package id.ac.unpas.elektronik7.di
//
//import android.app.Application
//import androidx.room.Room
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import id.ac.unpas.elektronik7.persistences.AppDatabase
//import id.ac.unpas.elektronik7.persistences.KomputerDao
//import id.ac.unpas.elektronik7.persistences.PeriferalDao
//import id.ac.unpas.elektronik7.persistences.SmartphoneDao
//import javax.inject.Singleton
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object PersistenceModule {
//    @Provides
//    @Singleton
//    fun provideAppDatabase(application: Application): AppDatabase {
//        return Room
//            .databaseBuilder(
//                application,
//                AppDatabase::class.java,
//                "pengelolaan-komputer, pengelolaan-periferal, pengelolaan-smartphone"
//
//            )
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//    @Provides
//    @Singleton
//    fun provideKomputerDao(appDatabase: AppDatabase): KomputerDao{
//        return appDatabase.komputerDao()
//    }
//    fun providePeriferalDao(appDatabase: AppDatabase): PeriferalDao{
//        return appDatabase.periferalDao()
//    }
//    fun provideSmartphoneDao(appDatabase: AppDatabase): SmartphoneDao{
//        return appDatabase.smartphoneDao()
//    }
//
//}