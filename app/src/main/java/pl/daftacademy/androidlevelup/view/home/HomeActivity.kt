package pl.daftacademy.androidlevelup.view.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_home.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.movies.MoviesFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nav.setNavigationItemSelectedListener { changePage(item = it) }
        if (savedInstanceState == null) showPage("All Movies", addToBackStack = false)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun changePage(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_movies -> showPage("All Movies")
            R.id.action_action -> showPage("Action")
            R.id.action_comedy -> showPage("Comedy")
            R.id.action_crime -> showPage("Crime")
            R.id.action_horror -> showPage("Horror")
            R.id.action_romance -> showPage("Romance")
            else -> return false
        }
        nav.menu.children.find { it.isChecked }?.isChecked = false
        item.isChecked = true
        drawer.closeDrawers()
        return true
    }

    private fun showPage(genere: String, addToBackStack: Boolean = false) {
        toolbar.title = genere
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerMovies, MoviesFragment.create(genere))
            .apply { if(addToBackStack) addToBackStack(null) }
            .commit()
    }
}