package com.example.database.source.datasource.base

import com.example.database.core.model.base.BaseDatabaseModel
import kotlinx.coroutines.flow.Flow

interface BaseDatabaseDataSource<T : BaseDatabaseModel> {

    suspend fun getFlow(): Flow<List<T>?>

    suspend fun insert(values: List<T>)

    suspend fun deleteNotIn(values: List<String>)

    suspend fun deleteAll()
}
