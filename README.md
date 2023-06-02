# word-counter

This repository consists of two projects a standard counter library and a spring boot api application which uses the library to expose the code to a api

How to install:
- Download the repository on to a local machine
- Navigate into word-couter app and run mvn clean install (the library can be ran alone via a main method)
- Navigate into the api app and run it (the word-couter dependancy has to be built first in order to build the api)

The library exposes two methods, addWord and getWordCount via a WordCounter class

The getWord method accepts a string as a input and then after some initial validation (checks if the string is aplhabetical via a regular expression) 
splits the input into words with spaces and adds them to a map where the key is the translated word and the value is the number of times the word has been found.
The method returns a boolean which indicates the success of the insertion.

The getWordCount method insures that the string entered is a valid and is a singular word (again with the help of a regex) and then checks if a entry in the hash map
is found with the key matching to the string. If yes, it returns the number of occurrences, if not it returns 0.

Both unit and integration tests covering all use cases are added.

Why use a hashmap?
After contemplating, I've chosen a hashmap because it has a O(1) access time, meaninng it be almost as fast for a small and many number of elements in the map.
Therefore it prefectly coveres the given use case. Concurrency is achieved with the help of a ConcurrentHashMap which locks certain parts of the map when modifying it's
contents and is therefore thread safe.

*Note* A possible enchancement would be to pre-calculate the number of translated occurrences of a given word when a string is submitted before putting the values into a hashmap,
so we don't insert a multiple of the same values in the map therefore calling the put method multiple times in one string for a single word. 
This enchancement would probably only be useful if a user is typing the same word in a string a bunch of times.

As far as design patterns are consirned, I didn't find a good solution to use any since the solution was quite small in size. The only use is a singleton pattern in the api project, to autowire
a WordCounter instance so that all users would share the same instance of that class (if multiple users would require not to share the same word counter then seperate instances and a possible peristance
would be needed)

As far as a microservice is considered, it really depends on what needs the services needs to cover, but the library is build so it can cover a variety of use cases. In the api project
the main method of communication is REST, though since the library is thread safe it could use a faster method like websockets.

As far as access, and resilliency. It really depends on the number of expected users that can access the service. For example, for every user a WordCounter object can be created and kept in memory, for a certain ammount
of time, and then it's contents can be persisted if reuse is needed. One instance can be used per microservice, and then we could spin up a service to server each individual user, or a mix in between.
My general idea is with this solution if one map is used for multiple users I would keep it in memory to a certain point and then find a way to optimize or persist the data, depending on the service needs.

