package com.creative.apps.kento.di.modules

import android.content.Context
import com.creative.apps.kento.presentation.adapters.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object AdaptersModule {

    @Provides
    fun providesAnimesAdapter(@ActivityContext context: Context) : AnimesRecyclerAdapter =
        AnimesRecyclerAdapter(context, ArrayList())

    @Provides
    fun providesCharactersAdapter(@ActivityContext context: Context) : CharactersRecyclerAdapter =
        CharactersRecyclerAdapter(context, ArrayList())

    @Provides
    fun providesSectionsFragmentAdapter(@ActivityContext context: Context) : SectionsFragmentAdapter =
        SectionsFragmentAdapter(context)

    @Provides
    fun providesAnimeGenresAdapter(@ActivityContext context : Context) : GenresRecyclerAdapter =
        GenresRecyclerAdapter(context, ArrayList())

    @Provides
    fun providesSeasonsYearsRecyclerAdapter(@ActivityContext context: Context) : SeasonsYearsRecyclerView =
        SeasonsYearsRecyclerView(context , ArrayList())
}