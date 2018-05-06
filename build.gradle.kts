import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
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

val javaVersion: JavaVersion by extra { JavaVersion.VERSION_1_8 }
val springVersion = "5.0.5.RELEASE"

application {
    mainClassName = "com.github.alexandrenavarro.tornadofxsample.TornadofxApp"
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("no.tornado:tornadofx:1.7.15") {
        exclude("org.glassfish", "javax.json")
    }
    compile(kotlin("reflect"))
    compile("io.github.openfeign:feign-core:9.6.0")
    compile("io.github.openfeign:feign-jackson:9.6.0")

    // Just set to use Feign with spring
    compile("org.springframework:spring-web:5.0.5.RELEASE") {
        exclude("org.springframework", "spring-aop")
        //exclude("org.springframework", "spring-expression") // need if use a beans {}
    }
    compile("org.springframework.cloud:spring-cloud-openfeign-core:2.0.0.RC1") {
        exclude("org.springframework", "spring-aop")
        //exclude("org.springframework", "spring-expression") // need if use a beans {}
        exclude("org.springframework.boot", "spring-boot-starter")
        exclude("org.springframework.boot", "spring-boot-starter-aop")
        exclude("org.springframework.cloud", "spring-cloud-netflix-archaius")
        exclude("org.springframework.cloud", "spring-cloud-netflix-ribbon")
    }
    compile("io.github.microutils:kotlin-logging:1.5.4")
    runtime("ch.qos.logback:logback-classic:1.0.13")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

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
//
//}

