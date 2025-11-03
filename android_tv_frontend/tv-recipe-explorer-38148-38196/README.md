# TV Recipe Explorer

Android TV application enabling users to browse, search and view mouth-watering recipes using a modern “Ocean Professional” theme.

## Prerequisites
* Android Studio Flamingo or newer  
* Android SDK 34, Kotlin 1.9+  
* A TV emulator (Android TV) or physical Android TV device

## Build & Run

```bash
cd android_tv_frontend
./gradlew :app:installDebug    # Build + deploy to connected emulator/device
```

The CI preview system exposes the built APK on port `3000` after successful compilation.

## Code Structure
```
android_tv_frontend/
 └── app/
     ├── src/main/java/com/example/android_tv_frontend/
     │    ├── MainActivity.kt                # Hosts all fragments
     │    ├── model/Recipe.kt               # Parcelable recipe model
     │    ├── data/RecipeRepository.kt      # Stub repository (replace with API)
     │    └── ui/
     │         ├── browse/                  # Home screen (Leanback rows)
     │         │     ├── HomeFragment.kt
     │         │     └── RecipeCardPresenter.kt
     │         └── detail/                  # Detail screen
     │               └── RecipeDetailFragment.kt
     └── res/                               # Themes, layouts, drawables
```

## Next Steps
* Replace `RecipeRepository` mock with Retrofit service once `recipe_database` API spec is ready.
* Implement search fragment and persistent navigation bar.
* Add ExoPlayer playback for recipe videos.
