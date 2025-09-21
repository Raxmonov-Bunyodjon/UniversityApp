plugins {
    // 🔹 Asosiy Android va Kotlin pluginlari
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // 🔹 Hilt DI plugin
    id("com.google.dagger.hilt.android")

    // 🔹 KSP (Kotlin Symbol Processing) plugin
    id("com.google.devtools.ksp")

    // 🔹 Safe Args (Navigation uchun tipli argumentlar)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.universityapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.universityapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // 🔹 ViewBinding yoqilgan
    buildFeatures {
        viewBinding = true
    }

    ndkVersion = "26.1.10909125"

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // 🔹 Java va Kotlin versiyalari
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

// 🔹 KSP konfiguratsiyasi (Room va boshqa annotation processorlar uchun)
ksp {
    arg("room.schemaLocation", "$projectDir/schemas") // Room schemas joyi
    arg("room.incremental", "true")                  // Incremental kompilyatsiya
    arg("room.expandProjection", "true")            // Projection kengaytirish
}

dependencies {
    // 🔹 Asosiy Android kutubxonalari
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // 🔹 Hilt (Dependency Injection)
    implementation("com.google.dagger:hilt-android:2.56.2")
    ksp("com.google.dagger:hilt-compiler:2.56.2")

    // 🔹 Room (Local database)
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // 🔹 Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // 🔹 Lifecycle (ViewModel va LiveData)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    // 🔹 Fragment KTX
    implementation("androidx.fragment:fragment-ktx:1.8.9")

    // 🔹 Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.3")

    // 🔹 Glide (rasmlar bilan ishlash)
    implementation("com.github.bumptech.glide:glide:5.0.4")

    // 🔹 Lottie (animatsiyalar)
    implementation("com.airbnb.android:lottie:6.1.0")

    // 🔹 Testing kutubxonalari
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // 🔹 DataStore (preference va boshqa saqlash)
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.datastore:datastore:1.1.1")

    // 🔹 CircleImageView (profil rasmlari uchun)
    implementation("de.hdodenhof:circleimageview:3.1.0")
}
