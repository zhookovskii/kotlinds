plugins {
    id("java")
    kotlin("jvm") version "1.9.23"

    id("org.jetbrains.kotlinx.dataframe") version "1.0.0-Beta2"
}

group = "com.zhukovskii.kotlinds"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:multik-core:0.2.3")
    implementation("org.jetbrains.kotlinx:multik-default:0.2.3")
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.8.0")
    implementation("org.jetbrains.lets-plot:lets-plot-batik:4.6.2")
    implementation("org.jetbrains.kotlinx:dataframe:1.0.0-Beta2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}