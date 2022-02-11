package com.creative.apps.kento.di.modules

import com.creative.apps.kento.data.repositories.*
import com.creative.apps.kento.framework.sources.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RepositoriesModule {

    @Provides
    fun providesPopularRepository(remotePopularAnimesDataSource: RemotePopularAnimesDataSource) : PopularRepository =
        PopularRepository(remotePopularAnimesDataSource)

    @Provides
    fun providesGenresRepository(remoteGenresDataSource : RemoteGenresDataSource) : GenresRepository =
        GenresRepository(remoteGenresDataSource)

    @Provides
    fun providesSeasonsRepository(remoteSeasonsDataSource : RemoteSeasonsDataSource) : SeasonsRepository =
        SeasonsRepository(remoteSeasonsDataSource)

    @Provides
    fun providesAnimeInformationRepository(remoteAnimeInformationDataSource : RemoteAnimeInformationDataSource) : AnimeInformationRepository =
        AnimeInformationRepository(remoteAnimeInformationDataSource)

    @Provides
    fun providesAnimeCharactersRepository(remoteAnimeCharactersDataSource : RemoteAnimeCharactersDataSource) : AnimeCharacterRepository =
        AnimeCharacterRepository(remoteAnimeCharactersDataSource)

    @Provides
    fun providesCharacterInformationRepository(remoteCharacterInformationDataSource : RemoteCharacterInformationDataSource) : CharacterInformationRepository =
        CharacterInformationRepository(remoteCharacterInformationDataSource)
}