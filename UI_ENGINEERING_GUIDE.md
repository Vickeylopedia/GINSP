# Campus Companion — UI Engineering & Skill Synergy Guide
*Your Campus. Connected.* • Engineering Standards & Cross-Agent Guidance

This document is the engineering manual for any developer or AI coding agent (including Google Antigravity, Google AI Studio, Claude Code, Cursor, or GitHub Copilot) working on the Campus Companion codebase.

---

## 1. Skill Governance & Authority Hierarchy

Five UI engineering skills are integrated into this project. To prevent conflicting design opinions, follow this strict governance hierarchy:

```
┌─────────────────────────────────────────────────────────────┐
│             APPROVED BRAND IDENTITY & DESIGN TOKENS         │
│           (CAMPUS_COMPANION_DESIGN_SYSTEM.md / DESIGN.md)   │
│                 [Highest Authority - Never Override]        │
└──────────────────────────────┬──────────────────────────────┘
                               │
       ┌───────────────────────┴───────────────────────┐
       ▼                                               ▼
┌──────────────────────────────┐       ┌──────────────────────────────┐
│        mobile-design         │       │       emil-design-eng        │
│   (Platform Architecture)    │       │   (Motion & Interactions)    │
│  - Android 15 edge-to-edge   │       │  - Button press scale 0.97f  │
│  - 48x48dp touch targets     │       │  - Asymmetric enter/exit     │
│  - M3 semantic color roles   │       │  - No scale(0) entry         │
│  - 4dp spacing grid          │       │  - Frequency gate (0ms tabs) │
│  - WCAG AA contrast floor    │       │  - Origin-aware transforms   │
└──────────────┬───────────────┘       └──────────────┬───────────────┘
               │                                      │
               └───────────────────────┬──────────────┘
                                       ▼
                       ┌──────────────────────────────┐
                       │ frontend-design & taste-skill│
                       │    (Aesthetic Guardrails)    │
                       │  - Anti-slop / no AI clichés │
                       │  - No card-in-card nesting   │
                       │  - No all-caps eyebrows      │
                       │  - Sentence-case headers     │
                       └──────────────┬───────────────┘
                                      ▼
                       ┌──────────────────────────────┐
                       │    web-design-guidelines     │
                       │      (Quality Audits)        │
                       │  - Focus preservation        │
                       │  - Error announcements       │
                       └──────────────────────────────┘
```

### Responsibility Breakdown
1. **`mobile-design` governs Platform Architecture & Structure:**
   - Path: `.agents/skills/mobile-design/SKILL.md`
   - Defines: Android navigation patterns (Bottom Bar <600dp, Navigation Rail ≥600dp), edge-to-edge system insets handling, Material 3 role mapping, and the non-negotiable Craft Floor.
2. **`emil-design-eng` governs Motion & Tactile Micro-Interactions:**
   - Path: `.agents/skills/emil-design-eng/SKILL.md`
   - Defines: Button press scale (`0.97f`), card press scale (`0.985f`), enter from `0.95f` (never 0f), exit duration faster than enter, and the animation frequency gate (0ms for repeat actions like navigation tabs).
3. **`frontend-design` & `design-taste-frontend` govern Visual Quality & Anti-Slop:**
   - Paths: `.agents/skills/frontend-design/SKILL.md` and `.agents/skills/design-taste-frontend/SKILL.md`
   - Defines: Ban on AI design clichés (no purple/blue gradients, no glassmorphism on cards, no card-in-card nesting, no all-caps tracking eyebrows). Ensures interfaces read as crafted commercial software.
4. **`web-design-guidelines` governs Interaction Quality Audits:**
   - Path: `.agents/skills/web-design-guidelines/SKILL.md`
   - Defines: Predictable focus states, input preservation during interruption, and explicit error recovery.

---

## 2. Brand Identity & Finalized Tokens

Any new screen or component MUST use the established tokens from `com.campuscompanion.ui.theme.*`. Never hardcode raw hex colors, arbitrary padding, or ad-hoc corner radii.

### 2.1 Approved Brand Colors
- **Atlantic Ink (`#102A43`):** High-contrast primary ink in light mode.
- **Companion Teal (`#087F78`):** Core brand anchor, primary action color in light theme.
- **Signal Mint (`#42C7B5`):** High-visibility hero accent, primary action color in dark theme.
- **Soft Mint (`#E7F5F2`):** Brand container and tinted callout background in light theme.
- **Cloud (`#F4F6F8`):** Neutral canvas background in light theme.
- **Slate (`#667788`):** Balanced secondary neutral for metadata and subtitles.

