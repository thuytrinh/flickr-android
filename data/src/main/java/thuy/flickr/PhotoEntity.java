package thuy.flickr;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.immutables.gson.Gson;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Gson.TypeAdapters
@JsonAdapter(GsonAdaptersPhotoEntity.class)
interface PhotoEntity {
  String id();
  String secret();
  String server();
  int farm();
  @Nullable String title();
  @SerializedName("url_l") @Nullable String url_l();
  @SerializedName("url_o") @Nullable String url_o();
  @SerializedName("url_c") @Nullable String url_c();
  @SerializedName("url_z") @Nullable String url_z();
  @SerializedName("url_n") @Nullable String url_n();
  @SerializedName("url_m") @Nullable String url_m();
  @SerializedName("url_q") @Nullable String url_q();
  @SerializedName("url_s") @Nullable String url_s();
  @SerializedName("url_t") @Nullable String url_t();
  @SerializedName("url_sq") @Nullable String url_sq();
}
