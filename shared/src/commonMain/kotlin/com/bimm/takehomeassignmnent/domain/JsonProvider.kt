package com.bimm.takehomeassignmnent.domain

/**
 * Provides a source for loading JSON data from various origins,
 * allowing different implementations to be injected per platform.
 */
fun interface JsonProvider {
    /**
     * Loads and returns the JSON content as a string.
     *
     * @return The loaded JSON data.
     */
    fun loadJson(): String
}

