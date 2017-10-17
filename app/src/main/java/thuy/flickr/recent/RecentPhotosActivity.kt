package thuy.flickr.recent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class RecentPhotosActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportFragmentManager
        .beginTransaction()
        .replace(android.R.id.content, RecentPhotosFragment())
        .commit()
  }
}
