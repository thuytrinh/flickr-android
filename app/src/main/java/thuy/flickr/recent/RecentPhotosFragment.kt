package thuy.flickr.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import thuy.flickr.R
import thuy.flickr.databinding.RecentPhotosBinding
import javax.inject.Inject

class RecentPhotosFragment : DaggerFragment() {
  @Inject lateinit var viewModel: RecentPhotosViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }

  override fun onCreateView(
      inflater: LayoutInflater?,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = layoutInflater?.inflate(R.layout.recent_photos, container, false)

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    val binding = RecentPhotosBinding.bind(view)
    binding.swipeRefreshLayout.isEnabled = false
    binding.viewModel = viewModel
  }
}
