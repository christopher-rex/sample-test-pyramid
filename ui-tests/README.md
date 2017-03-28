# UI-Tests

## Run E2E tests
- Step 1: Ensure that the Webapp and the services are up-and-running
- Step 2: Execute the cucumber command to run the scenarios tagged as @e2e (excluding work-in-progress tests marked as @wip)
```
[sample-test-pyramid/ui-tests]$ bundle exec cucumber --tags @e2e --tags ~@wip
```
## Run Stubbed tests
- Step 1: Ensure that the Webapp is up-and-running
- Step 2: [Start the stub-server](#start-or-stop-the-stubbed-servers)
- Step 3: Execute the cucumber command to run the scenarios tagged as @stub (excluding work-in-progress tests marked as @wip)
```
[sample-test-pyramid/ui-tests]$ bundle exec cucumber --tags @stub --tags ~@wip
```

## Start or Stop the stubbed servers
Start stubbed servers for user-service and coupon-service:
```
 ./ui-tests/stub/stub.sh start
```
Stop the stubbed servers:
```
 ./ui-tests/stub/stub.sh stop
```
List the stubbed servers:
```
 ./ui-tests/stub/stub.sh list
```