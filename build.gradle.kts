import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    kotlin("jvm") version "1.2.41"
    application
    distribution
    id("maven-publish")
    id("com.github.ben-manes.versions") version "0.17.0"
    id("com.diffplug.gradle.spotless") version "3.10.0"
    id("org.sonarqube") version "2.6.2"
    id("net.researchgate.release") version "2.6.0"

}

application {
    mainClassName = "com.github.alexandrenavarro.tornadofxsample.TornadofxApp"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:1.7.15")
    implementation(kotlin("reflect"))
    implementation("io.github.openfeign:feign-core:9.6.0")
    implementation("io.github.openfeign:feign-jackson:9.6.0")
    implementation("org.springframework.cloud:spring-cloud-openfeign-core:2.0.0.RC1")
    implementation("org.springframework:spring-web:5.0.5.RELEASE")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

}

val compileKotlin: KotlinCompile<KotlinJvmOptions> by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
    jcenter()
    mavenCentral()
    maven("https://repo.spring.io/libs-milestone-local/")
}


spotless {
    kotlin {
        ktlint()
        paddedCell()
    }

}

