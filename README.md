# Bitwise Calculator

The **Bitwise Calculator** is a Java program that evaluates arithmetic expressions involving bitwise operations (`&` for AND and `^` for XOR) and supports the use of parentheses for grouping. It uses a **recursive descent parsing** approach to evaluate the input expressions.

## Features
- Supports **bitwise AND (`&`)** and **bitwise XOR (`^`)** operations.
- Handles **parentheses** for grouping expressions.
- Parses and evaluates expressions in accordance with operator precedence:
  - **`&` (AND)** has higher precedence than **`^` (XOR)**.
- Reads expressions from an `InputStream`.

## Grammar
The calculator is implemented based on the following grammar:

Exp       → XorTerm Exp2  
Exp2      → '^' XorTerm Exp2 | ε  
XorTerm   → AndTerm XorTerm2  
XorTerm2  → '&' AndTerm XorTerm2 | ε  
AndTerm   → Digit | '(' Exp ')'  


### Examples of Valid Expressions
- `1 & 2`
- `1 ^ 2`
- `(1 & 3) ^ 4`
- `1 & (1 ^ 3)`

## How It Works
1. The program takes input from an `InputStream` and processes it character by character.
2. It uses **recursive descent parsing** to evaluate the input based on the grammar rules.
3. It checks for syntax errors and ensures the input expression is valid before producing a result.

## Implementation Details
### Classes and Methods
- **Constructor**: 
  - `BitwiseCalculator(InputStream in)` initializes the input stream and reads the first character (`lookahead`).
  
- **Core Methods**:
  - `eval()`:
    - Entry point for evaluating the expression.
    - Calls the `Exp()` method to parse the input.
    - Ensures no characters are left unprocessed.
  - `Exp()`, `XorTerm()`, and `AndTerm()`:
    - Recursive methods for parsing and evaluating expressions based on operator precedence.
  - `consume(int symbol)`:
    - Validates and advances the input stream to the next character.
  - `isDigit(int c)`:
    - Checks if a character is a digit.
  - `evalDigit(int c)`:
    - Converts a digit character into its integer value.

- **Error Handling**:
  - Throws `ParseError` for invalid input or syntax errors.
 
## Execution

1. **Compile**
   - `make compile`
   
2. **Clear**
   - `make clean`
