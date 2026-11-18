<div align="center">

# 🕒 Krishna Clock Hub

### 25 customizable digital clock widgets for Android
**12-hour + 24-hour • Gradients • Hacker / Neon / Minimal themes • Fully editable**

![Platform](https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Widgets](https://img.shields.io/badge/Widgets-25-blueviolet?style=for-the-badge)
![Built with](https://img.shields.io/badge/Built%20with-GitHub%20Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![License](https://img.shields.io/badge/License-All%20Rights%20Reserved-red?style=for-the-badge)

**Made with ❤️ by [Krishna](https://instagram.com/krishnaxvis)**

</div>

---

## ✨ Features

- 🕐 Every widget shows **12-hour and 24-hour** time, plus the date
- 🎨 **25 unique styles**: hacker terminal, neon, cyberpunk, glass, gradients, emoji and more
- 🖌️ **Fully editable**: gradient colors, text color, accent color, angle, corner radius, patterns, neon border
- 🎲 One-tap **quick gradients** and a **random gradient** button
- ➕ **Add to home** button right inside the app
- 👆 Tap a widget to open your Clock app
- 🪶 Tiny, no ads, no internet permission, no tracking

## 🧩 Widget Styles

| Category | Styles |
|---|---|
| 💻 **Hacker / Tech** | Hacker Terminal, Hacker Split, Matrix Strip, Red Alert, Dracula Shell, Retro Pixel |
| ⚡ **Neon / Gamer** | Neon Night, Cyberpunk Yellow, Gamer RGB, Toxic Lime |
| 🌈 **Gradient / Aesthetic** | Aura, Sunset, Vaporwave, Aurora, Galaxy, Lava, Mint Pop, Ocean Stack, Candy Pop |
| 🖤 **Minimal / Premium** | Glass Dark, Mono Dark, Gold Luxe, Ice, Midnight Sky |
| 😝 **Fun** | Fun Emoji |

Sizes: **4×2**, **4×1 (strip)** and **2×2**.

## 🖌️ Customize Your Widget

1. Open the **Krishna Clock Hub** app
2. Tap any style
3. Change colors, angle, corner radius, pattern or border
4. Tap **Save**. Your home screen widgets update instantly
5. Tap **Add to home** to place it on your screen

Patterns available: `none` · `grid` · `dots` · `scan` · `stars`

## 📲 Install

1. Open the **Actions** tab of this repository
2. Open the latest green ✅ run
3. Download **ClockWidget-APK** from *Artifacts* and extract it
4. Install `app-debug.apk` (allow *Install unknown apps* if asked)
5. Long-press your home screen → **Widgets** → **Krishna Clock Hub**

## 🏗️ Build Without a PC (GitHub Actions)

This project is built entirely on GitHub, so no computer is needed.

1. Upload `ClockWidget.zip` to the repository root
2. Keep the workflow file at `.github/workflows/build.yml`
3. Commit. The **Build APK** workflow starts automatically
4. When it turns green, download the APK from *Artifacts*

## 📁 Project Structure

```
ClockWidget/
├── .github/workflows/build.yml     # GitHub Actions build
├── build.gradle
├── settings.gradle
└── app/src/main/
    ├── AndroidManifest.xml
    ├── java/com/krishna/clockhub/
    │   ├── MainActivity.java       # customizer app
    │   ├── Renderer.java           # gradient + pattern engine
    │   ├── Styles.java             # all 25 style definitions
    │   ├── Theme.java              # saved user settings
    │   └── *Provider.java          # one widget provider per style
    └── res/layout/w_*.xml          # one layout per style
```

## ☕ Support My Work

If you like this project, you can buy me a coffee:

**UPI ID:** `krishnakantvishwakrma396@okhdfcbank`

Works with Google Pay, PhonePe, Paytm and any UPI app. 🙏

## 🔒 License & Permission

**Copyright © 2026 Krishna (KRISHNA853). All Rights Reserved.**

This code is **not open source**. You may **not** use, copy, modify, redistribute, publish, sell or build upon any part of this project without my **prior written permission**.

Viewing the code on GitHub is allowed. For any other use, ask first. See the [LICENSE](LICENSE) file.

## 📬 Contact / Ask for Permission

📸 Instagram: [@krishnaxvis](https://instagram.com/krishnaxvis)

Want to use this code, collaborate or suggest a change? Send me a message on Instagram or open an **Issue** or **Pull Request** here. Nothing is changed in this project without my approval.

---

<div align="center">

⭐ **Star this repo if you like it!** ⭐

</div>
