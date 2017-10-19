package thuy.flickr

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class PhotoRepositoryModule {
  @Provides
  @Singleton
  fun getPhotoRepository(context: Context): PhotoRepository {
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

    val appDatabase = createAppDatabase(context)
    return PhotoRepositoryImpl(
        api,
        PhotoMapper(),
        PhotoEntityMapper(),
        appDatabase.photoDao()
    )
  }

  private fun createAppDatabase(context: Context): AppDatabase =
      Room.databaseBuilder<AppDatabase>(
          context,
          AppDatabase::class.java,
          "flickr.sqlite"
      ).build()
}
