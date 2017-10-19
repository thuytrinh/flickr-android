package thuy.flickr

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface FlickrApi {
  @GET("services/rest/?method=flickr.photos.getRecent&nojsoncallback=1&format=json")
  fun getRecent(
      @Query("api_key") apiKey: String,
      @Query("extras") extras: String = "url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"
  ): Single<PhotosResponseEntity>
}
