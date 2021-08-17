package com.example.android.imdbdemo.modules

import com.example.android.imdbdemo.data.repos.*
import org.koin.dsl.module

val repoModule = module {
    factory<MoviesRepo> { MoviesRepoImpl(get()) }
    factory<DetailsRepo> { DetailsRepoImpl(get()) }
    factory<SearchRepo> { SearchRepoImpl(get()) }
    factory<TvRepo> { TvRepoImpl(get()) }
    factory<PersonRepo> { PersonRepoImpl(get()) }
}