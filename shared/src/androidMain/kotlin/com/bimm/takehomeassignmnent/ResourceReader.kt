package com.bimm.takehomeassignmnent

/**
 * Android-specific implementation of [readResourceFile].
 *
 * Loads a JSON resource file from the classpath using the context ClassLoader.
 * Reads the file into a String and returns its contents.
 *
 * This actual function complements the expect declaration in the common module,
 * enabling shared code to request JSON data without platform-specific logic.
 *
 * @param name The resource filename (e.g., "sakeshop.json").
 * @return The raw JSON content loaded from the classpath resource.
 * @throws IllegalArgumentException if the resource is missing or cannot be read.
 */
actual fun readResourceFile(name: String): String {
    val stream = Thread.currentThread()
        .contextClassLoader
        ?.getResourceAsStream(name)
        ?: throw IllegalArgumentException("Resource not found: $name")
    return stream.bufferedReader().use { it.readText() }
}
