package thuy.flickr

import dagger.Module
import dagger.Provides

@Module
class PhotoRepositoryModule {
  @Provides
  fun getPhotoRepository(): PhotoRepository = PhotoRepositoryImpl()
}
