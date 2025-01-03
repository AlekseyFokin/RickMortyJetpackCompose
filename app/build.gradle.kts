plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sniffsnirr.rickandmortyonjpc"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.sniffsnirr.rickandmortyonjpc"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.okhttp3)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.retrofit)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.coil)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.navigation.compos)

    testImplementation(kotlin("test"))

    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$version")
    androidTestImplementation("io.github.kakaocup:compose:0.3.0")
    androidTestImplementation ("com.kaspersky.android-components:kaspresso:1.5.5")
    androidTestImplementation ("com.kaspersky.android-components:kaspresso-compose-support:1.5.5")


}