// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    repositories{
        google()
        mavenCentral()
        maven {
            url = java.net.URI.create("https://jitpack.io")
        }
    }
}

plugins {
    id(Plugins.androidApplication) version Versions.Plugins.androidApplication apply false
    id(Plugins.jetbrainsKotlinAndroid) version Versions.Plugins.jetbrainsKotlinAndroid apply false
    id(Plugins.daggerHilt) version Versions.DI.hilt apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}