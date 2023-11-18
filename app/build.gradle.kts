import java.io.FileInputStream
import java.util.Properties;

plugins {
    id("com.android.application")
}


// local.properties 읽기 과정
var properties = Properties()
properties.load(FileInputStream("local.properties"))

android {
    namespace = "com.example.spotapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.spotapp"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // 읽기 과정 - BuildConfig에 등록
        buildConfigField("String", "SERVER_IP", properties.getProperty("serverAddress"))
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

    // 읽기 과정
    buildFeatures {
        buildConfig = true
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.kakao.maps.open:android:2.6.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}