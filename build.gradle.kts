plugins {
    `maven-publish`
    kotlin("jvm") version "1.4.32" apply false
}

buildscript {
    repositories {
        mavenCentral()
    }
}

group = "skywolf46"
version = properties["version"] as String

//tasks {
//    processResources {
//        expand("version" to project.properties["version"])
//    }
//}

repositories {
    mavenCentral()
    maven(properties["reposilite.release"] as String)
    maven(properties["reposilite.spigot"] as String)
}

dependencies {
    // java dependencies

}

//publishing {
//    repositories {
//        maven {
//            name = "Github"
//            url = uri("https://maven.pkg.github.com/milkyway0308/AsyncDataLoader")
//            credentials {
//                username = properties["gpr.user"] as String
//                password = properties["gpr.key"] as String
//            }
//        }
//        maven {
//            name = "Reposilite"
//            url = uri(properties["reposilite.release"] as String)
//            credentials {
//                username = properties["reposilite.user"] as String
//                password = properties["reposilite.token"] as String
//            }
//            authentication {
//                create<BasicAuthentication>("basic")
//            }
//        }
//    }
//    publications {
//        create<MavenPublication>("jar") {
//            groupId = "skywolf46"
//            artifactId = "asyncdataloader-all"
//            version = properties["version"] as String
//        }
//    }
//}