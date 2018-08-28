package kyrpap.githubrepos

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kyrpap.githubrepos.di.DaggerAppComponent
import javax.inject.Inject

class SearchReposApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()


        DaggerAppComponent.builder()
                .create(this)
                .build().inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = androidInjector

}