plugins {
    kotlin("jvm") version "1.4.32"
    id("maven-publish")
}

buildscript {
    repositories {
        mavenCentral()
    }
}

group = "skywolf46"
version = properties["version"] as String

tasks {
    processResources {
        expand("version" to project.properties["version"])
    }
}

repositories {
    mavenCentral()
    maven(properties["reposilite.release"] as String)
    maven(properties["reposilite.spigot"] as String)
}


dependencies {
    // java dependencies
    compileOnly(kotlin("stdlib"))

    compileOnly("org.spigotmc:spigot:1.12.2")
    compileOnly("skywolf46:bss:1.6.1")
    compileOnly("skywolf46:exutil:1.35.2") {
        isChanging = true
    }
}

publishing {
    repositories {
        maven {
            name = "Github"
            url = uri("https://maven.pkg.github.com/milkyway0308/AsyncDataLoader")
            credentials {
                username = properties["gpr.user"] as String
                password = properties["gpr.key"] as String
            }
        }
        maven {
            name = "Reposilite"
            url = uri(properties["reposilite.release"] as String)
            credentials {
                username = properties["reposilite.user"] as String
                password = properties["reposilite.token"] as String
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("jar") {
            from(components["java"])
            groupId = "skywolf46"
            artifactId = "asyncdataloader"
            version = properties["version"] as String
        }
    }
}