//usr/bin/env jshell --show-version --execution local "$0" "$@"; exit $?

/*
The `–show-version` option asks jshell to print its version when it starts. 
The `–execution local` option ensures that the jshell will not spin up a separate JVM for your script and so it runs faster.
*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : Dzmitry Marudau
 * @created at : 16:18
 * @since : 2019.09
 **/

var a = 7;
var b = 9;
System.out.printf("% d * %d = %d%n", a, b, (a*b));

var template = """
package by.shared;

import by.model._CLASS_NAME_;

/*
Created by DMA
*/
class _CLASS_NAME_DTO {}
""";

System.out.printf("Template:%n%s%n", template);

Files.newDirectoryStream(Paths.get(".")).forEach(System.out::println);

        //Arrays.stream(new File("src/by/model").list())
        Files.list(Paths.get("src/by/model")).filter(path -> path.toString().endsWith(".java") ).forEach(path -> {
                    var name = path.getFileName().toString();
                    System.out.printf("File name=%s%n", name);
                    var builderContents = template.replaceAll("_CLASS_NAME_", name);
                    File builderFile = new File("src/by/shared", name.replace(".java", "DTO.java"));
                    try (FileOutputStream fileOutputStream = new FileOutputStream(builderFile)) {
                        fileOutputStream.write(builderContents.getBytes( StandardCharsets.UTF_8));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


/exit
