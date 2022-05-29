# ProjectManagementSystem
This application provides the ability to work with the database and perform a number of operations with it.
This is a console application. 

### Version 1.0 of application provides works with databases — H2 and MYSQL.

## To work before the first launch, you need to do the following steps (once):

1. You need to execute the script in your databases, which is located in the "initDB.sql" file in the root directory of the project.
2. You need to execute the script in your databases, which is located in the "populateDB.sql" file in the root directory of the project.
3. You must enter the data in the json configuration file for each database. The files are named DATABASE NAME_config.json. 
4. Run the application. The main class is EntryPoint.java.

When app is runing you may choose `CRUD` or `Home Task Query` in main menu. The first allows to make operations with database of the type CREATE, DELETE, UPDATE, REED only you mast write correct SQL script. The second allows you to complete query from home task №4. 

