@calc_sample

Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
 
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
    When the customer searches for books published between 2013-02-11 and 2014-10-03
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'

  Scenario: Search books by author name
    Given a book with the title 'This book is nice', written by 'Fred Wilson', published in 2020-05-11
      And another book with the title 'Books are good', written by 'David Silta', published in 2007-03-27
      And another book with the title 'Books are great', written by 'Liam Washin', published in 2015-08-25
      And another book with the title 'How to choose a good book', written by 'Liam Washin', published in 1994-12-04
    When the customer searches for books with the author 'Liam Washin'
    Then 2 books should have been found
      And Book 1 should have the title 'Books are great'
      And Book 2 should have the title 'How to choose a good book'

    Scenario: Search books by title
    Given a book with the title 'Nuggets or Fried Chicken', written by 'John Miles', published in 2023-04-17
      And another book with the title 'Riding on the street', written by 'Charles Macqueen', published in 2005-09-20
    When the customer searches for books with the title 'Nuggets or Fried Chicken'
    Then 1 books should have been found
      And Book 1 should have the title 'Nuggets or Fried Chicken' and the author 'John Miles'

    Scenario: Search books by category
    Given a 'comedy' book with the title '6 jokes to learn while riding a bike', written by 'John Miles', published in 2021-02-28
      And another 'food' book with the title 'Peanut cookies and more', written by 'Mary Thomson', published in 2024-01-07
    When the customer searches for books with the category 'food'
    Then 1 books should have been found
      And Book 1 should have the title 'Peanut cookies and more'



