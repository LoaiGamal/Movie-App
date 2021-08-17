package com.example.android.imdbdemo.ui.personDetails

import androidx.lifecycle.liveData
import com.example.android.imdbdemo.data.model.Person
import com.example.android.imdbdemo.data.repos.PersonRepo
import com.example.android.imdbdemo.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PersonViewModel(private val personRepo: PersonRepo) : BaseViewModel(), CoroutineScope {
    var tempID = 0
    var detailJob = Job()
    override val coroutineContext: CoroutineContext
        get() = detailJob + Dispatchers.Main

    fun getThePerson(personID: Int) = liveData<Person> {
        try {
            val response =
                withContext(Dispatchers.IO) {
                    personRepo.getPerson(
                        personID
                    )
                }

            tempID = personID
            emit(response)
        } catch (e: Exception) {
            e.message
        }
    }
}