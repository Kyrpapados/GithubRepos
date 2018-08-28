package kyrpap.githubrepos.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kyrpap.githubrepos.SearchReposApplication
import kyrpap.githubrepos.di.modules.ActivitiesBindingModule
import kyrpap.githubrepos.di.modules.AppModule
import kyrpap.githubrepos.di.modules.NetworkModule

import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivitiesBindingModule::class, NetworkModule::class])
interface AppComponent  : AndroidInjector<SearchReposApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder
        fun build(): AppComponent
    }
}