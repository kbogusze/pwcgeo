# Project Description

Simple Spring Boot service, that is able to calculate any possible land
route from one country to another. The objective is to take a list of country data in JSON format
and calculate the route by utilizing individual countries border information.

## Dependencies
* Java version 17+ added to user environemnts variables

## To run the program and test it, you need to:
### 1. Copy the Git repository.
```
git clone https://github.com/kbogusze/pwcgeo.git
```
### 2. Build the project using the Maven wrapper tool.
### Windows
```
./mvnw.cmd clean install
```
### Linux
```
./mvnw clean install
```
### 3. Run the program
```
./mvnw spring-boot:run
```

### 4 Test using web browser
```
http://localhost:8080/routing/POL/ITA
```

### 5. Test using curl
```
curl http://localhost:8080/routing/POL/ITA
```


## Description of the solution
To implement this solution a spring boot project was created. On the project start program load data using feign 
client from the countries.json file that is published under 
https://raw.githubusercontent.com/mledoze/countries/master/countries.json . 
In the next step program create a graph of countries. 
The graph is represented as a map where the key is the country code and the value is a list of neighboring 
countries. Program search for all possible graphs that we could build. A graph is understood as set of all countries
that has border connection. To able user to search for a route there is an api endpoint that takes two country codes as parameter.
The program validate if the country codes are correct and if the route is possible using the graph that was created at startup.
If the route is possible the program returns the list of countries that are on the route. 
If the route is not possible the program returns an error message. To find the route the program uses a dijkstra algorithm.

