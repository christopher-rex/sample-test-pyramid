# Sample Test Pyramid

This repository is to demonstrate a TestPyramid implementation, with different automated test layers in action. For the purpose of this demonstration, we have built a web application (backed by a http service) which is the application under testing. 

## What is TestPyramid?

[Learn from Martin Fowler](https://martinfowler.com/bliki/TestPyramid.html) 

## Application Under Test

The application we have is a scaled-down version of shopping-cart which has login and coupon-validation features.

The application itself has two functional components,
- A web application layer (simply the UI layer). Code is in [frontend](https://github.com/christopher-rex/sample-test-pyramid/tree/master/frontend)folder
- A service Layer consisting of two micro-services 
    - User service which provides user authentication >> Code is in [user_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/user_service)folder
    - Coupon service which provides coupon validation >> Code is in [coupon_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/coupon_service)folder

## Demonstrated Test Layers

We have 4 layers of automated testing demonstrated for the application under test. They are

- End-to-End tests written as cucumber scenarios located at [ui-tests](https://github.com/christopher-rex/sample-test-pyramid/tree/master/ui-tests)folder
- Stubbed tests written as cucumber scenarios located at [ui-tests](https://github.com/christopher-rex/sample-test-pyramid/tree/master/ui-tests)folder
- Service API (or simply Integration) tests are located within each individual service
    - User service >> [UserServiceTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/src/test/java/UserServiceTest.java)
    - Coupon service >> [CouponServiceTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/coupon_service/src/test/java/CouponServiceTest.java)
- Unit tests (needless to say) are also bundled within each corresponding service
    - User service 
        - [LoginHandlerTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/src/test/java/com/testpyramid/handlers/LoginHandlerTest.java)
        - [UserRepositoryTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/src/test/java/com/testpyramid/persistence/UserRepositoryTest.java)
    - Coupon service 
        - [CouponHandlerTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/coupon_service/src/test/java/com/testpyramid/handlers/CouponHandlerTest.java)
        - [CouponRepositoryTest.java](https://github.com/christopher-rex/sample-test-pyramid/blob/master/coupon_service/src/test/java/com/testpyramid/persistence/CouponRepositoryTest.java)

## Starting the Servers

For ease of use, we have added a bash-script `up` in [frontend](https://github.com/christopher-rex/sample-test-pyramid/tree/master/frontend),[user_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/user_service),[coupon_service](https://github.com/christopher-rex/sample-test-pyramid/tree/master/coupon_service)folders which will execute the necessary command to start the corresponding server.
 ```
  [sample-test-pyramid/user_service]$ ./up
  [sample-test-pyramid/coupon_service]$ ./up
  [sample-test-pyramid/frontend]$ ./up
 ```
For more details of how to build, test, etc. refer to the README.md file in each of these folders 
- [README for the User service](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/README.md)
- [README for the Coupon service](https://github.com/christopher-rex/sample-test-pyramid/blob/master/user_service/README.md)
- [README for the Webapp](https://github.com/christopher-rex/sample-test-pyramid/blob/master/frontend/README.md) 

