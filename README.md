# Aconex coding challenge

## Design of the system
- **InputAdapter** interface, with two implementations FileInputAdapter (reads data from file) and ConsoleInputAdapter
(reads data from console).
- **NumberEncoding** interface with default implementation (DefaultNumberEncoding) to map letter to digit.
- **ArgumentParser** class parses arguments given by user to check if the user has given phone number file or dictionary file as input.
- **DataLoader** class loads dictionary and phone number data. Loads dictionary file given by user,if not, loads default dictionary. Loads phone numbers file if given by user,otherwise reads from console.
- **Dictionary** - Given a set of words and number encoding, it loads the inverted index (number to words mapping) of words, and uses this index to provide a set of dictionary words for any number.
- **PhoneToWordConverter** - Given a loaded dictionary, finds all possible matched phrases/words for a given phone number. This is the logic that it uses.
- **Orchestrator** - Class which triggers the entire flow - parsing arguments, loading data, loading inverted index of dictionary, converting the phone numbers to words, and displaying the results.

## Logic
- '.' is used as a separator in phone number string.
- Checks if the phone number has '.', If so, splits the string on '.', and for each substring, finds matched words,and combines them using '-'.
    * If any of these substrings(except the last one) is empty, its an invalid number, returns null. eg, 
        - Invalid numbers: 1. .3 , .1  
        - Valid numbers:1. , 12. (gets converted to 1 and 12)
        - Valid numbers: 1.45.23 (number can have multiple dots)
    * If no match is found for any one of these stings, number has no matches.
    * **Logic to find matches**:
    * Finds dictionary words for the whole number. If there are matched words, returns them.
        e.g. 2255 -  one of the matched words from dictionary is 'CALL'.
    * If no matches in dictionary, and number is of one digit, then returns the digit.
        e.g. 1 -  returns 1
    * Otherwise finds skip digit results. All possible matches by keeping any one of the digits as it is. eg. 445 - no matches for 445, splits on each digit ([4,45], [4,4,5], [44,5]). for each digit split list, finds matches for each of the substrings, and if matches are found, combines them with '-'.

##

## Running the program:

### Dependencies
- Java 7 or above,
- Maven  

### Step to build
```sh
$ cd aconex-coding-challenge
$ mvn clean install
```

## Usage:

- After building successfully, it will generate jar in `target` directory.
- User can provide dictionary file and phone number in the following way

```sh
$ java -jar target/aconex-coding-challenge-1.0-SNAPSHOT.jar -p /fullpath/filename.txt -d /fullpath/dictionary.txt
``` 
- If user doesn't provide input files, program will take numbers from console and use the default dictionary.
- After entering input numbers, user will have press ***q*** or ***quit***. Sample usage is shown below:
```sh
$ java -jar target/aconex-coding-challenge-1.0-SNAPSHOT.jar 
Please enter phone numbers, press q or quit to exit.
2255.63
q
```
