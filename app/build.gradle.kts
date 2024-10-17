import org.apache.tools.ant.util.JavaEnvUtils.VERSION_11
import org.apache.tools.ant.util.JavaEnvUtils.VERSION_1_8
import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.tictactoeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tictactoeapp"
        minSdk = 27
        //noinspection OldTargetApi
        targetSdk =34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {


        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }
    buildToolsVersion = "34.0.0"
}

dependencies {
    implementation("com.google.firebase:firebase-inappmessaging-display:21.0.0")
    implementation("com.google.guava:guava:33.3.1-android")
    implementation(libs.butterknife.compiler)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}