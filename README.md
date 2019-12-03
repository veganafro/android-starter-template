## Summary

Get started developing Android apps using this single activity, modularized template. The project is
organized using the below structure:

```
.
+-- app
    +-- injector
        +-- dagger-compiler
        +-- view
            +-- material
            +-- constraintlayout
            +-- swiperefreshlayout
            +-- presenter
                +-- dagger
                +-- contract
                |   +-- kotlin-coroutines-core
                |   +-- kotlin-coroutines-android
                +-- networking
                    +-- retrofit
                    +-- model
                        +-- moshi
                        +-- converter-moshi
                        +-- moshi-kotlin-codegen
```

### Notes

The `app` module contains the single `MainActivity` that can be used to coordinate navigation between
views, and the `view` module contains each `Fragment` that is used to specify a view. The rest of the
modules should be self explanatory with an understanding MVP architecture.

To access an HTTP endpoint, simply

1. Create a file named `apikey.properties` in the root directory
2. Add `apikey.properties` to the top level `.gitignore`
3. Edit `apikey.properties` and add a key value pair with the key `API_KEY` and a string value that is the API key
4. Now use `BuildConfig.API_KEY` anywhere within the presenter module that is accessing the networking module
5. Edit the `apiHost` variable in the `NetworkingModule.kt` to point to the API's base URL
6. Specify the endpoint's path in `Service.kt`

The rest of the project and modules should be self explanatory.
