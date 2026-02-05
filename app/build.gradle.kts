plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization")


}

android {
    namespace = "com.example.ktor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ktor"
        minSdk = 32
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // K-tor

    // implementation(platform("io.ktor:ktor-bom:3.1.2"))
    implementation(platform(libs.ktor.bom))


    // K-tor
    val ktorVersion = "3.2.2"

    // implementation(platform("io.ktor:ktor-bom:3.1.2"))
    implementation(platform("io.ktor:ktor-bom:$ktorVersion"))


    // implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")


    // implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")


    // implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
    // implementation(libs.ktor.client.serialization.jvm)

    // implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")



    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")


    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    implementation("io.ktor:ktor-client-encoding:$ktorVersion")











}