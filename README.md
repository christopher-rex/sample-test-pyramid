# Sample Test Pyramid

The purpose of this repository is to demonstrate a TestPyramid implementation, with different automated test layers in action. For the purpose of this demonstration, we have built a web application (backed by http services) which is the application under testing. 

## What is a Test-Pyramid?

[Learn from Martin Fowler](https://martinfowler.com/bliki/TestPyramid.html) 

## Application under test

The application we have is a scaled-down version of shopping-cart which has login and coupon-validation features.

The application itself has two functional components,
- A web application layer (simply the UI layer) >> Code is in [frontend](https://github.com/christopher-rex/sample-test-pyramid/tree/master/frontend) folder
- A service layer consisting of two micro-services, 
    - User service which provides user authentication >> Code is in [user_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/user_service) folder
    - Coupon service which provides coupon validation >> Code is in [coupon_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/coupon_service) folder

## Test layers demonstrated

We have built 4 layers of automated testing. They are,

- End-to-End (E2E) tests written as cucumber scenarios tagged as @e2e, located at [ui-tests/features](https://github.com/christopher-rex/sample-test-pyramid/tree/master/ui-tests/featuers) folder
- Stubbed tests written as cucumber scenarios tagged as @stub, located at [ui-tests/features](https://github.com/christopher-rex/sample-test-pyramid/tree/master/ui-tests/features) folder
- Service API (or simply Integration) tests written as JUnit tests, located within each individual service
    - User service >> [UserServiceTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/src/test/java/UserServiceTest.java)
    - Coupon service >> [CouponServiceTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/coupon_service/src/test/java/CouponServiceTest.java)
- Unit tests written as JUnit tests, needless to say, are also bundled within each corresponding service
    - User service 
        - [LoginHandlerTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/src/test/java/com/testpyramid/handlers/LoginHandlerTest.java)
        - [UserRepositoryTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/src/test/java/com/testpyramid/persistence/UserRepositoryTest.java)
    - Coupon service 
        - [CouponHandlerTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/coupon_service/src/test/java/com/testpyramid/handlers/CouponHandlerTest.java)
        - [CouponRepositoryTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/coupon_service/src/test/java/com/testpyramid/persistence/CouponRepositoryTest.java)

## Start the servers

To start the servers, for convenience, we have added a bash-script `up` in [frontend](https://github.com/christopher-rex/sample-test-pyramid/tree/master/frontend), [user_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/user_service), [coupon_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/coupon_service) folders which will execute the necessary commands to start the corresponding server.
 ```
  [sample-test-pyramid/user_service]$ ./up
  [sample-test-pyramid/coupon_service]$ ./up
  [sample-test-pyramid/frontend]$ ./up
 ```
 
## Run the tests 
For details of how to build or run the tests, refer to the individual README.md files in the corresponding folders, 
- [User service >> README](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/README.md)
- [Coupon service >> README](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/README.md)
- [Webapp >> README](https://github.com/christopher-rex/sample-test-pyramid/blob/master/frontend/README.md)
- [Ui-tests >> README](https://github.com/christopher-rex/sample-test-pyramid/blob/master/ui-tests/README.md)

