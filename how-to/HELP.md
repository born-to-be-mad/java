# HOW TO DO SMTH in Java way

* ### Copy Files by using
  - [X] FileInputStream/FileOutputStream
  - [X] java.nio.channels.FileChannel
  - [X] Apache Commons IO
  - [X] java.nio.file.Files(Java >=7)

* ### Sort Arrays
  - [X] arrays of primitives via Arrays.sort(Dual-Pivot implementation of Quicksort)
  - [X] arrays of primitives via IntStream and Comparator
  - [X] Stings via Arrays.sort
  - [X] Stings via Arrays.sort and Comparator
  - [X] Objects via Arrays.sort and Comparator
  
* ### Check that Array is sorted  
  - [X] arrays of primitives
  - [X] arrays of Comparable objects
  - [X] arrays of Objects
  
* ### Write JUnit tests
  - [X] Setting Up(junit dependency is needed)
  - [X] Run all the unit test classes.
`$ mvn test`

  - [X] Run all the unit test classes with debug
`$ mvn test -e`


  - [X] Run a single test class.
`$ mvn -Dtest=TestApp1 test`

  - [X] Run multiple test classes.
`$ mvn -Dtest=TestApp1,TestApp2 test`

  - [X] Run a single test method from a test class.
`$ mvn -Dtest=TestApp1#methodname test`

  - [X] Run all test methods that match pattern 'testHello*' from a test class.
`$ mvn -Dtest=TestApp1#testHello* test`

  - [X] Run all test methods match pattern 'testHello*' and 'testMagic*' from a test class.
`$ mvn -Dtest=TestApp1#testHello*+testMagic* test`  
  