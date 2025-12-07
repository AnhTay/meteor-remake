plugins {
    id("fabric-loom") version("1.2.7")
    id("maven-publish")
    java
}

version = "1.21.4"
group = "com.sieucapyeuem"

repositories {
    // Maven chính thức của SieuCapYeuEm
    maven {
        name = "SieuCapYeuEm-Maven"
        url = uri("https://maven.meteordev.org/releases")
    }

    // ⚙️ Maven chính thức của Fabric
    maven {
        name = "FabricMC"
        url = uri("https://maven.fabricmc.net/")
    }

    // Repo phổ biến cho các mod khác
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/releases/")
    }
    maven {
        name = "Architectury"
        url = uri("https://maven.architectury.dev/")
    }
    mavenCentral()
    mavenLocal()
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.4")
    mappings("net.fabricmc:yarn:1.21.4+build.1:v2")
    modImplementation("net.fabricmc:fabric-loader:0.16.9")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.137.0+1.21.4")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.processResources {
    filesMatching("fabric.mod.json") {
        expand(
            mapOf
