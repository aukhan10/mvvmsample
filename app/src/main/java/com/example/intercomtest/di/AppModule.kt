package com.example.intercomtest.di

import android.content.Context
import androidx.room.Room
import com.example.intercomtest.base.RetrofitHelper
import com.example.intercomtest.data.LocalDataSource
import com.example.intercomtest.data.LocalDataSourceImpl
import com.example.intercomtest.data.UniversityListApi
import com.example.intercomtest.data.UniversityListDataSource
import com.example.intercomtest.data.UniversityListDataSourceImpl
import com.example.intercomtest.db.UniversityDao
import com.example.intercomtest.db.UniversityDatabase
import com.example.intercomtest.mapper.UniversityListMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDataSource(retrofit: RetrofitHelper): UniversityListDataSource {
        val api = retrofit.getInstance().create(UniversityListApi::class.java)
        return UniversityListDataSourceImpl(api)
    }

    @Provides
    fun provideLocalDataSource(
        universityDao: UniversityDao, mapper: UniversityListMapper
    ): LocalDataSource {
        return LocalDataSourceImpl(universityDao, mapper)
    }

    @Singleton
    @Provides
    fun provideYourDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            UniversityDatabase::class.java,
            "university_db"
        ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: UniversityDatabase) = db.getUniversityDao()
}