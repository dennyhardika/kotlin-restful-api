import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
	application
}

group = "programmer-zaman-now"
version = "0.0.1-SNAPSHOT"


java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()  // Jika menggunakan Kotlin DSL
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.6")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.testng:testng:6.9.6")
	implementation("org.testng:testng:6.9.6")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//	implementation("com.example:library:1.0")
	implementation("org.springframework.boot:spring-boot-starter-web:3.3.6")  // Menggunakan pustaka dari Maven Central
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.25")

	// set baru
	implementation("org.postgresql:postgresql")
	implementation(kotlin("stdlib"))

	testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("net.coobird:thumbnailator:0.4.14")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.jar {
	manifest {
		attributes(
			"Main-Class" to "com.example.Main"  // Pastikan nama kelas utama Anda benar
		)
	}
}

