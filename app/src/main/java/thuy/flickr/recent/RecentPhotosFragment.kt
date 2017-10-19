package thuy.flickr.recent

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguelcatalan.materialsearchview.MaterialSearchView
import me.tatarka.bindingcollectionadapter2.ItemBinding
import thuy.flickr.BR
import thuy.flickr.R
import thuy.flickr.core.DaggerFragment
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
    addDividers(binding.photosView)

    binding.photoItemBinding = ItemBinding.of<PhotoViewModel>(BR.viewModel, R.layout.photo)
    binding.viewModel = viewModel

    return binding.root
  }

  private fun addDividers(photosView: RecyclerView) {
    val verticalDividerItemDecoration = DividerItemDecoration(
        activity, DividerItemDecoration.VERTICAL
    ).apply {
      setDrawable(resources.getDrawable(R.drawable.vertical_divider))
    }
    photosView.addItemDecoration(verticalDividerItemDecoration)

    val horizontalDividerItemDecoration = DividerItemDecoration(
        activity, DividerItemDecoration.HORIZONTAL
    ).apply {
      setDrawable(resources.getDrawable(R.drawable.horizontal_divider))
    }
    photosView.addItemDecoration(horizontalDividerItemDecoration)
  }

  override fun onDestroy() {
    viewModel.onCleared()
    super.onDestroy()
  }
}
