/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
}

group = "cc.duduhuo.demo"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":starter"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
