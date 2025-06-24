package io.papahgh.pennywise.data.store

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioSerializer
import androidx.datastore.core.okio.OkioStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import okio.BufferedSink
import okio.BufferedSource
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM
import okio.use

internal class PreferencesStore(
    path: String,
    json: Json,
    scope: CoroutineScope,
) {
    private val db =
        DataStoreFactory.create(
            storage =
                OkioStorage(
                    fileSystem = FileSystem.SYSTEM,
                    serializer = JsonSerializer(json),
                    producePath = { path.toPath() },
                ),
            scope = scope,
        )

    val valueFlow: Flow<PreferencesEntity>
        get() = db.data

    suspend fun updateValue(value: PreferencesEntity) {
        db.updateData { value }
    }
}

private class JsonSerializer(
    private val json: Json,
) : OkioSerializer<PreferencesEntity> {
    override val defaultValue: PreferencesEntity
        get() = PreferencesEntity.DEFAULT_VALUE

    override suspend fun readFrom(source: BufferedSource): PreferencesEntity =
        json.decodeFromString(PreferencesEntity.serializer(), source.readUtf8())

    override suspend fun writeTo(
        t: PreferencesEntity,
        sink: BufferedSink,
    ) {
        sink.use {
            it.writeUtf8(json.encodeToString(PreferencesEntity.serializer(), t))
        }
    }
}
