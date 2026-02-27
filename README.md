# Photo Collage Editor — Android

A feature-rich Android photo collage and photo-editing application built with Java. Users can combine multiple photos into beautiful collages using free-form layouts, pre-built frame grids, or designer templates — with a full suite of editing tools including filters, text overlays, borders, and backgrounds.

---

## Table of Contents

- [Screenshots](#screenshots)
- [Features](#features)
- [Architecture & Modules](#architecture--modules)
- [Tech Stack & Dependencies](#tech-stack--dependencies)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Monetization](#monetization)
- [Build Variants](#build-variants)
- [Contributing](#contributing)
- [License](#license)

---

## Screenshots

> Add screenshots here by placing images in `assets/screenshots/` and referencing them below.

| Home Screen | Collage Editor | Template Picker | Photo Editor |
|-------------|---------------|-----------------|--------------|
| _coming soon_ | _coming soon_ | _coming soon_ | _coming soon_ |

---

## Features

### Collage Creation
- **Free-Form Mode** — Freely arrange, scale, and rotate photos on a canvas using multi-touch gestures
- **Frame Mode** — Select from a library of grid-based frame layouts (1–8+ photos)
- **Template Mode** — Browse and apply professionally designed collage templates

### Photo Editing
- Single photo editor with full-screen canvas
- Real-time OpenGL-powered image filters and effects
- Brightness, contrast, saturation, sharpness, and temperature adjustments
- Selective blur (touch-based and circular region)
- Haze and atmosphere effects

### Text & Stickers
- Add custom text with multiple font choices
- Control text color, size, rotation, and position
- Sticker library with downloadable sticker packs

### Background & Borders
- Solid color background with color picker
- Photo/image backgrounds from gallery
- Adjustable border width and color
- Drop shadow customization

### Gallery Integration
- Browse all device albums
- Multi-photo selection
- Camera capture within the app

### Sharing & Export
- Save finished collage to device gallery
- Share directly to social media (Facebook, Instagram, WhatsApp, etc.)

### In-App Store
- Downloadable template packs
- Downloadable frame packs
- Downloadable sticker packs
- Package management (view, delete downloaded items)

### Notifications & Engagement
- Daily reminder notifications to drive re-engagement
- Scheduled notifications via AlarmManager
- Boot-aware: notifications restored after device restart

---

## Architecture & Modules

The project is split into four Gradle modules:

```
photo-collage-editor-android/
│
├── app/                          # Main application module
├── photoEditor/                  # Photo-editing library module
├── dauroiimageprocessing/        # OpenGL image processing library
└── NativeTemplatesAndroid-1.0.0/ # Google AdMob native ad templates
```

### Module Responsibilities

| Module | Role |
|--------|------|
| **`:app`** | All UI, collage logic, frame system, adapters, activities, fragments, monetization, and notifications |
| **`:photoEditor`** | Standalone photo-editor UI (activities), SQLite database layer (templates, filters, store items), in-app store vending, color picker |
| **`:dauroiimageprocessing`** | Real-time OpenGL ES filter pipeline: blur, haze, pixelation, crop, texture sampling — supports arm64-v8a, armeabi-v7a, x86, x86_64 |
| **`:NativeTemplatesAndroid-1.0.0`** | Pre-built native ad template views for AdMob |

### App Module — Layered Structure

```
collagestudio.photocollage.collagemaker/
│
├── activity/          # All Activity classes (Splash, Main, Collage, Editor…)
├── fragment/          # All Fragment classes (Collage, Gallery, Store, Color…)
├── frame/             # Frame builder, frame views, frame touch handling
├── multitouch/        # Multi-touch controller, ImageEntity, TextEntity
├── adapter/           # RecyclerView / ListView adapters
├── model/             # Plain data models (TemplateItem, ImageItem…)
├── listener/          # Callback interfaces
├── utils/             # ImageUtils, AdsHelper, DialogUtils, PermissionUtils…
├── notifications/     # AlarmReceiver, DeviceBootReceiver, NotificationHelper
├── cross_promotion/   # Cross-app promotion dialog and data loader
└── quickaction/       # Context quick-action popup menu
```

---

## Tech Stack & Dependencies

### Core Android
| Library | Version | Purpose |
|---------|---------|---------|
| AndroidX AppCompat | 1.0.2 | Activity/Fragment backward compatibility |
| AndroidX ConstraintLayout | 1.1.3 | Flexible UI layouts |
| Material Design Components | 1.0.0 | Buttons, dialogs, cards |
| AndroidX Lifecycle | 2.2.0 | ViewModel + LiveData |
| AndroidX Legacy Support | 1.0.0 | v4 support APIs |
| MultiDex | — | Exceeds 64K method limit |

### Image Loading
| Library | Version | Purpose |
|---------|---------|---------|
| Glide | 3.7.0 | Efficient image loading and caching |
| Picasso | 2.5.2 / 2.3.2 | Alternate image loading (legacy paths) |

### Image Processing
| Library | Version | Purpose |
|---------|---------|---------|
| `:dauroiimageprocessing` | local | OpenGL ES real-time filter pipeline |
| `:photoEditor` | local | Editor UI, SQLite storage, store |

### Monetization & Ads
| Library | Version | Purpose |
|---------|---------|---------|
| Google Play Services Ads | 19.7.0 | AdMob banner, native, interstitial ads |
| Facebook Audience Network | 6.3.0.1 | Facebook ads mediation |
| HouseAds2 | 1.0.0.15 | Self-serve house ads |
| `:NativeTemplatesAndroid-1.0.0` | local | AdMob native ad template views |

### Social & Auth
| Library | Version | Purpose |
|---------|---------|---------|
| Facebook Login SDK | [5,6) | User authentication via Facebook |

### Networking & Data
| Library | Version | Purpose |
|---------|---------|---------|
| Volley | 1.1.1 | HTTP networking (template downloads) |
| Gson | 2.8.5 | JSON serialization/deserialization |

### UI Utilities
| Library | Version | Purpose |
|---------|---------|---------|
| SuperSlim | 0.4.13 | Advanced RecyclerView layout manager |
| SwipeLayout | 1.2.0 | Swipe-to-reveal list items |
| DaimaJia Slider | 1.1.5 | Image slider/banner |
| NineOldAndroids | 2.4.0 | Animation backport |
| SDP (Scalable DP) | 1.0.6 | Screen-independent dimension units |

### Permissions
| Library | Version | Purpose |
|---------|---------|---------|
| Permissions | 3.8 | Runtime permission request management |

---

## Requirements

| Requirement | Version |
|-------------|---------|
| **Android Studio** | Arctic Fox (2020.3.1) or later |
| **JDK** | 1.8 (Java 8) |
| **Android SDK** | compileSdk 30, targetSdk 30 |
| **Min Android** | Android 4.1 (API 16) |
| **Gradle** | 6.5 |
| **Android Gradle Plugin** | 4.1.3 |
| **NDK** | Required for `:dauroiimageprocessing` native filters |

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/photo-collage-editor-android.git
cd photo-collage-editor-android
```

### 2. Open in Android Studio

Open Android Studio → **File → Open** → select the `photo-collage-editor-android/` folder.
Allow Gradle to sync all modules.

### 3. Configure `local.properties`

`local.properties` is excluded from version control (see `.gitignore`). Android Studio creates this automatically, but you can also create it manually:

```properties
sdk.dir=/path/to/your/Android/sdk
# macOS example: /Users/yourname/Library/Android/sdk
# Windows example: C\:\\Users\\yourname\\AppData\\Local\\Android\\Sdk
```

### 4. Build and Run

```bash
# Debug build via Gradle wrapper
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

Or press **Run ▶** in Android Studio.

---

## Project Structure

```
photo-collage-editor-android/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/collagestudio/photocollage/collagemaker/
│   │   │   │   ├── activity/          # Activities (Splash, Main, Collage…)
│   │   │   │   ├── fragment/          # Fragments (Editor, Gallery, Store…)
│   │   │   │   ├── frame/             # Frame layout engine
│   │   │   │   ├── multitouch/        # Multi-touch gesture handling
│   │   │   │   ├── adapter/           # List / Grid adapters
│   │   │   │   ├── model/             # Data models
│   │   │   │   ├── listener/          # Callback interfaces
│   │   │   │   ├── utils/             # Utility helpers
│   │   │   │   ├── notifications/     # Notification scheduling
│   │   │   │   ├── cross_promotion/   # Cross-app promotion
│   │   │   │   └── quickaction/       # Quick-action menu
│   │   │   ├── res/
│   │   │   │   ├── layout/            # 77 XML layouts
│   │   │   │   ├── drawable/          # Vector and raster assets
│   │   │   │   ├── anim/              # 24 animation definitions
│   │   │   │   ├── menu/              # Action menus
│   │   │   │   ├── font/              # Bundled font files
│   │   │   │   ├── values/            # Strings, colors, styles, dimens
│   │   │   │   └── mipmap-*/          # Launcher icons (all densities)
│   │   │   └── AndroidManifest.xml
│   │   └── test/ / androidTest/
│   └── build.gradle
│
├── photoEditor/
│   └── src/main/java/dauroi/photoeditor/
│       ├── ui/activity/               # Editor activities
│       ├── database/                  # SQLite schema and DAO
│       ├── colorpicker/               # Color picker dialog
│       ├── actions/                   # Blur, crop, rotate, flip actions
│       └── vending/                   # Item download / purchase logic
│
├── dauroiimageprocessing/
│   └── src/main/java/dauroi/com/imageprocessing/
│       ├── filter/                    # OpenGL filter classes
│       ├── gl/                        # Renderer, texture utilities
│       └── utils/                     # Image and rotation utilities
│
├── NativeTemplatesAndroid-1.0.0/
│   └── src/main/java/com/google/android/ads/nativetemplates/
│       ├── NativeTemplateStyle.java   # Style configuration
│       └── TemplateView.java          # Native ad template view
│
├── gradle/wrapper/
│   ├── gradle-wrapper.jar
│   └── gradle-wrapper.properties
│
├── build.gradle          # Root build script (classpath, repositories)
├── settings.gradle       # Module declarations
├── gradle.properties     # AndroidX, Jetifier, JVM heap config
├── local.properties      # ⚠ Local SDK path — NOT committed to VCS
└── .gitignore
```

---

## Configuration

### App Package Name
`collagestudio.photocollage.collagemaker`

### Version Information
| Property | Value |
|----------|-------|
| versionName | 1.1.3 |
| versionCode | 13 |
| compileSdkVersion | 30 |
| targetSdkVersion | 30 |
| minSdkVersion | 16 |

### AdMob Configuration

Ad unit IDs are stored in `app/src/main/res/values/strings.xml`. Replace the placeholder test IDs with your real AdMob IDs before releasing:

```xml
<!-- Replace with your real AdMob App ID in AndroidManifest.xml -->
<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="ca-app-pub-XXXXXXXXXXXXXXXX~XXXXXXXXXX" />
```

```xml
<!-- In strings.xml — replace test IDs with your real ad unit IDs -->
<string name="banner_ad_unit_id">ca-app-pub-XXXXXXXXXXXXXXXX/XXXXXXXXXX</string>
<string name="native_ad_unit_id">ca-app-pub-XXXXXXXXXXXXXXXX/XXXXXXXXXX</string>
<string name="interstitial_ad_unit_id">ca-app-pub-XXXXXXXXXXXXXXXX/XXXXXXXXXX</string>
```

> ⚠ **Never commit real AdMob IDs to a public repository.** Move them to `local.properties` or use a build secret manager.

### Facebook SDK Configuration

Update your Facebook App ID in `strings.xml`:

```xml
<string name="facebook_app_id">YOUR_FACEBOOK_APP_ID</string>
<string name="fb_login_protocol_scheme">fbYOUR_FACEBOOK_APP_ID</string>
```

---

## Monetization

This app implements a multi-channel monetization strategy:

| Channel | Implementation |
|---------|---------------|
| **AdMob Banner** | Persistent bottom banner on main screens |
| **AdMob Interstitial** | Shown between key user actions |
| **AdMob Native** | Blended native ads in lists |
| **Facebook Audience Network** | Ad mediation via Facebook |
| **House Ads** | Self-serve promotion via HouseAds2 |
| **In-App Store** | Downloadable content packs (templates, frames, stickers) |
| **Cross-Promotion** | Exit-intent dialog promoting other apps |
| **Daily Notifications** | Re-engagement notifications at 10:14 AM and 7:30 PM |

---

## Build Variants

The project currently defines two standard build types:

| Variant | Purpose |
|---------|---------|
| `debug` | Development build with debug logging, test ad units |
| `release` | Production build — requires signing keystore |

### Creating a Release Build

1. Create a `keystore.properties` file (excluded from git):

```properties
storeFile=path/to/your/release.keystore
storePassword=your_store_password
keyAlias=your_key_alias
keyPassword=your_key_password
```

2. Reference it in `app/build.gradle` under `signingConfigs`.

3. Build:

```bash
./gradlew assembleRelease
# or for App Bundle (recommended for Play Store)
./gradlew bundleRelease
```

---

## Key Activities Reference

| Activity | Class | Description |
|----------|-------|-------------|
| Splash | `SplashActivity` | App entry point, routes to Intro or Main |
| Home | `MainActivity` | Displays three creation mode buttons |
| Collage Editor | `PhotoCollageActivity` | Full collage composition canvas |
| Single Editor | `SingleEditor` | Single-photo editing workflow |
| Template Browser | `TemplateActivity` | Grid view of all templates |
| Template Detail | `TemplateDetailActivity` | Preview and apply a template |
| Frame Detail | `FrameDetailActivity` | Frame-based collage creation |
| Photo Selection | `SelectPhotoActivity` | Gallery album browser + multi-select |
| Add Text | `AddTextItemActivity` | Text overlay editor |
| Store | `DownloadedPackageActivity` | Browse and manage downloadable packs |
| Login | `Login` | Facebook authentication screen |

---

## Permissions

| Permission | Reason |
|------------|--------|
| `INTERNET` | Download templates, load ads, network requests |
| `CAMERA` | Capture photos directly in-app |
| `READ_EXTERNAL_STORAGE` | Access device photo gallery |
| `WRITE_EXTERNAL_STORAGE` | Save finished collages to gallery |
| `WAKE_LOCK` | Prevent sleep during image export |
| `VIBRATE` | Haptic feedback on interactions |
| `ACCESS_MEDIA_LOCATION` | Read EXIF GPS metadata from images |
| `RECEIVE_BOOT_COMPLETED` | Restore notification alarms after reboot |

---

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m "Add: brief description of change"`
4. Push to your fork: `git push origin feature/your-feature-name`
5. Open a Pull Request against `main`

### Code Style
- Follow standard Java naming conventions
- Keep Activities lean — move logic to Utils or helper classes
- All image operations must run off the main thread (AsyncTask / Executor)
- Test on both low-end (API 16) and modern (API 30+) devices

---

## License

```
Copyright (c) 2024 Collage Studio

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

> Built with Java · Android SDK 30 · AdMob · OpenGL ES
