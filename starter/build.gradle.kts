import org.jreleaser.model.Active

/*
 * Copyright 2025 Li Ying.
 * Licensed under the MIT License.
 */

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jreleaser")
    `java-library`
    `maven-publish`
}

group = "cc.duduhuo"
version = "1.2.1-1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    api("cc.duduhuo:simple-mem-cache:1.2.1")
    implementation("org.springframework.boot:spring-boot-starter")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    archiveClassifier.set("")
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to "Spring Boot Starter for Simple Mem Cache",
                "Implementation-Version" to archiveVersion,
                "Implementation-Vendor" to "Li Ying",
                "Built-By" to System.getProperty("user.name"),
                "Built-JDK" to System.getProperty("java.version"),
                "Built-Gradle" to gradle.gradleVersion
            )
        )
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "simple-mem-cache-spring-boot-starter"

            from(components["java"])

            pom {
                name = "Simple Mem Cache Spring Boot Starter"
                description =
                    "A lightweight, high-performance, and dependency-free in-memory cache solution with TTL and concurrent access support, ideal for storing hot data in web applications through a minimalistic, ready-to-use API."
                url = "https://github.com/liying2008/simple-mem-cache-spring-boot"
                inceptionYear = "2025"
                packaging = "jar"
                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/license/MIT"
                    }
                }
                developers {
                    developer {
                        name = "Li Ying"
                        email = "liruoer2008@yeah.net"
                        url = "https://github.com/liying2008"
                    }
                }
                scm {
                    url = "https://github.com/liying2008/simple-mem-cache-spring-boot"
                    connection = "scm:git:https://github.com/liying2008/simple-mem-cache-spring-boot.git"
                    developerConnection = "scm:git:https://github.com/liying2008/simple-mem-cache-spring-boot.git"
                }
            }
        }
    }
    repositories {
        maven {
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}

jreleaser {
    gitRootSearch = true
    signing {
        active = Active.ALWAYS
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                register("sonatype") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(layout.buildDirectory.dir("staging-deploy").get().asFile.path)
                }
            }
        }
    }
}
