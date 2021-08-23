## Get list of projects
```sh
firebase projects:list
```

## Get app id. FIREBASE_APP_ID
```sh
firebase apps:list --project demoapp-6e3f3
┌──────────────────┬───────────────────────────────────────────────┬──────────┐
│ App Display Name │ App ID                                        │ Platform │
├──────────────────┼───────────────────────────────────────────────┼──────────┤
│ DemoApp  Android │ 1:357372000022:android:d91adffcda0ef6f8       │ ANDROID  │
└──────────────────┴───────────────────────────────────────────────┴──────────┘
```

## Generate FIREBASE_TOKEN
```
firebase login:ci
```
