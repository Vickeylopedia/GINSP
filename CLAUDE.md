# Claude Code Instructions — Campus Companion

Read `UI_ENGINEERING_GUIDE.md`, `CAMPUS_COMPANION_DESIGN_SYSTEM.md`, and `DESIGN.md` before making any UI changes.

Skills are stored in `.agents/skills/`. If automatic skill loading is not active, read the relevant skill instruction files directly before writing UI:
- Android platform patterns: `.agents/skills/mobile-design/SKILL.md`
- Motion & tactile physics: `.agents/skills/emil-design-eng/SKILL.md`
- Anti-slop / quality: `.agents/skills/frontend-design/SKILL.md`

Rules:
- Native Kotlin + Jetpack Compose only. Never convert to web/React.
- Reuse components in `com.campuscompanion.ui.components.*`.
- Respect 48dp minimum touch targets and approved brand colors.
- Run `./gradlew test` to verify changes before completing tasks.
