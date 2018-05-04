import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    kotlin("jvm") version "1.2.41"
    id("com.github.ben-manes.versions") version "0.17.0"
    application
}

application {
    mainClassName = "com.github.alexandrenavarro.tornadofxsample.Application"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:1.7.15")
    compile(kotlin("reflect"))
    testCompile(kotlin("test"))
    testCompile(kotlin("test-junit"))
}

val compileKotlin: KotlinCompile<KotlinJvmOptions> by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
    jcenter()
}
