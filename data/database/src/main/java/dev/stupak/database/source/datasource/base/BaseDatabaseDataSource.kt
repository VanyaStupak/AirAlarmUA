package dev.stupak.database.source.datasource.base

import dev.stupak.database.core.model.base.BaseDatabaseModel
import kotlinx.coroutines.flow.Flow

interface BaseDatabaseDataSource<T : BaseDatabaseModel> {
    suspend fun getFlow(): Flow<List<T>?>

    suspend fun insert(values: List<T>)

    suspend fun deleteNotIn(values: List<String>)

    suspend fun deleteAll()
}
