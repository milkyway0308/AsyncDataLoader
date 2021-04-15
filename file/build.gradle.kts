import org.jetbrains.kotlin.cli.common.toBooleanLenient

plugins {
    kotlin("jvm")
    `maven-publish`
    java
}


repositories {
    mavenCentral()
    jcenter()
    maven(rootProject.properties["reposilite.release"].toString())
    maven(rootProject.properties["reposilite.spigot"].toString())
}

dependencies {
    compileOnly(project(":core"))
    compileOnly(kotlin("stdlib"))
    compileOnly("org.spigotmc", "spigot", "1.12.2")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.4.2")
    testImplementation(project(":core"))
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.4.2")
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        archiveBaseName.set("AsyncDataLoader-${project.name.capitalize()}")
    }

    processResources {
        filesMatching(listOf("loaderDeclaration.properties", "plugin.yml")) {
            expand("version" to rootProject.properties["version"])
        }
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    repositories {
        maven {
            name = "Reposilite"
            setUrl(
                if (rootProject.properties["snapshot"].toString().toBoolean())
                    rootProject.properties["reposilite.snapshot"].toString()
                else
                    rootProject.properties["reposilite.release"].toString()
            )

            credentials {
                username = rootProject.properties["reposilite.user"].toString()
                password = rootProject.properties["reposilite.token"].toString()
            }
        }
    }

    publications {
        create<MavenPublication>("jar") {
            groupId = "skywolf46"
            artifactId = "asyncdataloader-file"
            version = rootProject.properties["version"] as String
            from(components["java"])
        }
    }
}