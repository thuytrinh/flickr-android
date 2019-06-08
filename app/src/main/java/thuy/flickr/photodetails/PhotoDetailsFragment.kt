package thuy.flickr.photodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import thuy.flickr.core.DaggerFragment
import thuy.flickr.databinding.PhotoDetailsBinding
import javax.inject.Inject

class PhotoDetailsFragment : DaggerFragment() {
  @Inject lateinit var viewModel: PhotoDetailsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true

    viewModel.extractPhotoId(arguments)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val binding = PhotoDetailsBinding.inflate(inflater)
    binding.toolbar.setNavigationOnClickListener {
      requireActivity().finish()
    }

    // See http://frescolib.org/docs/using-simpledraweeview.html.
    val hierarchy = GenericDraweeHierarchyBuilder.newInstance(resources)
        .setProgressBarImage(ProgressBarDrawable())
        .build()
    binding.photoView.hierarchy = hierarchy

    binding.viewModel = viewModel
    return binding.root
  }

  override fun onDestroy() {
    viewModel.onCleared()
    super.onDestroy()
  }
}
