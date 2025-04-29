# Tema02---Lab-PAOJ
The full solution to the tasks listed in the second assignment of the Java Laboratory.

# Topic 2 – Advanced Object-Oriented Programming in Java

## 1. Permission Management with Marker Interfaces and `instanceof` Verification

**Specific Requirements:**

- Define a hierarchy of users: `User` (base class), with subclasses `Administrator`, `Editor`, and `Visitor`.
- Create marker interfaces such as `MaybeEdit`, `MaybeEditDelete`, and `MaybeView`, implemented according to the permissions of each user type.
- In an `ActionService` class, use `instanceof` to check whether a user has permission to perform a given action.
- Dynamically display the actions allowed for each type of user.

## 2. Using Sealed Classes to Validate Types in a Payment System

**Specific Requirements:**

- Create the `MethodPlate` class defined as `sealed`, allowing only extensions by `Card`, `Cash`, and `BankTransfer`.
- Each subclass must have specific behaviors:
  - `Card`: CVV and expiration date validation.
  - `Cash`: Displays that the transaction is instant.
  - `BankTransfer`: Checks the IBAN code.
- Create a method that receives a `PlateMethod` object and performs validation logic according to the actual subtype (using pattern matching with `instanceof`).
- Display relevant messages depending on the payment method used.

## 3. Custom Exception Handling and Multiple Error Handling

**Specific Requirements:**

- Create an application that simulates a reservation system with custom exception classes:
  - `ReservationException` (base class, checked).
  - `PlaceAvailableException`, `DateInvalidException` (derived from the base class).
- Implement the method `ReservationLoc(String date, int place)` that may throw multiple exceptions based on input data.
- In the `main` method, handle exceptions using separate `try-catch` blocks, followed by a generic `Exception` catch block.
- Display a final report indicating whether the reservation was successful (using `finally`).

## 4. Implementing an Adapter Class for Integration with a Third-Party Library

**Specific Requirements:**

- Assume the existence of an external class `SystemExistent` with the method `void displayXML(String xml)` (provide a simple implementation).
- Create your own system that generates data in JSON format using `String generateJSON()`.
- Implement an adapter class `AdaptorJsonToXml` that transforms JSON into XML and passes it to `SystemExistent`.
- Demonstrate the use of the adapter to integrate both systems without modifying existing code.
- Add a comment explaining why this pattern is useful in real-world applications.

## 5. Controlled Inheritance and Advanced Abstraction with `final` and `protected` Classes

**Specific Requirements:**

- Create an abstract class `OrganismViu` with abstract methods `breathe()` and `feed()`.
- Create a class `Animal` that extends `OrganismViu` and marks methods such as `breathe()` as `final`.
- Create another abstract class `Mammal` that derives from `Animal` and adds specific behaviors (e.g., `hasFeather()`).
- Implement concrete classes `Bear` and `Dolphin`, each implementing the remaining abstract methods.
- In the `main` method, create a list of `OrganismViu` and demonstrate polymorphic method calls.

## 6. Interface Inheritance and Default/Static/Private Methods

**Specific Requirements:**

- Define a `Device` interface with methods:
  - `start()`, `stop()`, a default method `status()`, and a static method `descriptionGeneral()`.
- Add a private method inside the interface to be used internally by `status()`.
- Create interfaces `Smart` and `Connectable` that extend `Device`.
- Implement classes `Phone`, `Smartwatch`, and `Television` based on these interfaces.
- Demonstrate in the `main` method all possible method calls related to the interface.

## 7. Managing Collections with `HashSet`, `HashMap` and Overriding `equals()` and `hashCode()`

**Specific Requirements:**

- Create a class `Product` with attributes such as `code`, `name`, and `price`.
- Override `equals()` and `hashCode()` so that two products with the same `code` are considered equal.
- Add products to a `HashSet<Product>` and demonstrate that duplicates are not added.
- Create a `HashMap<Product, Integer>` that maps a product to its available stock.
- Display the products and their stock using `entrySet()` and `forEach()`.

## 8. Advanced Enums with Custom Methods and Constructors

**Specific Requirements:**

- Create an enum `AccessLevel` with values: `ADMIN`, `EDITOR`, `USER`, `GUEST`.
- Each value must have a numeric code and a description (e.g., `ADMIN(1, "Full access")`).
- Add custom methods:
  - `getDescription()`, `getCode()`, and `fromCode(int code)` to retrieve the corresponding enum.
- Create a `UserAccount` class that contains an `AccessLevel` as an attribute.
- In the `main` method, create multiple accounts and filter only those with access above a certain level (compare enums using their natural order).

