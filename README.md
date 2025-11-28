# HQl_Example

Welcome to **HQl_Example**, a repository that demonstrates the most common operations you can perform using Hibernate Query Language (HQl) with Java and Hibernate ORM. The centerpiece of this repository is an interactive menu-driven application for querying and manipulating data in a `Product` table.

## Main Parts of the Code

- **`App.java`** ([source](src/main/java/com/hqlqueries/App.java)):
  - The main class that drives the HQl example application.
  - Initializes Hibernate, populates the database with sample `Product` entries, and presents an interactive menu.
  - Provides options for CRUD and search operations using HQl, with direct outputs to the console for each operation.

- **`Product.java`** (entity class in `com.hqlqueries.entity`):
  - Represents the product data with fields such as `id`, `name`, and `price`.
  - Mapped with Hibernate annotations to correspond to the database table.

- **`hibernate.cfg.xml`**:
  - The configuration file for Hibernate, specifying database connection parameters and entity mappings.

- **Other configuration files** (`pom.xml`, `.classpath`, `.project`, `.settings`):
  - Define dependencies, environment, and IDE settings.

## HQl Operations & Sample Outputs

The application supports the following operations with typical outputs shown below:

### 1. Fetch all the products

**HQl code:**
```java
Query<Product> query = session.createQuery("from Product", Product.class);
List<Product> productList = query.getResultList();
productList.forEach(System.out::println);
```
**Output Example:**
```
Product{id=1, name='Samsung Refrigerator', price=35000}
Product{id=2, name='RealMe 8 Pro', price=21999}
Product{id=3, name='Samsung Galaxy S25 Ultra', price=175000}
```

---

### 2. Fetch products with price > input value

**HQl code:**
```java
Query<Product> query2 = session.createQuery("from Product where price > :minPrice", Product.class);
query2.setParameter("minPrice", min);
List<Product> productList2 = query2.getResultList();
productList2.forEach(System.out::println);
```
**Output Example (for input price: 25000):**
```
Product{id=1, name='Samsung Refrigerator', price=35000}
Product{id=3, name='Samsung Galaxy S25 Ultra', price=175000}
```

---

### 3. Fetch all products ordered by price DESC

**HQl code:**
```java
Query<Product> query3 = session.createQuery("from Product order by price DESC", Product.class);
List<Product> productList3 = query3.getResultList();
productList3.forEach(System.out::println);
```
**Output Example:**
```
Product{id=3, name='Samsung Galaxy S25 Ultra', price=175000}
Product{id=1, name='Samsung Refrigerator', price=35000}
Product{id=2, name='RealMe 8 Pro', price=21999}
```

---

### 4. Update Product price by ID

**HQl code:**
```java
Query<?> query4 = session.createQuery("update Product set price = :newPrice where id = :id");
query4.setParameter("newPrice", price1);
query4.setParameter("id", id);
int rows = query4.executeUpdate();
```
**Output Example:**
```
1 record(s) updated.
```

---

### 5. Delete Product by ID

**HQl code:**
```java
Query<?> query5 = session.createQuery("delete from Product where id = :id");
query5.setParameter("id", delId);
int deleted = query5.executeUpdate();
```
**Output Example:**
```
1 record from Product with ID: 2
```

---

### 6. Count total number of products

**HQl code:**
```java
Query<Long> query6 = session.createQuery("select count(*) from Product", Long.class);
Long countLong = query6.uniqueResult();
System.out.println("Total products: " + countLong);
```
**Output Example:**
```
Total products: 2
```

---

### 7. Search the products by using LIKE operator

**HQl code:**
```java
Query<Product> query7 = session.createQuery("from Product where name like :txt", Product.class);
query7.setParameter("txt", txt + "%");
List<Product> productList4 =  query7.getResultList();
productList4.forEach(System.out::println);
```
**Output Example (for pattern: "Sam"):**
```
Product{id=1, name='Samsung Refrigerator', price=35000}
Product{id=3, name='Samsung Galaxy S25 Ultra', price=175000}
```

---

### 0. Exit

Cleanly closes the database session, factory, and scanner.

## Getting Started

1. **Clone the Repository**

   ```bash
   git clone https://github.com/SaiSujith07/HQl_Example.git
   ```

2. **Configure your environment**
   - Ensure you have Java and Maven installed.
   - Set up your database connection details in `hibernate.cfg.xml`.

3. **Build and Run**
   - Use Maven to compile and run the app:
     ```bash
     mvn compile
     mvn exec:java -Dexec.mainClass="com.hqlqueries.App"
     ```

## Contributing

Contributions are welcome! Feel free to open issues or pull requests for improvements, additional examples, or bug fixes.

## License

This project is licensed under the MIT License.

## Author

- [SaiSujith07](https://github.com/SaiSujith07)

---

Happy querying!
