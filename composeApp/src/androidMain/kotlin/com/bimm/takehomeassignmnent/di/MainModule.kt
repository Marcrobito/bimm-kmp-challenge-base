package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.presentation.SakeShopsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Provides all Android-specific DI bindings.
 *
 * Currently only binds:
 *  - [SakeShopsViewModel]: the ViewModel responsible for driving the shops list UI.
 *
 * Future enhancements:
 *  - Add qualifiers or additional modules for other Android components (e.g. Services, Workers).
 *  - If you introduce new ViewModels or use cases, group them into feature modules.
 */
val mainModule = module {
    /**
     * Injects SakeShopsViewModel with its required [SakeShopsRepository] from the shared module.
     */
    viewModel { SakeShopsViewModel(get()) }
}