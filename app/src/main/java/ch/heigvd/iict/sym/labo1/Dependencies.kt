package ch.heigvd.iict.sym.labo1

import ch.heigvd.iict.sym.labo1.data.IUserRepository
import ch.heigvd.iict.sym.labo1.data.InMemoryUserRepository
import org.koin.dsl.module

val appDependencies = module {
    single<IUserRepository> { InMemoryUserRepository() }
}