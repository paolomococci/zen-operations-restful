import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
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
    implementation("com.jayway.jsonpath:json-path:2.6.0")
    implementation("org.webjars:angularjs:2.0.0-alpha.22")
    implementation("org.webjars:bootstrap:5.1.3")
    implementation("org.webjars:jquery:3.6.0")
    implementation("org.webjars:popper.js:2.9.3")
    implementation("org.webjars:font-awesome:5.15.4")
    implementation("org.webjars.npm:feather-icons:4.28.0")
    implementation("org.webjars:chartjs:26962ce-1")
    implementation("org.webjars:d3js:6.6.0")
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
