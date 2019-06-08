package thuy.flickr.recent

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguelcatalan.materialsearchview.MaterialSearchView
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.LayoutManagers
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

    viewModel.loadPhotos(savedInstanceState)
    viewModel.onErrorLoadingPhotos.subscribe {
      view?.let {
        Snackbar
            .make(it, R.string.cannot_load_photos, Snackbar.LENGTH_LONG)
            .setAction(R.string.retry) { viewModel.retry() }
            .show()
      }
    }
    viewModel.onPhotoTapped.subscribe {
      startActivity(PhotoDetailsActivity.newIntent(requireActivity(), it))
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    viewModel.onSaveInstanceState(outState)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
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

    // To configure columns for different orientations.
    // e.g. for portrait, column count is 2 while for landscape, the count is 4.
    binding.photosView.layoutManager = LayoutManagers
        .grid(resources.getInteger(R.integer.galleryColumns))
        .create(binding.photosView)
    addDividers(binding.photosView)

    binding.photoItemBinding = ItemBinding.of<PhotoViewModel>(BR.viewModel, R.layout.photo)
    binding.viewModel = viewModel

    return binding.root
  }

  private fun addDividers(photosView: RecyclerView) {
    val verticalDividerItemDecoration = DividerItemDecoration(
      activity, DividerItemDecoration.VERTICAL
    ).apply {
      setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.vertical_divider)!!)
    }
    photosView.addItemDecoration(verticalDividerItemDecoration)

    val horizontalDividerItemDecoration = DividerItemDecoration(
      activity, DividerItemDecoration.HORIZONTAL
    ).apply {
      setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.horizontal_divider)!!)
    }
    photosView.addItemDecoration(horizontalDividerItemDecoration)
  }

  override fun onDestroy() {
    viewModel.onCleared()
    super.onDestroy()
  }
}
