# Universal Data Processor with Adapter Pattern

## Introduction
This Universal Data Processor is a Java application crafted to efficiently manage and process educational data, accommodating both CSV and JSON file formats with ease. It demonstrates the power of design patterns by implementing generic interfaces for data processing and employing the Adapter pattern to provide a consistent and unified interface for multiple data formats. By leveraging external libraries such as OpenCSV for CSV files and Gson for JSON files, this project underscores the significance of dependency management tools by showcasing functionality without relying on Maven or Gradle.

## Features
- **Flexible Data Processing**: Utilizes generic processing for diverse data models, enhancing the application's flexibility.
- **Support for CSV and JSON**: Seamlessly reads and writes data in both CSV and JSON formats, addressing a wide range of data storage preferences.
- **Adaptive Interface Design**: Employs the Adapter pattern to integrate CSV and JSON processing capabilities through a common interface, simplifying client interactions.
- **Comprehensive Data Analysis**: Offers functionalities to list records, calculate average values of numerical fields, and perform statistical analysis.
- **Robust Error Management**: Implements effective error handling for scenarios like out-of-bound indices and invalid file paths, ensuring stable application performance.

## Technologies
- **Programming Language**: Java
- **Libraries**: OpenCSV (for CSV processing), Gson (for JSON processing)
- **Design Patterns**: Strategy, Observer, and Adapter Patterns for versatile, responsive, and uniform data handling

## Implementation Details
- **Strategy Pattern**: Facilitates the dynamic selection of data processing strategies based on file type without affecting client code.
- **Observer Pattern**: Updates data processing tasks in real-time as file content changes, enhancing responsiveness.
- **Adapter Pattern**: Integrates CSV and JSON data processing methods into a unified interface, allowing the client to interact with different data formats transparently.
- **DataProcessor & WebDataProcessor Interfaces**: Define consistent operations for handling CSV and JSON data, ensuring the application's extensibility and maintainability.

## Setup and Installation
1. Install Java (JDK 8 or newer).
2. Clone the project repository.
3. Add the OpenCSV and Gson libraries to your project's build path manually (as the project does not use Maven or Gradle for dependency management).
4. Compile the project using your preferred IDE or the Java command-line compiler.

## How to Use
- Example usage for CSV data processing:
  ```java
  CSVUtils<Student> csvUtils = new CSVUtils<>();
  StartingDataContainer<Student> container = csvUtils.readFromFile("path/to/csv", true, Student.class);
  csvUtils.listAllRecords(container.getData(), container.getHeader());
  

- Example usage for CSV data processing:
 ```java
  JSONUtils<Student> jsonUtils = new JSONUtils<>();
  List<Student> students = jsonUtils.readDataFromFile("path/to/json", Student.class);
  jsonUtils.listAllFetchRecords(students);
 ```
- Libraries and mock data included in project.

## Contribution
- Your contributions are encouraged and appreciated! Fork this project to add features, fix bugs, or improve the documentation. Please submit a pull request with your enhancements for review.
