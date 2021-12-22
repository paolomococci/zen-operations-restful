import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
}

group = "local.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("com.jayway.jsonpath:json-path:2.4.0")
    implementation("org.webjars:angularjs:1.7.7")
    implementation("org.webjars:bootstrap:4.3.1")
    implementation("org.webjars:jquery:3.4.1")
    implementation("org.webjars:popper.js:1.15.0")
    implementation("org.webjars:font-awesome:5.9.0")
    implementation("org.webjars.npm:feather-icons:4.22.1")
    implementation("org.webjars:chartjs:26962ce-1")
    implementation("org.webjars:d3js:5.9.1")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}
