# kotlin-pretty-print

## Installation

> ℹ\
> Kotlin Pretty Print is not available on any public repositories,\
> so you will have to clone the project and compile it yourself

````xml
<dependency>
    <groupId>de.kotlinPrettyPrint</groupId>
    <artifactId>kotlin-pretty-print</artifactId>
    <version>1.0</version>
</dependency>
````

## Usage

A Pretty Print can be created using the prettyPrint{} function. Kotlin Pretty Print is intended
to be used with multi-line-strings.
````kotlin
prettyPrint { 
    """
        some text
    """
}
````

Inside the prettyPrint-Block, the printForEach{} function becomes available.
This function will apply a template to each Element of an Iterable<>.
````kotlin
prettyPrint { 
    """
        Here is a List:
        ${list.printForEach { """
            this will be printed for each list-entry
        """
        }}
    """
}
````

## Example

#### Example Data Structure
````kotlin
data class Department(
    val name: String,
    val location: String?,
    val employees: List<Employee>
)

data class Employee(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val programmingLanguages: List<String>
)
````

#### Example Data

````kotlin
val departments = listOf(
    Department("Main Department", "Berlin", listOf(
        Employee("John", "Doe", 45, listOf("Kotlin", "Java", "Javascript")),
        Employee("Birgit", "Müller", 26, listOf("Kotlin", "Java"))
    )),
    Department("Side Department", null, listOf(
        Employee("Jane", "Smith", 53, listOf("Java", "C", "Fortran")),
        Employee("Harald", "Günther", 28, listOf("Java", "Delphi"))
    ))
)
````

#### Pretty Print Statement

````kotlin
prettyPrint {
    """            
        List of departments:
        ${departments.printForEach {
        """
            Department '$name'
            Location: ${location ?: "unknown"}
            Employees:
            ${employees.printForEach {
            """
                $firstName $lastName
                Age: $age
                Programming Languages: $programmingLanguages
            """
        }}
        """
    }}
    """
}
````

#### Output

````
List of departments:

    Department 'Main Department'
    Location: Berlin
    Employees:
    
        John Doe
        Age: 45
        Programming Languages: [Kotlin, Java, Javascript]
    
        Birgit Müller
        Age: 26
        Programming Languages: [Kotlin, Java]
    

    Department 'Side Department'
    Location: unknown
    Employees:
    
        Jane Smith
        Age: 53
        Programming Languages: [Java, C, Fortran]
    
        Harald Günther
        Age: 28
        Programming Languages: [Java, Delphi]
````