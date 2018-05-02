GA SO Game 24 Trainer
=====================

This version uses Spring as a backend and a few static HTML files as front end.

### Building
Build the application using the standard Gradle build:

    ./gradlew build

### Running

    java -jar build/lib/gasogame24trainer-0.0.1-SNAPSHOT.jar

Then use the browser to go to

- <http://localhost:8080/viewpuzzle.html> to solve a puzzle by category

- <http://localhost:8080/practice_sheet.html> to print puzzle sheets.
  Use the puzzle type as parameter to indicate the number of puzzles
  to print.  For example:

    - `ONE_DOT=1`: print 1 one-dot puzzle
    - `ONE_DOT=2&TWO_DOT=3`: print 2 one-dot puzzles and 3 two-dot
      puzzles
    - `ONE_DOT,DOUBLE_FOUR=1`: print 1 one-dot four-double-digit puzzle
