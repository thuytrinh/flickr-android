package thuy.flickr

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidBindingModule {
  @ContributesAndroidInjector(modules = arrayOf(
      MainActivityModule::class
  ))
  abstract fun mainActivity(): MainActivity
}
