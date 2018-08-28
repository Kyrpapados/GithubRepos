package kyrpap.githubrepos.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kyrpap.githubrepos.ui.repos.SearchReposActivity
import kyrpap.githubrepos.ui.repos.details.SearchRepoDetailsActivity

@Module
abstract class ActivitiesBindingModule {
    @ContributesAndroidInjector
    abstract fun searchReposActivity(): SearchReposActivity

    @ContributesAndroidInjector
    abstract fun searchRepoActivityActivity(): SearchRepoDetailsActivity

}