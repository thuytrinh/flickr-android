package thuy.flickr

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

open class QueryRepository @Inject internal constructor() {
  private val queryTextRelay = BehaviorRelay.create<String>()

  open val latestQueryText: String? get() = queryTextRelay.value

  open val queries: Flowable<Query>
    get() = queryTextRelay
        .map {
          when (it.isBlank()) {
            true -> Recent
            false -> Search(it)
          }
        }
        .toFlowable(BackpressureStrategy.BUFFER)

  open fun putQueryText(queryText: String?) {
    queryTextRelay.accept(queryText ?: "")
  }

  private fun a() {
  }

  private fun b() {
  }

  private fun c() {
  }

  private fun d() {
  }

  private fun e() {
  }

  private fun f() {
  }

  private fun g() {
  }

  private fun h() {
  }

  private fun i() {
  }

  private fun j() {
  }

  private fun k() {
  }

  private fun l() {
  }

  private fun m() {
  }

  private fun o() {
  }
}
