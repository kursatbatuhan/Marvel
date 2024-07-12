// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.androidApplication) version Versions.Plugins.androidApplication apply false
    id(Plugins.jetbrainsKotlinAndroid) version Versions.Plugins.jetbrainsKotlinAndroid apply false
    id(Plugins.daggerHilt) version Versions.DI.hilt apply false
}