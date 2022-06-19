# selenium-ta-project

To run tests execute the following command:

```
mvn -Dbrowser=chrome -Denv=qa -DdefaultSuite=smoke test

```
To generate Allure report after test run execute the following command:
```
mvn allure:serve
```

browser parameter can be changed, available options at this moment:
- chrome
- firefox

env parameter can be changed, available options:
- qa
- dev

defaultSuite parameter can be changed, available options:
- smoke
- debug

You can run tests without specifying the defaultSuite, in this case debug suite will be run

```
mvn -Dbrowser=chrome -Denv=qa test

```
