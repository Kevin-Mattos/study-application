plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.ktLint) version Versions.ktlint_version
    id(BuildPlugins.detect) version Versions.detect_version
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = "com.example.studyapplication"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(AppConfig.proguardConsumerFile), AppConfig.proguardConsumerRules
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
}

dependencies {
    implementation(Libs.coreModuleLibraries)
    testImplementation(Libs.testLibraries)
    androidTestImplementation(Libs.androidTestLibraries)
    koin()

    implementation(project(path = ":coreUi"))
    implementation(project(path = ":datasource"))
    implementation(project(path = ":data:auth"))
    implementation(project(path = ":domain:auth"))
    implementation(project(path = ":ui:featureauth"))
}
