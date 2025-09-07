import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        AppKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}