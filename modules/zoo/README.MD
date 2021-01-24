# ZOO modules

## Compiling Simple Solid Module without dependencies
Compile/Recompile 'zoo.animal.feeding' from 'zoo/feeding' folder:
`javac --module-path mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java`
Alternate forms:
* `javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/*.java`
* `javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java`
* `javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/module-info.java`
* `javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/*.java`

`javac` options:
* `-d <dir>` - Directory for class files
* `-p <path>` or `--module-path <path>` - Module path

## Running Simple Solid Module without dependencies
Run program from 'zoo.animal.feeding'
`java --module-path feeding --module zoo.animal.feeding/zoo.animal.feeding.Task`
Alternate forms
* `java -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task`

`java` options:
* `-m <name>` or  `--module <name>` - Module name
* `-p <path>` or `--module-path <path>` - Module path

##Packaging Simple Solid Module without dependencies
* Create jar for 'zoo.animal.feeding'
`jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .`
* Run program from jar
`java -p mods -m zoo.animal.feeding/zoo.animal.feeding.Task`

`jar` options:
* `-c` or `--create` - creates a new JAR file
* `-v` or `--verbose`  - prints details when working with JAR files
* `-f <fileName>` or `--file <fileName>` - defines JAR filename
* `-C <directory>`  - defines directory containing files to be used to create the JAR