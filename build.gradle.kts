import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.metadata.jvm.deserialization.bytesToStrings

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.2.41"
    application
    distribution
    id("maven-publish")
    id("jacoco")
    id("com.github.ben-manes.versions") version "0.17.0"
    id("com.diffplug.gradle.spotless") version "3.12.0"
    id("org.sonarqube") version "2.6.2"
    id("net.researchgate.release") version "2.7.0"
}

val javaVersion: JavaVersion by extra { JavaVersion.VERSION_1_8 }

// dependencies bom
val springBootVersion = "2.0.2.RELEASE"
val springCloudVersion = "Finchley.RC1"

// implementation
val tornadoFxVersion = "1.7.17"
val kotlinLoggingVersion = "1.5.4"
val kotlinxCoroutinesJavafxVersion = "0.22.5"

// runtime
val logbackClassicVersion = "1.2.3"

// test
val kluentVersion = "1.38"
val mockitoKotlinVersion = "2.0.0-alpha04"
val testfxVersion = "4.0.13-alpha"

// tool
val jacocoVersion = "0.7.9"

application {
    mainClassName = "com.github.alexandrenavarro.tornadofxsample.CountryApp"
}

dependencies {

    // bomd
    implementation("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
    implementation("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")


    // kotlin base
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // spring / feign : Just set to use Feign with spring
    implementation("org.springframework:spring-web") {
        exclude("org.springframework", "spring-aop")
    }
    implementation("org.springframework.cloud:spring-cloud-openfeign-core") {
        exclude("org.springframework", "spring-aop")
        exclude("org.springframework.boot", "spring-boot-starter")
        exclude("org.springframework.boot", "spring-boot-starter-aop")
        exclude("org.springframework.cloud", "spring-cloud-netflix-archaius")
        exclude("org.springframework.cloud", "spring-cloud-netflix-ribbon")
    }
    implementation("io.github.openfeign:feign-core")
    implementation("io.github.openfeign:feign-jackson")

    // logging
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")

    // tornadofx
    implementation("no.tornado:tornadofx:$tornadoFxVersion") {
        exclude("org.glassfish", "javax.json")
    }

    // coroutine javafx
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:$kotlinxCoroutinesJavafxVersion")

    // logging runtime
    runtime("ch.qos.logback:logback-classic:$logbackClassicVersion")

    // test
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.amshove.kluent:kluent:$kluentVersion")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion")
    testImplementation("org.testfx:testfx-junit:$testfxVersion")

}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

jacoco {
    toolVersion = "$jacocoVersion"
}


tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}


// Equivalent
//val compileKotlin: KotlinCompile<KotlinJvmOptions> by tasks
//compileKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
    jcenter()
    mavenCentral()
    maven("https://repo.spring.io/libs-milestone-local/")
}


//spotless {
//    kotlin {
//        ktlint()
//        paddedCell()
//    }
//}

