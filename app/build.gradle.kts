plugins {
    id(Plugins.androidApplication)
    id(Plugins.jetbrainsKotlinAndroid)
    id(Plugins.kapt)
    id(Plugins.daggerHilt)
    id("com.google.gms.google-services")
}

android {
    namespace = Configs.applicationId
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = Configs.testInstrumentationRunner
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Configs.proguardAndroidOptimize),
                Configs.proguardRules
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        enable = true
    }
    viewBinding {
        enable = true
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    kapt {
        correctErrorTypes = true
        useBuildCache = true
    }
    kotlinOptions {
        jvmTarget = Configs.jvmTarget
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
}

dependencies {
    //Android
    implementation(Dependencies.Android.androidCoreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.androidNavigationUI)
    implementation(Dependencies.Android.androidNavigationFragment)
    implementation(Dependencies.Android.stdlib)

    //Compose
    api(Dependencies.Android.composeBom)
    api(Dependencies.Android.compose)
    api(Dependencies.Android.composeFoundation)
    api(Dependencies.Android.composeMaterial)
    api(Dependencies.Android.composeConstraintLayout)
    debugApi(Dependencies.Android.composeTooling)
    debugApi(Dependencies.Android.composeToolingData)
    api(Dependencies.Android.composeToolingPreview)

    //Tests
    testImplementation(Dependencies.Tests.junit)
    androidTestImplementation(Dependencies.Tests.androidJunit)
    androidTestImplementation(Dependencies.Tests.espressoCore)

    //DI
    implementation(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)

    //MultiDex
    implementation(Dependencies.MultiDex.multiDex)

    //BottomNavigation
    implementation(Dependencies.BottomNavigation.chipNavigationBar)

    // Network
    implementation(Dependencies.Network.gson)
    implementation(Dependencies.Network.gsonAdapter)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.loggingInterceptor)
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
    implementation("com.google.firebase:firebase-analytics:21.2.0")

}