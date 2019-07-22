# CODE REVIEW CHECKLIST
*The main purpose of code reviews is to improve your codebase, improve your team's development skills, share knowledge, communicate, and —finally— write good code.*

* Be careful about code format. The team needs to agree on a format and everyone in the team should use that format.

* Ensure that proper naming conventions have been followed. <br/>Follow Java naming convention, f.e.. class names should start with capital letters, method names start with lowercase letters, etc.

* Remove comments if they give no information! Comments should explain why the code is written. Comments may also be used if there are complicated business rules or workaround solutions. Other comments should be removed.

* Attention should be paid to the architectural approach of the written code. The code should split into multiple layers. The MVC design pattern must be maintained and any parts that do not fit the layered architecture should be corrected.

* if there is a hard-coded variable in the code, it must be assigned to a constant or a configuration variable.

* Check that similar variables can be grouped under an enum.

* Verify that existing libraries/classes are used instead of writing code from scratch.
Anything should be removed if another library is used that does the same work as the existing libraries in the application.

*  The purpose of the written code should be understood from the related issue or the code itself. If no code belonging to the requested business rule has been written, it must be corrected.

* The code blocks should be concise. Make sure that the code is separated into blocks and each block has its own and only one responsibility. (Single Responsibility Principle)

* It's better to check whether SOLID principles are applied or not.***

* Code duplication should be checked, if there is a code written for the same purpose in the project, that code should be used or changed so that it can be used. (Don't Repeat Yourself)

* Edit any unreadable and not properly named code. The method or class name should describe its work correctly.

* Be sure that exceptions and errors are handled and logged properly. If the method returns an error code, check that if it describes the error properly.

* The code should be checked for security as possible as you can. Be aware of SQL Injection, XSS, Man in the middle, etc. Specifically, check the implementation behind the HTTP 3xx redirects.

* Performance controls should be done. Possible null pointer exceptions, cache errors, possible lazy initialization exceptions, String + String usages instead of StringBuilder should be fixed.

* checkstyle/ findbugs issues are resolved