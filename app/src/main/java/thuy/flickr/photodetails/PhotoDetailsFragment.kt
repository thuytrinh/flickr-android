package thuy.flickr.photodetails

import android.os.Bundle
import dagger.android.support.DaggerFragment

class PhotoDetailsFragment : DaggerFragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }
}
