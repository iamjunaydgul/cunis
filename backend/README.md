# Backend - Spring Boot
This project includes the following functionalities:

### APIs:
1. **API to save country name and university data in the database**
   - This API allows saving information about universities along with their country name into the H2 (in-memory) database.

2. **API to retrieve university data based on country name**
   - This API retrieves data of universities based on the provided country name from the H2 (in-memory) database.

3. **API to update university data based on country name and university ID**
   - This API updates the information of a university based on the provided country name and university ID in the H2 (in-memory) database.

### Note:
- For the H2 (in-memory) database:
  - The database file will be created at the location `./data/testdb`, as configured in the `applications.properties` file.
    ```
    spring.datasource.url=jdbc:h2:file:./data/testdb
    ```
  - After running the application, first, verify and use the exact `testdb` file location in `localhost:(your port)/h2-console`. For example:
    ```
    jdbc:h2:~/downloads/uniInfo/data/testdb
    ```
  - Ensure the correct path is used for accessing the `testdb` file in the H2 console.

![image](https://github.com/iamjunaydgul/cunis/assets/52971436/0ba98873-b2e4-46ae-923a-06181434a7a1)
