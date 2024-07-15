object Dependencies {
    object Android {
        val androidCoreKtx by lazy {
            "androidx.core:core-ktx:${Versions.Android.androidCoreKtx}"
        }
        val appCompat by lazy {
            "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        }
        val materialDesign by lazy {
            "com.google.android.material:material:${Versions.Android.materialDesign}"
        }
        val constraintLayout by lazy {
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        }
        val androidNavigationFragment by lazy {
            "androidx.navigation:navigation-fragment-ktx:${Versions.Android.androidNavigationFragment}"
        }
        val androidNavigationUI by lazy {
            "androidx.navigation:navigation-ui-ktx:${Versions.Android.androidNavigationUI}"
        }
        val stdlib by lazy {
            "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Plugins.jetbrainsKotlinAndroid}"
        }
        val composeBom by lazy {
            "androidx.compose:compose-bom:2023.01.00"
        }
        val compose by lazy {
            "androidx.compose.ui:ui"
        }
        val composeFoundation by lazy {
            "androidx.compose.foundation:foundation"
        }
        val composeMaterial by lazy {
            "androidx.compose.material:material"
        }
        val composeConstraintLayout by lazy {
            "androidx.constraintlayout:constraintlayout-compose:${Versions.Android.composeConstraintLayout}"
        }
        val composeTooling by lazy {
            "androidx.compose.ui:ui-tooling:1.3.3"
        }

        val composeToolingData by lazy {
            "androidx.compose.ui:ui-tooling-data:1.3.3"
        }
        val composeToolingPreview by lazy {
            "androidx.compose.ui:ui-tooling-preview:1.3.3"
        }
    }

    object Tests {
        val junit by lazy {
            "junit:junit:${Versions.Tests.junit}"
        }
        val androidJunit by lazy {
            "androidx.test.ext:junit:${Versions.Tests.androidJunit}"
        }
        val espressoCore by lazy {
            "androidx.test.espresso:espresso-core:${Versions.Tests.espressoCore}"
        }
    }

    object DI {
        val hilt by lazy {
            "com.google.dagger:hilt-android:${Versions.DI.hilt}"
        }
        val hiltCompiler by lazy {
            "com.google.dagger:hilt-compiler:${Versions.DI.hilt}"
        }
    }

    object MultiDex {
        val multiDex by lazy {
            "androidx.multidex:multidex:${Versions.MultiDex.multiDex}"
        }
    }

    object BottomNavigation {
        val chipNavigationBar by lazy {
            "com.github.ismaeldivita:chip-navigation-bar:${Versions.BottomNavigation.chipNavigationBar}"
        }
    }

    object Network {
        val gson by lazy {
            "com.google.code.gson:gson:${Versions.Network.gson}"
        }
        val gsonAdapter by lazy {
            "com.squareup.retrofit2:converter-gson:${Versions.Network.gsonConverter}"
        }
        val retrofit by lazy {
            "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        }
        val rxJavaAdapter by lazy {
            "com.squareup.retrofit2:adapter-rxjava3:${Versions.Network.rxJava3Adapter}"
        }
        val okHttp by lazy {
            "com.squareup.okhttp3:okhttp:${Versions.Network.okHttp}"
        }
        val loggingInterceptor by lazy {
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.loggingInterceptor}"
        }
    }
}