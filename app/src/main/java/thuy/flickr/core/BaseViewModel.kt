package thuy.flickr.core

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

abstract class BaseViewModel {
  private val clearRelay = PublishRelay.create<Unit>()

  fun <T> Observable<T>.autoClear(): Observable<T> = this.takeUntil(clearRelay)

  fun onCleared() {
    clearRelay.accept(Unit)
  }
}
