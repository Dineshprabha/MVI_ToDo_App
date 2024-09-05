package com.dinesh.mvitodoapp.di

import android.content.Context
import com.dinesh.mvitodoapp.model.local.Todo
import com.dinesh.mvitodoapp.model.local.TodoDao
import com.dinesh.mvitodoapp.model.local.TodoDatabase
import com.dinesh.mvitodoapp.model.repository.TodoRepoImpl
import com.dinesh.mvitodoapp.model.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesTodoDatabase(@ApplicationContext context: Context) : TodoDatabase{
        return TodoDatabase.getInstance(context)
    }

    @Provides
    fun providesTodoDao(todoDatabase: TodoDatabase) : TodoDao{
        return todoDatabase.getTodoDao()
    }

    @Provides
    fun providesRepository(todoDao: TodoDao) : TodoRepository{
        return  TodoRepoImpl(todoDao)
    }

}