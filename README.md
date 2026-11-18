<div align="center">

# ◷ K-Clock Widgets

### Make every minute look yours.

A customisable Android clock-widget collection with expressive gradients, carefully tuned colour palettes and a clean dark design.

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://www.android.com/)
[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Build](https://img.shields.io/badge/Build-GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)](https://github.com/features/actions)
[![Widgets](https://img.shields.io/badge/Widget_styles-29-9B75E8?style=for-the-badge)](#-widget-gallery)

**Designed & crafted with ♥ by [Krishna](https://www.instagram.com/krishnaxvis/)**

</div>

---

## ✦ Overview

K-Clock Widgets brings a collection of Android home-screen clocks into one lightweight app. Choose a style, tune its colours and details, preview the look, then add it to your launcher.

- **29 clock styles** — from AMOLED minimal to neon, gradient and playful designs.
- **Personal styling** — gradient colours, text and accent colours, angle, corner radius, patterns and borders.
- **Quick palettes** — curated presets plus a random gradient option.
- **Home-screen ready** — add supported widgets directly from the app or from your launcher.
- **Creator support** — Instagram and UPI support links live in About & Support, away from the main widget gallery.

## ✨ Highlights

| Feature | Details |
|---|---|
| Time display | 12-hour / 24-hour formats supported by the existing widget implementation |
| Colour controls | HEX colours for gradient start/end, text and accent |
| Gradient | Adjustable direction / angle |
| Surface | Corner radius and optional border |
| Patterns | None, grid, dots, scan and stars (where supported by the renderer) |
| Presets | Curated palettes and random colour combinations |
| Widget actions | Save design, reset style and add widget to the home screen |
| App appearance | Dark AMOLED-inspired gallery and dedicated preferences / support area |

## 🎨 Widget gallery

The project includes the original styles plus four additional visual variations.

| Collection | Styles |
|---|---|
| Hacker / Tech | Hacker Terminal, Hacker Split, Matrix Strip, Red Alert, Dracula Shell, Retro Pixel |
| Neon / Gamer | Neon Night, Cyberpunk Yellow, Gamer RGB, Toxic Lime |
| Gradient / Aesthetic | Aura, Sunset, Vaporwave, Aurora, Galaxy, Lava, Mint Pop, Ocean Stack, Candy Pop |
| Minimal / Premium | Glass Dark, Mono Dark, Gold Luxe, Ice, Midnight Sky |
| Fun | Fun Emoji |
| New additions | Lavender Glass, Carbon Minimal, Arctic Glow, Ember Luxe |

> Widget sizes depend on each provider's widget metadata and the launcher. Resize options may vary by device.

## 🖌️ Customise a widget

1. Open **K-Clock Widgets**.
2. Select a widget style from the gallery.
3. Choose a gradient preset or enter your own HEX colours.
4. Adjust text colour, accent colour, gradient angle and corner radius.
5. Select a background pattern and optional border.
6. Save your design, or use **Add to home**.
7. To change it later, open the same style and save the updated settings.

## 📱 Install on Android

### Option A — Install a built APK

1. Open the repository's **Actions** tab.
2. Select **Build K-Clock Widgets APK**.
3. Run the workflow (or push a commit to trigger it).
4. When the run completes, download the **K-Clock-Widgets-APK** artifact.
5. Extract the artifact ZIP and install `app-debug.apk`.
6. If Android asks, allow installation from the browser or file manager you used.

### Option B — Add a home-screen widget

1. Long-press an empty area of your Android home screen.
2. Open **Widgets**.
3. Find **K-Clock Widgets**.
4. Drag the desired clock style onto the home screen.
5. Resize it if your launcher supports resizing.

## 🛠️ Build from source

### Requirements

- Android Gradle Plugin **8.7.3**
- Gradle **8.9**
- JDK **17**
- Android SDK / Build Tools for `compileSdk 35`

### Local build

```bash
git clone https://github.com/KRISHNA853/ClockWidget.git
cd ClockWidget
gradle assembleDebug
```

The debug APK is generated at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

### GitHub Actions

The repository includes `.github/workflows/build.yml`. It runs on pushes and can also be started manually using `workflow_dispatch`.

## 🔐 Privacy

The project is designed as an offline-first clock-widget app. The supplied source manifest does not request internet permission. GitHub Actions needs network access during build to download Gradle and Android build dependencies.

## ☕ Support the creator

If you enjoy the project, you can support its development.

- Instagram: **[@krishnaxvis](https://www.instagram.com/krishnaxvis/)**
- UPI ID: `krishnakantvishwakrma396@okhdfcbank`

UPI payments are optional. Use the in-app **About & Support** section to open a UPI app or copy the UPI ID.

## ⚙️ Project details

| Item | Value |
|---|---|
| App name | K-Clock Widgets |
| Application ID | `com.krishna.clockhub` |
| Version | `2.1` |
| Minimum Android | Android 8.0 (API 26) |
| Compile SDK | 35 |
| Target SDK | 35 |
| Language | Java |
| Build | GitHub Actions |

## ⚠️ Build status & compatibility note

This source package has been prepared for a GitHub Actions / Gradle build. An actual APK build and on-device test have **not** been run as part of preparing this ZIP. The project currently targets API 35; Android 16 installation/runtime behaviour must be confirmed with a successful build and device test.

## 📄 License

See [`LICENSE`](LICENSE) for the project's license terms.

---

<div align="center">

**K-Clock Widgets** · Time, styled your way.

Made with ♥ by [Krishna](https://www.instagram.com/krishnaxvis/)

</div>
