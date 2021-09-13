package com.example.android.imdbdemo.ui

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.imdbdemo.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

//    private val languages = arrayOf<String>("English", "عربي")

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val ad: ArrayAdapter<*> =
//            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, languages)
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        languageSpinner.adapter = ad
//        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectedLanguage: String = adapterView?.selectedItem as String
//
//                when (selectedLanguage) {
//                    "English" -> {
//                        setLocal(this@MainActivity, "en")
//                        finish()
//                    }
//                    "عربي" -> {
//                        setLocal(this@MainActivity, "ar")
//                        finish()
//                    }
//                }
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {}
//        }



        english.setOnClickListener {
            setLocal(this, "en")
                val inten = intent
                finish()
                startActivity(inten)
        }

        arabic.setOnClickListener {
            setLocal(this, "ar")
            val inten = intent
            finish()
            startActivity(inten)
        }

        navController = findNavController(R.id.container)

        bottomNavView.setupWithNavController(navController)


        searchBar.setOnClickListener {
            searchBar.isCursorVisible = true
        }

        searchBar.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    val searchingResult = searchBar.text.toString()
                    if (searchingResult != "") {
                        val bdl = Bundle()
                        bdl.putString("searchQuery", searchingResult)
                        navController.navigate(R.id.searchingFragment, bdl)
                    }
                    hideSoftKeyboard()

                    searchBar.clearFocus()
                    searchBar.isCursorVisible = false
                    searchBar.setText("")

                    return true
                }
                return false
            }
        })
    }

    override fun onBackPressed() {
        if (!navController.popBackStack())
            super.onBackPressed()
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    fun Activity.hideSoftKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }


    private fun setLocal(activity: Activity, langCode: String) {
        val local = Locale(langCode)
        Locale.setDefault(local)

        val resources: Resources = activity.resources
        val config: Configuration = resources.configuration

        config.setLocale(local)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

}