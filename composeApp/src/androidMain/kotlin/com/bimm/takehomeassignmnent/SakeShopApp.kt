package com.bimm.takehomeassignmnent

import android.app.Application
import com.bimm.takehomeassignmnent.di.mainModule
import com.bimm.takehomeassignmnent.di.sharedModule
import com.bimm.takehomeassignmnent.domain.JsonProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * Custom [Application] that bootstraps Koin for dependency injection.
 *
 * It initializes two modules:
 *  1. [sharedModule]: the multiplatform shared DI bindings, passing in an Android-specific
 *     [JsonProvider] implementation that reads the embedded JSON from `raw`.
 *  2. [mainModule]: Android-specific bindings (e.g., ViewModels).
 */
class SakeShopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SakeShopApp)
            modules(
                // Provides shared repository & datasource using the Android JSON loader
                sharedModule(androidJsonProvider),
                // Provides Android UI bindings (ViewModels)
                mainModule
            )
        }
    }

    /**
     * Android-specific provider that loads the `sakeshop.json` file from the raw resources.
     */
    private val androidJsonProvider = object : JsonProvider {
        override fun loadJson(): String {
            return resources
                .openRawResource(R.raw.sakeshop)
                .bufferedReader()
                .use { it.readText() }
        }
    }
}