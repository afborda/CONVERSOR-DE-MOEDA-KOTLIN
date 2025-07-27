plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.serialization)
}


android {
  namespace = "com.study.conversordemoeda"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.study.conversordemoeda"
    minSdk = 26
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    buildConfig = true  // Adicione esta linha
    viewBinding = true
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

}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)

  // Dependências do Ktor
  implementation(libs.ktor.client.core)
  implementation(libs.ktor.client.android)
  implementation(libs.ktor.client.content.negotiation)
  implementation(libs.ktor.serialization.kotlinx.json)
  implementation(libs.ktor.client.logging)

  // Dependência do Kotlinx Serialization
  implementation(libs.kotlinx.serialization.json)

  // Dependências do Lifecycle
  implementation(libs.lifecycle.viewmodel)
  implementation(libs.lifecycle.runtime)

  //Dependências do Coil
  implementation(libs.coil)
  implementation(libs.coil.svg)


  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}
