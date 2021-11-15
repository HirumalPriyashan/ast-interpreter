# ast-interpreter

An algorithm to convert Abstract Syntax Tree (AST) in to Standardize Tree (ST) and implement CSE machine

- Name: Hirumal Priyashan
- Index No: 180463U

## Program structure

The program directory structure is as following

```txt
│
├───docs : javadocs for source files are included here
├───inputs : set of input files
├───lib : required libraries for test the functionality
├───out : compiled class files
├───outputs : set of output files for iput files 
└───src : source code for the application
```

`src` directory will have follwing structure

```txt
│   Main.java
│
├───ASTInterpreter
│       ASTInterpreter.java
│       ASTInterpreterTest.java
│       ASTStandardizer.java
│
├───ASTTreeGenerator
│       ASTGenerator.java
│
├───CSEMachine
│   │   CSEMachine.java
│   │   CSEMachineWrapper.java
│   │   CSEMachineWrapperTest.java
│   │   CSEStandardizer.java
│   │
│   ├───CSERules
│   │       AbstractRule.java
│   │       BuiltInFunctions.java
│   │       CSERule1.java
│   │       CSERule10.java
│   │       CSERule11.java
│   │       CSERule12.java
│   │       CSERule13.java
│   │       CSERule2.java
│   │       CSERule3.java
│   │       CSERule4.java
│   │       CSERule5.java
│   │       CSERule6.java
│   │       CSERule7.java
│   │       CSERule8.java
│   │       CSERule9.java
│   │
│   └───Symbols
│           Symbol.java
│           SymbolFactory.java
│
├───FileReader
│       FileReader.java
│       TextFileReader.java
│
├───Logger
│       Logger.java
│
├───MyExeptions
│       ASTNotGeneratedException.java
│
├───Node
│       Node.java
│
├───Standardizer
│       AbstractStandardizer.java
│       AtStandardizer.java
│       CommaStandardizer.java
│       ConditionalStandardizer.java
│       FnFrmStandardizer.java
│       LetStandardizer.java
│       MultiParameterStandardizer.java
│       OpStandardizer.java
│       RecStandardizer.java
│       SimultaniousStandardizer.java
│       Standardizer.java
│       TupleStandardizer.java
│       WhereStandatdizer.java
│       WithinStandardizer.java
│
└───Visitor
        IVisitable.java
        IVisitor.java
```

## How to use

Navigate to the src directory

```bash
cd ./src
```

compile source files

```bash
javac .\Main.java
```

run the program

```bash
java Main <input-file-path> [-c] [-a]
```
