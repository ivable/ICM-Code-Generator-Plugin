# CodeGeneratorPlugin
Gradle plugin to generate code for intershop commerce management.

## Install plugin

Add the code generator plugin at the top of your `build.gradle`.
```
plugins {
  id "com.we.intershop.gradleplugin.icm-code-generator" version "1.0"
}
```

### Business Object Extension
To see the help message, run
```
../gradlew createboext
```
Example:
Generate a ProductBO extension
```
# navigate to the cartridge with plugin
../gradlew createboext -Pextname=SomeExt -Ptype=1
```
Supported Business Object extensions
 - 1 - ProductBO
 - 2 - CatalogCategoryBO

## Authors

* **Willem Evertse @ [INCENTRO](https://www.incentro.com)**


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for detailss