# Java Platform Module System (JPMS)

* to see which modules are available within Java `java --list-modules`
<BR/>The modules are split into 4 categories. 
  * The ones starting with java (standard Java modules), 
  * the ones starting with javafx (JavaFX modules), 
  * the ones starting with jdk (JDK specific modules) 
  * the ones starting with oracle (Oracle specific modules). 
<BR/>Each module ends with @X indicating that the module belongs to Java X version(f.e. 11.0.2).

* to see the description of the module `java --describe-module java.sql`
<BR/>Module properties are located in a file *module-info.java*.
  * The *exports* keyword indicates that these packages are available to other modules. This means that public classes are default only public within the module unless it is specified within the module info declaration.
  * The *requires* keyword indicates that this module depends on another module.
  * The *uses* keyword indicates that this module uses a service.
  
* The following command will create a JRE with only the *java.base* module.
```  
  jlink --module-path ../jmods --add-modules java.base --output d:\java\jre  
```


# Projects
* *single-module-app* - single module 'Hello world' application
  * to compile the application:
    ```
    javac -d mods/by.dma1979.jpmshello src/by.dma1979.jpmshello/module-info.java src/by.dma1979.jpmshello/by/dma1979/jpmshello/HelloSingleModule.java
    ```
    The -d option specifies the destination directory for the compiled classes. 
    In our case, we compile it to a subdirectory by.dma1979.jpmshello which resembles our module. 
    At the end, we specify the class(es) to be compiled.
    
  * to execute the compiled classes, we need to set the module-path. 
    ```
    java --module-path mods --module by.dma1979.jpmshello/by.dma1979.jpmshello.HelloSingleModule
    ```
    This option sets the directories where the modules can be found. 
    The module option sets the main class which must be invoked. 
    It is necessary to set the module and the package and the class where the main class is located.

  * to package the application in a jar file
    ```
    jar --create --file target/jpms-hello-single-module.jar --main-class by.dma1979.jpmshello.HelloSingleModule -C mods/by.dma1979.jpmshello .
    ```
    * The *file* option specifies the location and name of the jar file. 
    * The *main-class* option specifies the entry point of the application(where the main class resides). 
    * The *C* option specifies the location of the classes to include in the jar file.
    
  * to execute the application from the jar file.
    ```
    java --module-path target/jpms-hello-single-module.jar --module by.dma1979.jpmshello/by.dma1979.jpmshello.HelloSingleModule
    ```
  * to check the module description(the same as for Java standard modules):
    ```
    java --module-path target/jpms-hello-single-module.jar --describe-module by.dma1979.jpmshello  
    ```      