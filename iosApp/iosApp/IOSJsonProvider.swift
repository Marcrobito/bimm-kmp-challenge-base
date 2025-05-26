//
//  IOSJsonProvider.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import Foundation
import Shared

/// Provides JSON data from the app bundle for use in the shared module.
///
/// Implements the `JsonProvider` interface, loading the `sakeshop.json`
/// resource packaged in the iOS app bundle.
public class IOSJsonProvider: JsonProvider {
    /// Loads the `sakeshop.json` file from the main bundle and returns its contents as a string.
    ///
    /// - Returns: The JSON string read from the `sakeshop.json` file.
    /// - Throws: A runtime error if the file is not found or cannot be read.
    public func loadJson() -> String {
        guard let url = Bundle.main.url(forResource: "sakeshop", withExtension: "json") else {
            fatalError("sakeshop.json not found in bundle.")
        }
        do {
            let data = try Data(contentsOf: url)
            guard let jsonString = String(data: data, encoding: .utf8) else {
                fatalError("Failed to decode sakeshop.json as UTF-8 string.")
            }
            return jsonString
        } catch {
            fatalError("Failed to read sakeshop.json: \(error)")
        }
    }
}
