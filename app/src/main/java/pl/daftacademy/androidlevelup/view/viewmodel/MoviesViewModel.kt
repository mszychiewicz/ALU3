package pl.daftacademy.androidlevelup.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pl.daftacademy.androidlevelup.data.MovieDao
import pl.daftacademy.androidlevelup.data.MovieFileDao
import pl.daftacademy.androidlevelup.entity.Movie

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val movieDao: MovieDao = MovieFileDao(getApplication())

    fun getMovies() = movieDao.getAllMovies()
    fun getMovies(genere: String): List<Movie> {
        if (genere.equals("All Movies")) {
            return getMovies()
        } else {
            return getMovies().filter { movie -> movie.genres.contains(genere) }
        }
    }
}
