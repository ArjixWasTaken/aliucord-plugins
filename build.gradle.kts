import com.aliucord.gradle.AliucordExtension
import com.android.build.gradle.BaseExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.aliucord.com/snapshots")
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.aliucord:gradle:main-SNAPSHOT")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.aliucord.com/snapshots")
    }
}

fun Project.android(configuration: BaseExtension.() -> Unit) =
        extensions.getByName<BaseExtension>("android").configuration()

fun Project.aliucord(configuration: AliucordExtension.() -> Unit) =
        extensions.getByName<AliucordExtension>("aliucord").configuration()

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "com.aliucord.gradle")
    apply(plugin = "kotlin-android")

    aliucord {
        author("Arjix", 674710789138939916L)
        updateUrl.set("https://raw.githubusercontent.com/ArjixWasTaken/aliucord-plugins/builds/updater.json")
        buildUrl.set("https://raw.githubusercontent.com/ArjixWasTaken/aliucord-plugins/builds/%s.zip")
    }

    android {

        defaultConfig {
            minSdk = 24
            //noinspection ExpiredTargetSdkVersion
            targetSdk = 31
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = freeCompilerArgs +
                        "-Xno-call-assertions" +
                        "-Xno-param-assertions" +
                        "-Xno-receiver-assertions"
            }
        }
    }

    dependencies {
        val discord by configurations
        val implementation by configurations

        discord("com.discord:discord:aliucord-SNAPSHOT")
        implementation("com.aliucord:Aliucord:main-SNAPSHOT")

        implementation("androidx.appcompat:appcompat:1.4.0")
        implementation("com.google.android.material:material:1.4.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}