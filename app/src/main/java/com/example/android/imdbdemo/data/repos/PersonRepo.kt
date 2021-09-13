package com.example.android.imdbdemo.data.repos

import com.example.android.imdbdemo.data.model.Person
import com.example.android.imdbdemo.data.remote.TmdbEndPoints

interface PersonRepo {
    suspend fun getPerson(id: Int): Person
}

class PersonRepoImpl(private val service: TmdbEndPoints): PersonRepo{
    override suspend fun getPerson(id: Int): Person {
        return service.getPersonById(id, "your key")
    }
}