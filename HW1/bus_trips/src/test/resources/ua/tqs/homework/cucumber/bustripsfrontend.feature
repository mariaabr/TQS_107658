@calc_sample

Feature: Search Trip
    To allow a user to find and buy a bus trip.

    Scenario: Buy a bus trip from Aveiro to Viseu
    
        Given a page of the BusTripFrontend
        When I search for a bus trip from 'Aveiro' to 'Buston' at 09.04.2024 and click the button
        Then I choose the third bus trip and click 'Book this Trip'
            And fill the name input with 'MR'
            And fill the age input with 20
            And fill the address input with 'street Eça, 123'
            And fill the location input with 'Aveiro'
            And fill the city input with 'Aveiro'
            And fill the zipcode input with '1234-678'
            And fill the credit card number input with '34589'
            And fill the month input with '03'
            And fill the year input with '2027'
            And fill the the name on card input with 'MR'
            And click the 'Buy Bus Trip' button