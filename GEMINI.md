# Google AI Studio & Gemini Instructions — Campus Companion

## Project Context
Campus Companion is a native Android application built with Kotlin and Jetpack Compose for university students, targeting FUNAAB.

## MANDATORY UI GUIDELINES
Before implementing or modifying any user interface:
1. Open and read `UI_ENGINEERING_GUIDE.md`.
2. Open and read `CAMPUS_COMPANION_DESIGN_SYSTEM.md` and `DESIGN.md`.
3. Reference the installed skills in `.agents/skills/`:
   - Android navigation & edge-to-edge: `.agents/skills/mobile-design/SKILL.md`
   - Tactile motion & press physics: `.agents/skills/emil-design-eng/SKILL.md`
   - Anti-template aesthetic standards: `.agents/skills/frontend-design/SKILL.md`

## Architecture Requirements
- **Do not convert the project to React, HTML, or web frameworks.** This is an Android native Compose app.
- **Do not modify the Gradle wrapper or SDK setup** to force browser-based live rendering.
- All tokens are in `com.campuscompanion.ui.theme.*`.
- All base components are in `com.campuscompanion.ui.components.*`.
- Run `./gradlew test` and `./gradlew compileDebugKotlin` to verify changes.
