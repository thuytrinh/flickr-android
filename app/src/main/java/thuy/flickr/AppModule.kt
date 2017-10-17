package thuy.flickr

import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class AppModule {
  @Provides
  fun getResources(app: App): Resources = app.resources
}
