package io.papahgh.pennywise.config

import io.papahgh.pennywise.AccountCreateViewModel
import io.papahgh.pennywise.CategoryCreateViewModel
import io.papahgh.pennywise.TransactionCreateViewModel
import io.papahgh.pennywise.data.AccountRepository
import io.papahgh.pennywise.data.CategoryRepository
import io.papahgh.pennywise.data.DefaultAccountRepository
import io.papahgh.pennywise.data.DefaultCategoryRepository
import io.papahgh.pennywise.data.DefaultPreferencesRepository
import io.papahgh.pennywise.data.DefaultTransactionRepository
import io.papahgh.pennywise.data.PreferencesRepository
import io.papahgh.pennywise.data.TransactionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.dsl.onClose
import kotlin.reflect.KClass

class PennywiseContext private constructor(
    declaration: KoinAppDeclaration,
) : AutoCloseable {
    private val koinApp = startKoin(declaration)

    internal fun <T : Any> inject(
        clazz: KClass<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null,
    ) = koinApp.koin.get<T>(clazz, qualifier, parameters)

    override fun close() = stopKoin()

    companion object {
        fun of() = of {}

        fun of(overrides: KoinAppDeclaration) =
            PennywiseContext {
                modules(platformModule, commonModule, databaseModule, repositoryModule, presentationModule)
                overrides()
            }
    }
}

internal expect val platformModule: Module

private val commonModule: Module =
    module {
        single { Json { ignoreUnknownKeys = true } }
        single { CoroutineScope(Dispatchers.IO + SupervisorJob()) }.onClose { it?.cancel() }
    }

private val databaseModule =
    module {
        single { get<PennywiseDatabase>().accountDao }
        single { get<PennywiseDatabase>().categoryDao }
        single { get<PennywiseDatabase>().transactionDao }
    }

private val repositoryModule =
    module {
        single<AccountRepository> { DefaultAccountRepository(get()) }
        single<CategoryRepository> { DefaultCategoryRepository(get()) }
        single<PreferencesRepository> { DefaultPreferencesRepository(get()) }
        single<TransactionRepository> { DefaultTransactionRepository(get()) }
    }

private val presentationModule =
    module {
        viewModel { AccountCreateViewModel(get()) }
        viewModel { params -> CategoryCreateViewModel(params.get(), get()) }
        viewModel { params -> TransactionCreateViewModel(params.get(), params.get(), get()) }
    }
