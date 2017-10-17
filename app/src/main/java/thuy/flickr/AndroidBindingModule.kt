package thuy.flickr

import dagger.Module
import dagger.android.ContributesAndroidInjector
import thuy.flickr.photodetails.PhotoDetailsFragment
import thuy.flickr.recent.RecentPhotosFragment
import thuy.flickr.search.SearchFragment

@Module
abstract class AndroidBindingModule {
  @ContributesAndroidInjector(modules = arrayOf(
      PhotoRepositoryModule::class
  ))
  abstract fun getRecentPhotosFragment(): RecentPhotosFragment

  @ContributesAndroidInjector
  abstract fun getPhotoDetailsFragment(): PhotoDetailsFragment

  @ContributesAndroidInjector
  abstract fun getSearchFragment(): SearchFragment
}
