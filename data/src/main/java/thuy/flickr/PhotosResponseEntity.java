package thuy.flickr;

import com.google.gson.annotations.JsonAdapter;

import org.immutables.gson.Gson;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Gson.TypeAdapters
@JsonAdapter(GsonAdaptersPhotosResponseEntity.class)
interface PhotosResponseEntity {
  @Nullable PhotosEntity photos();
}
