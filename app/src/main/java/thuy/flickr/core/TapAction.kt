package thuy.flickr.core

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

class TapAction<TSender> internal constructor(
    private val getSender: () -> TSender
) {
  companion object {
    fun <TSender> create(getSender: () -> TSender): TapAction<TSender>
        = TapAction(getSender)
  }

  private val onTap = PublishRelay.create<TSender>()
  val observe: Observable<TSender> get() = onTap

  fun perform() = onTap.accept(getSender())
}
