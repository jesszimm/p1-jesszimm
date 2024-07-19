# WorkList Data Structures and Trie Implementation

## Overview

This project involves implementing various WorkList data structures and a Trie, with applications in data compression. It's part of a university course designed to enhance understanding of data structures and algorithms in Java.

## Project Goals

1. **Review Java Concepts**: Strengthen foundational skills in Java.
2. **Explore Data Structures**: Implement and understand WorkList and Trie data structures.
3. **Apply Knowledge**: Use these structures in a practical application involving data compression.

## Key Components

### WorkList ADT

A generalized data structure that encompasses:

- **add(work)**: Add an item to the WorkList.
- **peek()**: View the next item.
- **next()**: Retrieve and remove the next item.
- **hasWork()**: Check if there are items left.

### Data Structures Implemented

1. **ListFIFOQueue**: A linked list implementation ensuring O(1) operations.
2. **ArrayStack**: A dynamic array stack with manual resizing.
3. **CircularArrayFIFOQueue**: An efficient circular buffer.

### Trie Data Structure

- **HashTrieMap**: A trie using a HashMap for efficient storage and retrieval of string-based keys.

### Compression Application

- Implemented functionality to compress data into a `.zip` format compatible with standard zip programs.

## Restrictions

- Avoid using `java.util.*` except for specified exceptions.
- Code design and architecture are critical for grading.
- Use provided interfaces and classes without modification.

## Development Environment

- **Java**: Ensure a setup of IntelliJ and Git for development.
- **JUnit**: Tests written using JUnit 5 to ensure functionality and correctness.
