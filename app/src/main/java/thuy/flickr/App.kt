package thuy.flickr

import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : DaggerApplication() {
  override fun onCreate() {
    super.onCreate()
    Fresco.initialize(this)

    // See https://github.com/JakeWharton/timber.
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
      DaggerAppComponent.builder().create(this)
}
