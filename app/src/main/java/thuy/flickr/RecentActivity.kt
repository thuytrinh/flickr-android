package thuy.flickr

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

class RecentActivity : DaggerAppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportFragmentManager
        .beginTransaction()
        .replace(android.R.id.content, RecentFragment())
        .commit()
  }
}
