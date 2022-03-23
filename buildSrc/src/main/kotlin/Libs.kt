import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Libs {
	//std
	private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
	private const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
	const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
	const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"

	//android ui
	private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
	private const val material = "com.google.android.material:material:${Versions.material_version}"
	private const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"

	//	compose
	private const val composeUi ="androidx.compose.ui:ui:${Versions.compose_version}"
	private const val composeUiTooling ="androidx.compose.ui:ui-tooling:${Versions.compose_version}"
	private const val composeMaterial ="androidx.compose.material:material:${Versions.compose_version}"
	private const val composeActivity ="androidx.activity:activity-compose:${Versions.compose_activity_version}"

	//tests
	private const val junit = "junit:junit:${Versions.junit_version}"
	private const val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit_version}"
	private const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"

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

	val compose = listOf(
		composeUi,
		composeUiTooling,
		composeMaterial,
		composeActivity
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

fun DependencyHandlerScope.composeUi() {
	implementation(Libs.compose)
}