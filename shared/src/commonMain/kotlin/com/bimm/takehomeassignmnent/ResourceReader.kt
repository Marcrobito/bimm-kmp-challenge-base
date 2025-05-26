package com.bimm.takehomeassignmnent

/**
 * Reads a JSON resource file by name.
 *
 * Originally, the intention was for the shared (CommonMain) module to load the JSON
 * directly from its own resources directory and distribute it to each platform.
 * However, we encountered indexing issues and could not reliably read the file at runtime.
 * This function is expected to be implemented per-platform to load the JSON content.
 *
 * @param name The filename of the resource to load (e.g., "sakeshop.json").
 * @return The raw JSON content as a string.
 */
expect fun readResourceFile(name: String): String
