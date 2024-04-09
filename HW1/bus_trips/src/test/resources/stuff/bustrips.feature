@calc_sample

Feature: Search Trip
  To allow a user to find himself a trip between 2 cities, the platform must offer ways to search for a trip.

  Scenario: Search for a trip between 2 cities x and y
    Given a trip from 'aveiro' to 'viseu' day 11.04.2024 with departure at 09:10 and arrival at 10:15 with duration 01:05 and price 3.99
    And another trip from 'aveiro' to 'viseu' day 09.04.2024 with departure at 10:15 and arrival at 11:15 with duration 01:00 and price 2.99
    And another trip from 'aveiro' to 'viseu' day 09.04.2024 with departure at 11:30 and arrival at 12:35 with duration 01:05 and price 2.99
    When the user searches from a trip between 'aveiro' and 'viseu' day 09.04.2024
    Then 2 trips should be returned
      And Trip 1 should departe at 10:15 of 09.04.2024 day
      And Trip 2 should departe at 11:30 of 09.04.2024 day 

  Scenario: Search for a trip between 2 cities x and y with currency
    Given a trip from 'aveiro' to 'viseu' day 11.04.2024 with departure at 09:10 and arrival at 10:15 with duration 01:05 and price 3.99 with currency "EUR"
    And another trip from 'aveiro' to 'viseu' day 09.04.2024 with departure at 10:15 and arrival at 11:15 with duration 01:00 and price 2.99 with currency "EUR"
    And another trip from 'aveiro' to 'viseu' day 09.04.2024 with departure at 11:30 and arrival at 12:35 with duration 01:05 and price 2.99 with currency "USD"
    When the user searches from a trip between 'aveiro' and 'viseu' day 09.04.2024 with currency "EUR"
    Then 1 trips should be returned
      And Trip 1 should be departe at 10:15 of 09.04.2024 day with the currency "EUR"
