package io.papahgh.pennywise.config

import io.papahgh.pennywise.AccountCreateViewModel
import io.papahgh.pennywise.CategoryCreateViewModel
import io.papahgh.pennywise.TransactionCreateViewModel
import io.papahgh.pennywise.data.AccountRepository
import io.papahgh.pennywise.data.CategoryRepository
import io.papahgh.pennywise.data.DefaultAccountRepository
import io.papahgh.pennywise.data.DefaultCategoryRepository
import io.papahgh.pennywise.data.DefaultTransactionRepository
import io.papahgh.pennywise.data.TransactionRepository
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.dsl.onClose
import kotlin.reflect.KClass

class PennywiseContext(
    private val factory: PennywiseFactory,
    private val appDeclaration: KoinAppDeclaration = {},
) : AutoCloseable {
    private val koinApp =
        startKoin {
            appDeclaration()
            val databaseModule =
                module {
                    single { get<PennywiseDatabase>().accountDao }
                    single { get<PennywiseDatabase>().categoryDao }
                    single { get<PennywiseDatabase>().transactionDao }
                    single { factory.createDatabase() }.onClose { it?.close() }
                }
            val repositoryModule =
                module {
                    single<AccountRepository> { DefaultAccountRepository(get()) }
                    single<CategoryRepository> { DefaultCategoryRepository(get()) }
                    single<TransactionRepository> { DefaultTransactionRepository(get()) }
                }
            val presentationModule =
                module {
                    viewModel { AccountCreateViewModel(get()) }
                    viewModel { params -> CategoryCreateViewModel(params.get(), get()) }
                    viewModel { params -> TransactionCreateViewModel(params.get(), params.get(), get()) }
                }
            modules(databaseModule, repositoryModule, presentationModule)
        }

    internal fun <T : Any> inject(
        clazz: KClass<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null,
    ) = koinApp.koin.get<T>(clazz, qualifier, parameters)

    override fun close() = stopKoin()
}
