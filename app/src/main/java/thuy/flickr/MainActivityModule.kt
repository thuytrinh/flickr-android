package thuy.flickr

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
  @Provides
  fun getRecentViewModel(activity: MainActivity): RecentViewModel
      = ViewModelProviders.of(activity).get(RecentViewModel::class.java)
}
