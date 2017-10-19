package thuy.flickr

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PhotoRepositoryModule {
  @Provides
  fun getPhotoRepository(): PhotoRepository {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.flickr.com/")
        .client(httpClient)
        .build()
        .create(FlickrApi::class.java)
    return PhotoRepositoryImpl(api, PhotoMapper())
  }
}
