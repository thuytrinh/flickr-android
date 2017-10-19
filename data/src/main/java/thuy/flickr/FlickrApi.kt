package thuy.flickr

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface FlickrApi {
  @GET("services/rest/?method=flickr.photos.getRecent&nojsoncallback=1&format=json")
  fun getRecent(@Query("api_key") apiKey: String): Single<PhotosResponseEntity>
}
