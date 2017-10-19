package thuy.flickr;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import org.immutables.gson.Gson;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Value.Immutable
@Gson.TypeAdapters
@JsonAdapter(GsonAdaptersPhotosEntity.class)
interface PhotosEntity {
  int page();
  int pages();
  @SerializedName("perpage") int photoCountPerPage();
  int total();
  @SerializedName("photo") @Nullable List<PhotoEntity> photos();
}