### 2.2 Theme Mappings
- **Light Theme (`CcLightColorScheme`):**
  - Background: `#F4F6F8` | Surface: `#FFFFFF` | Elevated Surface: `#F9FBFC`
  - Text Primary: `#102A43` (14.1:1 contrast) | Text Secondary: `#667788` (4.6:1 contrast)
  - Primary Action: `#087F78` (5.0:1 contrast) | Borders: `#E3E9EC`
- **Dark Theme (`CcDarkColorScheme`):**
  - Background: `#0D1824` | Surface: `#152535` | Elevated Surface: `#1B3042`
  - Text Primary: `#F0F6F5` (14.8:1 contrast) | Text Secondary: `#A6B7C2` (7.4:1 contrast)
  - Primary Action: `#42C7B5` (5.9:1 contrast) | Borders: `#2B4050` (≥3:1 contrast)

### 2.3 Typography Scale (Inter)
Font family: **Inter** locally packaged in `app/src/main/res/font/` (Regular, Medium, SemiBold, Bold).
- `displayLarge` (44sp Bold): Hero milestones and streak numbers.
- `headlineMedium` (24sp SemiBold): Top-level screen headers.
- `titleLarge` (18sp SemiBold): Card and dialog titles.
- `titleMedium` (16sp Medium): List item primary labels.
- `bodyLarge` (16sp Regular): Continuous reading, academic course text.
- `bodyMedium` (14sp Regular): Subtitles, descriptions, campus venue notes.
- `labelLarge` (14sp SemiBold): Interactive buttons and active tab labels.
- `labelSmall` (11sp Medium): Status badges and timestamp metadata.

### 2.4 Spacing & Concentric Shapes
- **Grid:** 4dp base scale (`CcSpacing.xs=4dp`, `s=8dp`, `sm=12dp`, `m=16dp`, `l=24dp`, `xl=32dp`, `huge=64dp`).
- **Touch Target Floor:** Minimum `48×48dp` for every tappable element with ≥8dp gutter between targets.
- **Concentric Radius Rule:** `outer_radius = inner_radius + padding`.
  - Base radii: `sm=8dp`, `md=12dp` (default cards/inputs), `lg=16dp` (dialogs), `xl=24dp` (bottom sheets).

---

## 3. Component Usage Rules for Agents

When implementing features, always reuse the foundation components in `com.campuscompanion.ui.components.*`:

1. **Buttons (`Buttons.kt`):**
   - Use `CcFilledButton` for the single primary constructive action.
   - Use `CcOutlinedButton` for secondary actions.
   - Use `CcTextButton` for dismissive/subtle actions.
   - Use `CcIconButton` for app bar actions (always provide `contentDescription`).
   - All buttons have built-in Emil press physics (`scale 0.97f`) and loading state support that preserves text layout.
2. **Inputs (`Inputs.kt`):**
   - Use `CcTextField` for form inputs (ensures 3:1 outline contrast and icon+text error messages).
   - Use `CcSearchField` for campus discovery and course searching.
3. **Cards & Lists (`Cards.kt`, `Lists.kt`):**
   - Use `CcClickableCard` (`scale 0.985f` on press) for distinct selectable items.
   - **Never nest a card inside another card.** Use `CcListItem` and dividers for content inside a section.
   - Use `CcSectionHeader` with sentence-case titles. Never use uppercase tracked eyebrows.
4. **Status & Badges (`Badges.kt`):**
   - Use `CcStatusBadge` for state indicators (Success, Warning, Error, Info, Brand).
   - **Never convey status by color alone.** The badge automatically pairs an icon, background tint, and explicit label.
5. **Feedback & Edge States (`Feedback.kt`):**
   - Every data-fetching screen must provide all 4 states: **Loading** (`CcLoadingSkeleton`), **Empty** (`CcEmptyState` with primary action), **Error** (`CcErrorState` with retry CTA), and **Populated**.

---

## 4. Cross-Agent Workflow: Antigravity ↔ Google AI Studio

When importing this project into **Google AI Studio** or other AI environments:
- **Do not convert the project to React, HTML, or Flutter.** The project is native Kotlin with Jetpack Compose.
- **Do not alter the Gradle build structure** just to fit an unsupported previewer.
- Review existing components before creating new ones.
- Read `CAMPUS_COMPANION_DESIGN_SYSTEM.md` and `DESIGN.md` for specific architectural rationale.
