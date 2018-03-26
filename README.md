# sudoku_solver
Brute Force Sudoku Solver in Java

## How to compile

Install [Maven](https://maven.apache.org/).

Execute `mvn clean package` to compile.

## How to run the project

Execute `java -jar target/sudoku-solver-1.0-jar-with-dependencies.jar` to run the application and input the sudoku matrix (not optimal)

or

Execute `java -jar target/sudoku-solver-1.0-jar-with-dependencies.jar path/to/json/file` to run the application passing the path of a JSON file with the following format:

```json
[
    [0,1,2,...],
    [0,1,2,...],
    ...
]
```

Example:
```json
[
    [1,0,0,4,8,9,0,0,6],
    [7,3,0,0,0,0,0,4,0],
    [0,0,0,0,0,1,2,9,5],
    [0,0,7,1,2,0,6,0,0],
    [5,0,0,7,0,3,0,0,8],
    [0,0,6,0,9,5,7,0,0],
    [9,1,4,6,0,0,0,0,0],
    [0,2,0,0,0,0,0,3,7],
    [8,0,0,5,1,2,0,0,4]
]
```