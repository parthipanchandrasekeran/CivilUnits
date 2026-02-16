# Civil Units v1.1.0 — Release Notes

## What's New in v1.1.0

### Tablet Support
- App now works on tablets and large-screen devices
- Adaptive grid layout for category cards (auto-adjusts column count)
- Content constrained to 600dp max width on converter, quick civil, favorites, and history screens for comfortable reading on wide displays
- Rebar table uses full screen width on tablets for better data visibility
- Portrait orientation lock removed — landscape mode supported

---

## v1.0.0 — Initial Release

**Civil Units** is an offline unit conversion app built for Canadian civil engineers. Metric-first defaults with full imperial support.

### Unit Conversion (11 Categories)
- **Length** — mm, cm, m, km, in, ft, yd, mi
- **Area** — mm², cm², m², km², in², ft², yd², acre, hectare
- **Volume** — mL, L, m³, in³, ft³, yd³, US gal, Imp gal
- **Mass** — mg, g, kg, tonne, oz, lb, US ton
- **Density** — kg/m³, g/cm³, lb/ft³, lb/yd³
- **Force** — N, kN, kgf, lbf, kip
- **Pressure / Stress** — Pa, kPa, MPa, GPa, psi, psf, bar, atm
- **Moment / Torque** — N·m, kN·m, kgf·m, lbf·ft, kip·ft
- **Flow** — m³/s, L/s, L/min, gal/min, cfs
- **Temperature** — °C, °F, K
- **Slope / Grade** — ratio, percent, permille, degrees, 1:n

### Quick Civil
10 one-tap conversion cards for the most common Canadian civil pairs:
MPa/psi, kPa/psf, kN/lbf, kN/kip, kN·m/lbf·ft, kg/m³/lb/ft³, mm/in, m/ft, m²/ft², L/s/gpm

### Civil Tools
- Rebar reference table (#3–#11 US bar / 10M–36M metric)
- Diameter and cross-sectional area in both unit systems
- Searchable by bar number or metric size

### Features
- Favorites — star any conversion pair for quick access
- History — auto-saved with timestamps, swipe to delete
- Precision control — Auto, 2, 4, or 6 decimal places
- Copy to clipboard — one tap
- In-app updates — automatic flexible updates via Play Store
- No internet required — fully offline (updates use Play Store)
- Material 3 with dynamic color on Android 12+

### Technical
- Android 8.0+ (API 26)
- Target SDK 35
- R8 minification enabled
- 72 unit definitions, base-unit conversion engine
- 27 unit tests passing

## AAB Location
`app/build/outputs/bundle/release/app-release.aab`

## Mapping File (for Play Console)
`app/build/outputs/mapping/release/mapping.txt`
