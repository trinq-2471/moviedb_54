package com.sun.moviedb_54.screen.search.provider

import android.content.SearchRecentSuggestionsProvider

class SuggestionProvider : SearchRecentSuggestionsProvider() {

    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.sun.moviedb_54"
        const val MODE = DATABASE_MODE_QUERIES
    }
}
