package com.creative.apps.kento.di.modules

import com.creative.apps.kento.data.repositories.*
import com.creative.apps.kento.interactors.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object InteractorsModule {

    @Provides
    fun providesGetPopularAnimesUseCase(repository : PopularRepository) : GetPopularAnimesUseCase =
        GetPopularAnimesUseCase(repository)

    @Provides
    fun providesGetGenresUseCase(repository: GenresRepository) : GetGenresUseCase =
        GetGenresUseCase(repository)

    @Provides
    fun providesGetSeasonsUseCase(repository : SeasonsRepository) : GetSeasonsUseCase =
        GetSeasonsUseCase(repository)

    @Provides
    fun providesGetAnimeInformationUseCase(respository : AnimeInformationRepository) : GetAnimeInformationUseCase =
        GetAnimeInformationUseCase(respository)

    @Provides
    fun providesGetAnimeCharactersUseCase(respository : AnimeCharacterRepository) : GetAnimeCharactersUseCase =
        GetAnimeCharactersUseCase(respository)

    @Provides
    fun providesGetCharacterInformationUseCase(respository : CharacterInformationRepository) : GetCharacterInformationUseCase =
        GetCharacterInformationUseCase(respository)
}