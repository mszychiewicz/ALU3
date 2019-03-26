package pl.daftacademy.androidlevelup.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.entity.Movie
import pl.daftacademy.androidlevelup.view.viewmodel.MoviesViewModel

const val GENERE_EXTRA = "GENERE_EXTRA"

class MoviesFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MoviesViewModel::class.java] }
    private val adapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val genere = arguments?.getString(GENERE_EXTRA) ?: throw IllegalStateException("Required genere argument")
        recyclerView.adapter = adapter
        adapter.items = viewModel.getMovies(genere)
    }

    companion object {
        fun create(genere: String): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            args.putString(GENERE_EXTRA, genere)
            fragment.arguments = args
            return fragment
        }
    }
}
