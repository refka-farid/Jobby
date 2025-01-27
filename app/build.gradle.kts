import utils.Factory

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

Factory.createHelloWord("from Build Gradle").also {
    println(it)
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = Versions.applicationId
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

configurations.all {
    resolutionStrategy { force("androidx.test:core:${Versions.test_core_ktx}") }
}

dependencies {

    implementation(project(":domain"))

    //kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")

    //androidx
    implementation("androidx.core:core-ktx:${Versions.core_ktx}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}")

    //ui
    implementation("com.google.android.material:material:${Versions.android_material}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}")
    implementation("androidx.recyclerview:recyclerview:${Versions.recyclerview}")

    //data
    implementation("androidx.preference:preference-ktx:${Versions.preference}")
    implementation("androidx.datastore:datastore-preferences:${Versions.datastore_preferences}")
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
    implementation("com.google.code.gson:gson:${Versions.gson}")
    implementation("org.danilopianini:gson-extras:${Versions.gson_extra}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp}")
    implementation("androidx.room:room-runtime:${Versions.room}")
    kapt("androidx.room:room-compiler:${Versions.room}")
    implementation("androidx.room:room-ktx:${Versions.room}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinx_serialization_converter}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}")

    //test
    //jvm-testsVersions
    testImplementation("androidx.arch.core:core-testing:${Versions.core_testing}")
    testImplementation("androidx.test:core-ktx:${Versions.test_core_ktx}")
    testImplementation("junit:junit:${Versions.junit}")
    testImplementation("com.google.truth:truth:${Versions.truth}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${Versions.mockito_kotlin}")
    testImplementation("org.mockito:mockito-core:${Versions.mockito_core}")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.mockito:mockito-inline:${Versions.mockito_core}")
    testImplementation("androidx.room:room-testing:${Versions.room}")

    //dagger hilt test
    androidTestImplementation("com.google.dagger:hilt-android-testing:${Versions.hilt}")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:${Versions.hilt}")

    //instrumented-tests
    androidTestImplementation("androidx.test.ext:junit:${Versions.test_ext_junit}")

    //espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso_core}")

}
