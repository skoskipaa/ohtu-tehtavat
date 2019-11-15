Feature: User can log in with valid username/password-combination

    Scenario: user can login with correct password
       Given command login is selected
       When  username "pekka" and password "akkep" are entered
       Then  system will respond with "logged in"

    Scenario: user cannot login with incorrect password
       Given command login is selected
       When username "pekka" and password "pekka" are entered
       Then system will respond with "wrong username or password"

    Scenario: non-existent user cannot log in
       Given command login is selected
       When username "kalle" and password "akkep" are entered
       Then system will respond with "wrong username or password"