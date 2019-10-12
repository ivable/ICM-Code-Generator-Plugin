
# ICM Code Generator Plugin
Gradle plugin to generate code for intershop commerce management.
## Install plugin

Add the code generator plugin at the **top** of your `build.gradle`.
```
plugins {
  id "com.ivable.intershop.gradleplugin.icm-code-generator" version "1.1"
}
```

#### Example build.gradle:
    plugins {id "com.ivable.intershop.gradleplugin.icm-code-generator" version "1.1"}
    apply plugin: 'java-cartridge'
    apply plugin: 'static-cartridge'
    apply plugin: 'com.intershop.gradle.cartridge-resourcelist'
    apply plugin: 'com.intershop.gradle.isml'
    
    intershop 
    {
    ..
    }

## Using Business Object Extension
#### Prerequisite
- Cartridge must have the capi and internal package structure
- Cartridge must have the java plugin enabled

To see the help message, run
```
# navigate to the cartridge with the plugin enabled
cd /icm-proj/my-cartridge
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

## For developers
Clone this project and make your changes. Run this command  : `gradlew publishToMavenLocal` to publish the plugin to the local maven repository (*~/.m2/repository*) on your machine.
To use the modified plugin, add this code to the **top** of a build.gradle of a cartridge you want to test.

    buildscript {
        repositories { mavenLocal() }
        dependencies { classpath "com.ivable.intershop.gradleplugin:icm-code-generator:1.1" }
    }
    apply plugin: "com.ivable.intershop.gradleplugin.icm-code-generator"

This wil load the gradle plugin from the local maven repo and apply it to the project.
PR's are of course welcomed.

## Authors

* **Willem Evertse @ [Ivable](https://www.ivable.com)**


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
