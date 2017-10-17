package thuy.flickr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

class RecentPhotosFragment : DaggerFragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }

  override fun onCreateView(
      inflater: LayoutInflater?,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = layoutInflater?.inflate(R.layout.recent_photos, container, false)
}
