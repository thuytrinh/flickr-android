package thuy.flickr.core

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel {
  private val clearRelay = PublishRelay.create<Unit>()
  private val compositeDisposable by lazy { CompositeDisposable() }

  fun <T> Observable<T>.autoClear(): Observable<T> = this.takeUntil(clearRelay)

  fun Disposable.autoDispose() = compositeDisposable.add(this)

  fun onCleared() {
    clearRelay.accept(Unit)
    compositeDisposable.dispose()
  }
}
