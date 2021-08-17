package com.example.android.imdbdemo.ui.personDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.android.imdbdemo.R
import com.example.android.imdbdemo.data.model.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_person.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonFragment : Fragment() {

    private val args: PersonFragmentArgs by navArgs()
    private val viewModel: PersonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.id
        observeData(id)

    }

    private fun observeData(id: Int) {
        viewModel.getThePerson(id).observe(viewLifecycleOwner, {
            setData(it)
        })
    }

    private fun setData(details: Person) {
        val picasso = Picasso.get()
        picasso.load("https://image.tmdb.org/t/p/w500${details.profilePath}")
            .placeholder(R.drawable.no_image_available)
            .into(personImage)

        name.text = details.name
        knownForResult.text = details.knownForDepartment

        if (details.gender == 1) {
            genderResult.text = "Female"
        } else if (details.gender == 2) {
            genderResult.text = "Male"
        }

        birthdayResult.text = details.birthday
        placeOfBirthResult.text = details.placeOfBirth
        bioResult.text = details.biography
    }
}