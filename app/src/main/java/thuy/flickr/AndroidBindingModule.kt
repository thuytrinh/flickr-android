package thuy.flickr

import dagger.Module
import dagger.android.ContributesAndroidInjector
import thuy.flickr.recent.RecentPhotosFragment

@Module
abstract class AndroidBindingModule {
  @ContributesAndroidInjector(modules = arrayOf(
      PhotoRepositoryModule::class
  ))
  abstract fun getRecentPhotosFragment(): RecentPhotosFragment
}
