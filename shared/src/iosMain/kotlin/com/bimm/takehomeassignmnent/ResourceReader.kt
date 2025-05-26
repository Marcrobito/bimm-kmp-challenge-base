package com.bimm.takehomeassignmnent

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*

/**
 * iOS-specific implementation of [readResourceFile].
 *
 * Loads a JSON file bundled in the app's resources using Foundation APIs.
 * Reads the file from the main bundle by name and returns its contents as a String.
 *
 * This actual function complements the expect declaration in the common module,
 * enabling shared code to request JSON data without platform-specific logic.
 *
 * @param name The resource filename (e.g., "sakeshop.json").
 * @return The raw JSON content loaded from the app bundle.
 * @throws IllegalStateException if the resource is missing or cannot be read.
 */
@OptIn(ExperimentalForeignApi::class)
actual fun readResourceFile(name: String): String {
    // Strip .json extension when locating the resource
    val baseName = name.removeSuffix(".json")
    val path = NSBundle.mainBundle.pathForResource(baseName, "json")
        ?: error("Resource not found: $name")
    val content = NSString.stringWithContentsOfFile(path, encoding = NSUTF8StringEncoding, error = null)
    return content ?: error("Could not read content of: $name")
}
