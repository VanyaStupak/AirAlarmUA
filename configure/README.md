# dev.devlight.skeleton

## Overview

## Implementation details

### Credentials 

#### AppDistribution

To use AppDistribution, follow these steps:

1. Create a file `appdistribution-android.json` in `/configure/credential` (you can change the name and location in `AppDistributionConventionPlugin`).
2. Copy the content of the `appdistribution-android-local.json` (or `appdistribution-android-cicd.json`) file into it.
3. Credential files are located in Confluence.

__N.B.__

> _Why are there `appdistribution-android-local.json` and `appdistribution-android-cicd.json`?_

To reduce key vulnerability and to parallelize access.

> _Why is the name `appdistribution-android.json`?_ 

So that the plugin can work both locally and on CI/CD.

> _What happens on CI/CD?_

Bitbucket Pipeline creates the `appdistribution-android.json` file with the content of `appdistribution-android-cicd.json`.

## Dependencies

## References

## TODO

## Generation signing/dev.devlight.skeleton-debug.p12
```
keytool -genkeypair -alias <secrets.properties/debugKey> -keyalg RSA -keystore dev.devlight.skeleton-debug.p12 -storetype PKCS12 -storepass <secrets.properties/debugPassword> -keypass <secrets.properties/debugPassword>
```