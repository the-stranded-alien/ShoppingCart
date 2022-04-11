# Version Number : 5b8d0fd276b6d288905ed2f63a934e057e8feca2

## ShoppingCart
A Shopping Cart Web App using SpringBoot &amp; Understanding the Basics of TDD

##Instruction for ShoppingCart :

### FrameWork Used : Java SpringBoot (Maven)

##### Creating the MySQL Database
1. mysql -u root -p
2. Enter your password to the MySQL
3. CREATE DATABASE shop;
4. CREATE USER shop;
5. ALTER USER shop IDENTIFIED BY 'shop';
6. GRANT ALL PRIVILEGES ON shop.* to shop;
7. FLUSH PRIVILEGES;
8. Try to login to your 'shop' database using : mysql -u shop -p
9. Enter Password 'shop'.
10. USE shop;
11. Successfully creates the database required.

##### Steps to Run the Application
1. Extract the project from the zip file.
2. Open the project in any ide that supports Java SpringBoot projects.
3. Resolve the Maven dependencies by going to pom.xml
4. Then go to the IDE's terminal and enter the command : mvn spring-boot:run .
5. Your application should be started on localhost:/8080 or any other port number as set up by you.
6. Register yourself first and then login using your credentials.
7. Add few products into the database using Add Product before going and adding anything to your cart.
8. Your shopping cart is ready to be used.

## Instruction for Step1, Step2, Step3
#### Steps to run
1. Unzip the folder.
2. Open the unzipped folder with any IDE that support SpringBoot
3. Resolve Maven Dependencies
4. Go to ShoppingCart >> src >> test >> CartTest
5. Run All Tests to Verify


## Few Points to Consider
1. I haven't done TDD with any framework before and I was getting lots of errors related to Spring Context and Beans with my main Web-App "Shopping Cart".
2. The entire application runs fine and you can perform all the 3 steps on it, just the test cases aren't working.
3. So, I implemented basic Unit Tests with simple Java Code for all 3 Steps, presented in Step1, Step2, Step3.
4. I tried understanding the basics of TDD and tried implementing as well but couldn't do it with SpringBoot webapp.
