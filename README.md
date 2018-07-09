# CodeGeneratorPlugin
Gradle plugin to generate code for intershop commerce management.

## Install plugin

Add the code generator plugin to your `build.gradle`
```
apply plugin: 'icm-code-generator'
```

### Business Object Extension
To see the help message, run the following
```
../gradlew createboext
```
Example:
Generate a ProductBO extension
```
../gradlew createboext -Pextname=SomeExt -Ptype=1
```

## Authors

* **Willem Evertse**


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for detailss