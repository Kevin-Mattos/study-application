import org.gradle.api.JavaVersion

object AppConfig {
	const val compileSdk = 31
	const val minSdk = 21
	const val targetSdk = 31
	const val versionCode = 1
	const val versionName = "1.0.0"
	const val buildToolsVersion = "30.0.3"

	const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
	const val proguardConsumerRules =  "proguard-rules.pro"
	const val proguardConsumerFile = "proguard-android-optimize.txt"
}

object AndroidConfig {
	const val jvmTarget = "1.8"
	val sourceCompatibility = JavaVersion.VERSION_1_8
	val targetCompatibility = JavaVersion.VERSION_1_8
}