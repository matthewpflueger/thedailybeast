Code challenge from The Daily Beast
=============


## Overview

Demonstrate your knowledge of Web API usage, basic I/O operations using Java, and connecting to and consuming API endpoints.

Using the Daily Beast RSS 2.0 feed:

```
http://www.thedailybeast.com/feed/articles.rss.xml
```

(you may use the “limit” query parameter to specify how many articles to return)

Create a command line application to count the number of times that each word appears in the top 100 articles. Use the text from description tag of each item. Each word should be normalized to lowercase before counting. Ignore the words found in /src/main/resources/stopwords.txt. The output must be valid JSON and include the top 10 words and the number of stop words ignored. 

Please use the following format:

```json
{
    "words": [
        {
            "word": "foo",
            "count": 1000
        },
        {
            "word": "bar",
            "count": 500
        }
    ],
    "stopWordsIgnored": 10000
}
```

Requirements:

- Use Java 1.5/1.6+
- All dependencies should be publicly available or properly included with the project and referenced within the POM
- The code must include unit/integration tests
- We must be able to run your application by executing the following commands:

```
mvn package
java –jar target/code-test-1.0-SNAPSHOT-jar-with-dependencies.jar <PARAM>
```

