Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is succesful with valid username and password
        Given command new is selected
        When username "jukka" and password "akkuj2000" are entered
        Then system will respond with "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When username "jukka" and password "kissa1980" are entered
        And username "jukka" and password "kalat1995" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When username "jj" and password "catcat999" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When username "pirkko" and password "ki55a" are entered
        Then system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When username "pirkko" and password "kissanainen" are entered
        Then system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salainen1" is created
        And command login is selected
        When username "eero" and password "salainen1" are entered
        Then system will respond with "logged in"

