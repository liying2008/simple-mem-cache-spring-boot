/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

plugins {
    id("org.springframework.boot") version "2.6.13" apply false
    id("io.spring.dependency-management") version "1.0.15.RELEASE" apply false
    id("org.jreleaser") version "1.21.0" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.encoding = "UTF-8"
    }

    tasks.withType<Javadoc> {
        options.encoding = "UTF-8"
    }
}
