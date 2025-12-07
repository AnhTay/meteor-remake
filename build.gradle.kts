plugins {
    alias(libs.plugins.fabric.loom)
    id("maven-publish")
}

base {
    archivesName = "SieuCapYeuEm"
    group = "com.sieucapyeuem"
    version = libs.versions.minecraft.get() + "-local"
}

repositories {
    maven {
        name = "meteor-maven"
        url = uri("https://maven.meteordev.org/releases")
    }
    maven {
        name = "meteor-maven-snapshots"
        url = uri("https://maven.meteordev.org/snapshots")
    }
    maven {
        name = "FabricMC"
        url = uri("https://maven.fabricmc.net/")
    }
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com")
    }
    mavenCentral()
}

dependencies {
    minecraft(libs.minecraft)
    mappings(variantOf(libs.yarn) { classifier("v2") })
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
}

loom {
    accessWidenerPath = file("src/main/resources/sieucapyeuem.accesswidener")
}

tasks {
    processResources {
        val props = mapOf(
            "version" to project.version,
            "mod_id" to "sieucapyeuem",
            "mod_name" to "SieuCapYeuEm",
            "minecraft_version" to libs.versions.minecraft.get()
        )

        inputs.properties(props)
        filesMatching("fabric.mod.json") {
            expand(props)
        }
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${project.name}" }
        }
        manifest {
            attributes["Main-Class"] = "com.sieucapyeuem.Main"
        }
    }

    withType<JavaCompile> {
        options.release.set(21)
        options.encoding = "UTF-8"
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "sieucapyeuem"
            version = libs.versions.minecraft.get() + "-SNAPSHOT"
        }
    }
}
