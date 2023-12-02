import org.jetbrains.kotlin.gradle.targets.jvm.tasks.KotlinJvmTest

tasks {
    withType<KotlinJvmTest> {
        useJUnitPlatform()
    }
}
