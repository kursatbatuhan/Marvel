import org.gradle.api.artifacts.dsl.DependencyHandler

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
}