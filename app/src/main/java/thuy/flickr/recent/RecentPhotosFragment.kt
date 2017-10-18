package thuy.flickr.recent

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguelcatalan.materialsearchview.MaterialSearchView
import dagger.android.support.DaggerFragment
import me.tatarka.bindingcollectionadapter2.ItemBinding
import thuy.flickr.BR
import thuy.flickr.R
import thuy.flickr.databinding.RecentPhotosBinding
import thuy.flickr.photodetails.PhotoDetailsActivity
import javax.inject.Inject

class RecentPhotosFragment : DaggerFragment() {
  @Inject lateinit var viewModel: RecentPhotosViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true

    viewModel.loadPhotos()
    viewModel.onErrorLoadingPhotos.subscribe {
      view?.let {
        Snackbar
            .make(it, R.string.cannot_load_photos, Snackbar.LENGTH_LONG)
            .setAction(R.string.retry) { viewModel.loadPhotos() }
            .show()
      }
    }
    viewModel.onPhotoTapped.subscribe {
      startActivity(PhotoDetailsActivity.newIntent(activity, it))
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater?,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val binding = RecentPhotosBinding.inflate(inflater)

    binding.swipeRefreshLayout.isEnabled = false
    binding.toolbar.inflateMenu(R.menu.recent_photos)
    binding.searchView.setMenuItem(binding.toolbar.menu.findItem(R.id.search))
    binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String?): Boolean = viewModel.onQueryTextSubmit(query)

      override fun onQueryTextChange(newText: String?): Boolean = false
    })

    binding.photoItemBinding = ItemBinding.of<PhotoViewModel>(BR.viewModel, R.layout.photo)
    binding.viewModel = viewModel

    return binding.root
  }

  override fun onDestroy() {
    viewModel.onCleared()
    super.onDestroy()
  }
}
