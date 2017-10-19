package thuy.flickr

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
  @Provides
  fun getResources(app: App): Resources = app.resources

  @Provides
  @Singleton
  fun appContext(app: App): Context = app
}
