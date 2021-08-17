package com.example.android.imdbdemo.ui

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.imdbdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.container)

        bottomNavView.setupWithNavController(navController)


        searchBar.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    val searchingResult = searchBar.text.toString()
                    val bdl = Bundle()
                    bdl.putString("searchQuery", searchingResult)

                    navController.navigate(R.id.searchingFragment, bdl)

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

    fun showSearchBar() {
        searchBar.visibility = View.VISIBLE
    }

    fun hideSearchBar() {
        searchBar.visibility = View.GONE
    }

    fun Activity.hideSoftKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}