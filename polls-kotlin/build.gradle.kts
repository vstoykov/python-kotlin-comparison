import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
	jacoco
	kotlin("jvm") version "1.9.22"
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
}

group = "examples"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

springBoot {
	mainClass.set("polls.PollsApplicationKt")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.data:spring-data-rest-hal-explorer")
	implementation("org.springframework.session:spring-session-core")
	implementation("io.pebbletemplates:pebble-spring-boot-starter:3.2.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.http4k:http4k-core:4.40.2.0")
	runtimeOnly("com.h2database:h2:2.2.224")
	implementation("org.postgresql:postgresql:42.6.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.security:spring-security-test")
	testImplementation(kotlin("test-junit5"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName<BootRun>("bootRun") {
	sourceResources(sourceSets["main"])
}

tasks.jar { enabled = false }
