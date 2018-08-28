package kyrpap.githubrepos.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providesContext(app: Application): Context = app.applicationContext

}