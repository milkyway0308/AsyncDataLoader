plugins {
    id("java")
    id ("maven-publish")
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
    maven("https://maven.pkg.github.com/milkyway0308/BungeePlayerSync") {
        credentials {
            username = properties["gpr.user"] as String
            password = properties["gpr.key"] as String
        }
    }

}

dependencies {
    // java dependencies
    compileOnly(files("V:/API/Java/Minecraft/Bukkit/Spigot/Spigot 1.12.2.jar"))
    implementation("skywolf46:bss:1.2.0")

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
    }
    publications{
        create<MavenPublication>("jar"){
            from(components["java"])
            groupId = "skywolf46"
            artifactId = "asyncdataloader"
            version = properties["version"] as String
            pom{
                url.set("https://github.com/milkyway0308/AsyncDataLoader.git")
            }
        }
    }
}