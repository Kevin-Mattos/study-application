plugins {
	id (BuildPlugins.androidLibrary)
	id (BuildPlugins.kotlinAndroid)
}

android {
	compileSdk = AppConfig.compileSdk

	defaultConfig {
		minSdk = AppConfig.minSdk
		targetSdk = AppConfig.targetSdk

		testInstrumentationRunner = AppConfig.androidTestInstrumentation
	}

	kotlinOptions {
		jvmTarget = AndroidConfig.jvmTarget
	}

	compileOptions {
		sourceCompatibility = AndroidConfig.sourceCompatibility
		targetCompatibility = AndroidConfig.targetCompatibility
	}
}

dependencies {
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

	implementation(Libs.coreModuleLibraries)
	api(Libs.uiLibraries)

	testImplementation(Libs.testLibraries)
	androidTestImplementation(Libs.androidTestLibraries)
}