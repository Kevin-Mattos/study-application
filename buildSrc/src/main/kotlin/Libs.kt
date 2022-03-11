import org.gradle.api.artifacts.dsl.DependencyHandler

object Libs {
	//std
	private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
	private val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
	const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
	const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"

	//android ui
	private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
	private val material = "com.google.android.material:material:${Versions.material_version}"
	private val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"

	//tests
	private val junit = "junit:junit:${Versions.junit_version}"
	private val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit_version}"
	private val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"

	val coreModuleLibraries = listOf(
		kotlinStdLib,
		core_ktx
	)

	val uiLibraries =
		listOf(
			material,
			appcompat,
			constraintlayout
		)

	val androidTestLibraries = listOf(
		ext_junit,
		espresso_core
	)

	val testLibraries = listOf(
		junit,
	)
}


fun DependencyHandler.kapt(list: List<String>) {
	list.forEach { dependency ->
		add("kapt", dependency)
	}
}

fun DependencyHandler.implementation(list: List<String>) {
	list.forEach { dependency ->
		add("implementation", dependency)
	}
}

fun DependencyHandler.api(list: List<String>) {
	list.forEach { dependency ->
		add("api", dependency)
	}
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
	list.forEach { dependency ->
		add("androidTestImplementation", dependency)
	}
}

fun DependencyHandler.testImplementation(list: List<String>) {
	list.forEach { dependency ->
		add("testImplementation", dependency)
	}
}