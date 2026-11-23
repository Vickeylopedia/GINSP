# Project Instructions for AI Agents & Developers

Welcome to the **Campus Companion** project repository.

## CRITICAL: Mandatory Reading Before Modifying Any UI

Before writing, editing, or proposing any UI code, layout, component, or screen:
1. **Read `UI_ENGINEERING_GUIDE.md` in full.**
2. **Read `CAMPUS_COMPANION_DESIGN_SYSTEM.md` and `DESIGN.md`.**
3. **Inspect the existing design tokens in `app/src/main/java/com/campuscompanion/ui/theme/` and reusable components in `app/src/main/java/com/campuscompanion/ui/components/`.**

> **Note on Agent Skills Discovery:**
> This repository contains five reference skills stored in `.agents/skills/`:
> - `mobile-design` (`.agents/skills/mobile-design/SKILL.md`)
> - `emil-design-eng` (`.agents/skills/emil-design-eng/SKILL.md`)
> - `frontend-design` (`.agents/skills/frontend-design/SKILL.md`)
> - `design-taste-frontend` (`.agents/skills/design-taste-frontend/SKILL.md`)
> - `web-design-guidelines` (`.agents/skills/web-design-guidelines/SKILL.md`)
>
> In environments that do NOT automatically discover or execute agent skills (such as Google AI Studio, standard IDEs, or automated web previews), **skills are NOT automatically injected**. Agents and developers MUST explicitly open and read the skill files above from `.agents/skills/` to apply their engineering guidance.

## Core Non-Negotiable Rules

1. **Native Android Stack Only:**
   - This is a pure Kotlin + Jetpack Compose Android application.
   - **DO NOT** replace Jetpack Compose with React, HTML/CSS, or Flutter.
   - **DO NOT** convert this project into a web application to force web-browser previews.

2. **Approved Brand Identity:**
   - Primary: Companion Teal (`#087F78` light) / Signal Mint (`#42C7B5` dark).
   - Surfaces: White (`#FFFFFF` light) / Oceanic Navy (`#152535` dark).
   - Backgrounds: Cloud (`#F4F6F8` light) / Midnight Navy (`#0D1824` dark).
   - Primary Text: Atlantic Ink (`#102A43` light) / Mint White (`#F0F6F5` dark).
   - Secondary Text: Slate (`#667788` light) / Soft Grey (`#A6B7C2` dark).
   - Font: Inter (locally bundled in `app/src/main/res/font/`).
   - Never invent arbitrary colors or introduce unapproved gradients.

3. **Component Interaction & Craft Floor:**
   - 48×48dp minimum touch target on all interactive elements.
   - Emil Kowalski button press physics: `scale(0.97f)` on press.
   - No card-in-card nesting. Use `CcListItem` and dividers for internal structure.
   - No all-caps tracked eyebrows.
   - Never convey status by color alone; pair with icon and text (`CcStatusBadge`).
   - Every data screen must handle Loading, Empty, Error, and Populated states.

4. **Edge-to-Edge & Insets:**
   - The application targets Android 15 (API 35) with `enableEdgeToEdge()`.
   - Always respect `WindowInsets.statusBars` and `WindowInsets.navigationBars`.
