# Vodafone Search Test
Write a test case (API) and automate it for testing search https://www.vodafone.ua/search

TestNG Suite included

# Test cases

### GET vodafone.ua/api/search/{phrase}/page=2

### Case 1
Send request without searching word ` GET vodafone.ua/api/search/?page=1`
and return status code **404**

### Case 2
Send request with available words and verify page 1 and page 2

### GET vodafone.ua/api/search/autocomplete/vodafone

### Case 3
Send request with word and get list of possible variants

### Case 4.1
Send request with empty phrase `GET vodafone.ua/api/search/autocomplete/` 
and receive empty list of possible variants

### Case 4.2
Send request with phrase without possible variants `GET vodafone.ua/api/search/autocomplete/123`
and receive  empty list of possible variants