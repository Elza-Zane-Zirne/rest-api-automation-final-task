# Go REST automation framework 

E2E test runner framework with Serenity and Maven

## Getting started

### Prerequisites

- Java 8
- Java friendly IDE (e.g. IntelliJ IDEA)

### Setup
1. Clone the repository
2. Import as Maven project


### Test execution

To execute tests use configuration 
`clean verify serenity:aggregate`

To execute tests with a specific tag add `-Dtags`argument

(For example, to run tests with @Run tag use configuration
`clean verify -Dtags=run serenity:aggregate`)

Test results are located at `target/site/serenity/index.html`

### Project structure
```bash 
.
├── main
│      ├── java/in.co.gorest.api
│      │       ├── support               # Support files
│      │       └── util                  # Utility files       
│      └── resources
│              └── data.properties       # Configuration data
└── test
       ├── java/in.co.gorest.api
       │       ├── gherkinsDefinitions   # Gherkins definitions 
       │       ├── requestBodies         # Request bodies
       │       ├── serenityRunner        # Execution settings
       │       └── serenitySteps         # Step implementation         
       └── resources
               ├── data                  # .json data files
               └── features              # Feature files  
```