package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.domain.JsonProvider
import org.koin.core.context.startKoin
import org.koin.core.Koin
import org.koin.mp.KoinPlatform.getKoin

/**
 * Retrieves the current [Koin] instance for direct access from iOS.
 *
 * Originally intended to allow shared DI usage on Swift side, but due to
 * time constraints the full integration was deferred.
 *
 * @return existing Koin application context.
 */
fun getKoinInstance(): Koin = getKoin()

/**
 * Initializes Koin with the provided [JsonProvider] for use in multiplatform
 * contexts (e.g., iOS). Intended to inject a platform-specific JSON loader.
 *
 * Full iOS DI integration was planned but postponed due to time limitations.
 *
 * @param jsonProvider platform-specific implementation to load JSON data.
 */
fun doInitKoin(jsonProvider: JsonProvider) {
    startKoin {
        modules(sharedModule(jsonProvider))
    }
}
