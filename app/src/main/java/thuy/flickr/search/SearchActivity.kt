package thuy.flickr.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
  companion object {
    fun newIntent(context: Context): Intent =
        Intent(context, SearchActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      supportFragmentManager
          .beginTransaction()
          .replace(android.R.id.content, SearchFragment())
          .commit()
    }
  }
}
