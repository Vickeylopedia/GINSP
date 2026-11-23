# Design: Campus Companion

Read: Premium academic and campus utility for FUNAAB students, with a calm, exacting, approachable interface, Android-first.
Platforms: Android (minSdk 26, targetSdk 34+, edge-to-edge), Compact (<600dp) with Adaptive Rail (≥600dp), en-NG / en-US.
Dials: variance 6 · motion 4 · density 5

Signature: The Daily Course Timeline — a crisp, glanceable schedule card showing next lecture venue, transit time across campus, and active status, rendered in Companion Teal and Signal Mint.

## Color roles
- Primary: Companion Teal (#087F78 light, 5.0:1 on surface) / Signal Mint (#42C7B5 dark, 5.9:1 on surface)
- Background: Cloud (#F4F6F8 light) / Midnight Navy (#0D1824 dark)
- Surface: White (#FFFFFF light) / Deep Surface (#152535 dark)
- Elevated Surface: Soft Tint (#F9FBFC light) / Raised Navy (#1B3042 dark)
- Primary Text: Atlantic Ink (#102A43 light, 14.1:1 on bg) / Mint White (#F0F6F5 dark, 14.8:1 on bg)
- Secondary Text: Slate (#667788 light, 4.6:1 on bg) / Soft Grey (#A6B7C2 dark, 7.4:1 on bg)
- Border / Outline: Subtle Border (#E3E9EC light) / Deep Border (#2B4050 dark, ≥3:1 against surface)
- Brand Container: Soft Mint (#E7F5F2 light) / Dark Mint (#0F3A37 dark)

## Type roles (Inter)
- Display Large: 44sp / 52sp / Bold / -0.5sp tracking (hero numbers, streak peaks)
- Headline Medium: 24sp / 32sp / SemiBold / 0sp tracking (top screen titles)
- Title Large: 18sp / 24sp / SemiBold / 0sp tracking (card and dialog titles)
- Body Large: 16sp / 24sp / Normal / 0.15sp tracking (primary academic text)
- Body Medium: 14sp / 20sp / Normal / 0.2sp tracking (secondary descriptions)
- Label Large: 14sp / 20sp / SemiBold / 0.1sp tracking (interactive button & tab controls)
- Label Small: 11sp / 14sp / Medium / 0.3sp tracking (timestamps, status badges)

## Spacing, radius & elevation
- Spacing base: 4dp grid (4, 8, 12, 16, 20, 24, 32, 40, 48, 64)
- Touch targets: 48×48dp minimum with ≥8dp gutters
- Radius scale: Soft system (xs=6dp, sm=8dp, md=12dp default, lg=16dp modal, xl=24dp sheet)
- Concentric rule: outer_radius = inner_radius + padding
- Elevation: Tonal first (Material 3 surface tinting; level0=0dp, level1=1dp, level2=3dp, level3=6dp, level4=8dp, level5=12dp)

## Motion & Interaction (Emil Kowalski + Mobile-Design)
- Button press physics: scale(0.97f) on touch-down via 100ms ease-out
- Card press physics: scale(0.985f)
- Entry animations: never scale(0); start from scale(0.95f) with opacity fade
- Durations: micro=100ms, short=150ms, medium=250ms, sheet enter=300ms, sheet exit=200ms
- Asymmetry: exits are 30-50% faster than enters
- Frequency gate: 100+/day actions (tabs, shortcuts) have instant 0ms transitions
- Reduced Motion: collapses all spring/slide motion to instant or 100ms crossfade

## Voice
- Three adjectives: Exacting, Supportive, Direct
- Words used: "Enroll", "Schedule", "Venue", "Drop course", "Saved", "Offline copy"
- Words avoided: "Seamless", "Supercharge", "Effortless", "Oops!", "Unlock", "All-in-one"

## House rules
- No card inside another card. Lists and grouped dividers handle sub-items.
- Status is never carried by color alone; always pair with explicit text and icon.
- Both light and dark themes receive equal design attention and pass WCAG AA.

