//
//  SakeShopsViewModelTests.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 26/05/25.
//

import XCTest
import Combine
@testable import KMP_Take_Home_Assignment
import Shared

/// A fake repository that always returns the given list of shops.
private class FakeSuccessRepository: SakeShopsRepository {
    let shops: [SakeShop]
    init(shops: [SakeShop]) { self.shops = shops }
    func getSakeShops() async throws -> [SakeShop] {
        return shops
    }
}

/// A fake repository that always throws the given error.
private class FakeErrorRepository: SakeShopsRepository {
    let error: Error
    init(error: Error) { self.error = error }
    func getSakeShops() async throws -> [SakeShop] {
        throw error
    }
}

final class SakeShopsViewModelTests: XCTestCase {
    private var cancellables = Set<AnyCancellable>()

    @MainActor func testLoadDataSuccess() {
        // Given
        let expected = [
            SakeShop(name: "A", description: "D", picture: nil, rating: 4.0,
                     address: "Addr", latitude: 0, longitude: 0,
                     googleMapsLink: "", website: "")
        ]
        let repo = FakeSuccessRepository(shops: expected)
        let vm = SakeShopsViewModel(repository: repo)

        // Assert initial state
        XCTAssertEqual(vm.state, .notInitialized)

        // When
        vm.loadData()

        // Immediately enters .loading
        XCTAssertEqual(vm.state, .loading)

        // Then wait for .success
        let exp = expectation(description: "Wait for success state")
        vm.$state
            .sink { state in
                if case .success(let shops) = state {
                    XCTAssertEqual(shops, expected)
                    exp.fulfill()
                }
            }
            .store(in: &cancellables)

        wait(for: [exp], timeout: 2.0)
    }

    @MainActor func testLoadDataError() {
        // Given
        let testError = NSError(domain: "Test", code: 42, userInfo: [NSLocalizedDescriptionKey: "Boom"])
        let repo = FakeErrorRepository(error: testError)
        let vm = SakeShopsViewModel(repository: repo)

        // When
        vm.loadData()

        // Then
        let exp = expectation(description: "Wait for error state")
        vm.$state
            .sink { state in
                if case .error(let message) = state {
                    XCTAssertEqual(message, "Boom")
                    exp.fulfill()
                }
            }
            .store(in: &cancellables)

        wait(for: [exp], timeout: 2.0)
    }
}

