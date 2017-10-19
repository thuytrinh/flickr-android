package thuy.flickr

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
internal interface PhotoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(photos: List<PhotoDbEntity>)

  @Query("SELECT * FROM photos WHERE id = :photoId LIMIT 1")
  fun loadPhotoById(photoId: String): PhotoDbEntity?
}
