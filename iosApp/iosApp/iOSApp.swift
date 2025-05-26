import SwiftUI
import Shared

/// The main entry point of the iOS application.
///
/// - Responsibilities:
///   1. Initialize the shared dependency injection container.
///   2. Create and inject the `SakeShopsViewModel` for the root view.
///   3. Present `SakeShopsListView` and trigger data loading on appear.
@main
struct iOSApp: App {
    /// The view model powering the Sake Shops list.
    @StateObject private var viewModel: SakeShopsViewModel

    /// Configures DI and instantiates the view model.
    ///
    /// 1. Calls into the shared Koin setup with an iOS JSON provider.
///  2. Uses `SharedFactory` to obtain a repository implementation.
///  3. Wraps the view model in a `StateObject` to use in SwiftUI.
    init() {
        // Start Koin with the iOS JSON loader
        KoinKt.doInitKoin(jsonProvider: IOSJsonProvider())

        // Provide a repository from the shared module
        let jsonProvider = IOSJsonProvider()
        let repository = SharedFactory().provideSakeShopsRepository(jsonProvider: jsonProvider)

        // Initialize the view model as a StateObject for SwiftUI
        _viewModel = StateObject(wrappedValue: SakeShopsViewModel(repository: repository))
    }

    /// The scene graph for the application.
    ///
    /// - Presents `SakeShopsListView`.
    /// - Triggers `loadData()` when the view appears.
    var body: some Scene {
        WindowGroup {
            SakeShopsListView(viewModel: viewModel)
                .onAppear {
                    viewModel.loadData()
                }
        }
    }
}
