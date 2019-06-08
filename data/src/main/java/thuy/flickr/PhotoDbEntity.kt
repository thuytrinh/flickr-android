package thuy.flickr

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "photos")
internal class PhotoDbEntity(
  @PrimaryKey var id: String = "",
  @ColumnInfo(name = "secret") var secret: String = "",
  @ColumnInfo(name = "server") var server: String = "",
  @ColumnInfo(name = "farm") var farm: Int = 0,
  @ColumnInfo(name = "title") var title: String? = null,
  @ColumnInfo(name = "url_l") var urlL: String? = null,
  @ColumnInfo(name = "url_o") var urlO: String? = null,
  @ColumnInfo(name = "url_c") var urlC: String? = null,
  @ColumnInfo(name = "url_z") var urlZ: String? = null,
  @ColumnInfo(name = "url_n") var urlN: String? = null,
  @ColumnInfo(name = "url_m") var urlM: String? = null,
  @ColumnInfo(name = "url_q") var urlQ: String? = null,
  @ColumnInfo(name = "url_s") var urlS: String? = null,
  @ColumnInfo(name = "url_t") var urlT: String? = null,
  @ColumnInfo(name = "url_sq") var urlSq: String? = null
)
