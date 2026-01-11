/*
 * Copyright 2025-present Li Ying.
 * Licensed under the MIT License.
 */

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "simple-mem-cache-spring-boot"
include("starter")
include("demo")
