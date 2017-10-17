package thuy.flickr.search

import android.os.Bundle
import dagger.android.support.DaggerFragment

class SearchFragment : DaggerFragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    retainInstance = true
  }
}
