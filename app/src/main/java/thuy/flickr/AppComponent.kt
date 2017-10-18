package thuy.flickr

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    AndroidBindingModule::class,
    AppModule::class,
    PhotoRepositoryModule::class
))
interface AppComponent : AndroidInjector<App> {
  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<App>()
}
