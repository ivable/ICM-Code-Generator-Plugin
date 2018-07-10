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
#### Prerequisite
- Cartridge must have the capi and internal package structure
- Cartridge must have the java plugin enabled

To see the help message, run
```
# navigate to the cartridge with the plugin enabled
../gradlew createboext
```
Example:
Generate a ProductBO extension
```
# navigate to the cartridge with the plugin enabled
../gradlew createboext -Pextname=SomeExt -Ptype=1
```
Supported Business Object extensions
 - 1 - ProductBO
 - 2 - CatalogCategoryBO
 - 3 - BasketBO
 - 4 - OrderBO

## Authors

* **Willem Evertse @ [Incentro](https://www.incentro.com)**


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details