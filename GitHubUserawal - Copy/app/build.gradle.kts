plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.githubuser_awal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.githubuser_awal"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "GITHUB_TOKEN", "\"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\"")
        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }

}

dependencies {
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.google.android.material:material:1.10.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.android.material:material:1.0.0")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.7.0")
    implementation ("androidx.viewpager:viewpager:1.0.0")
    implementation ("io.coil-kt:coil:1.4.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.fragment:fragment-ktx:1.4.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation("androidx.test:monitor:1.6.1")
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.3.1")
}
