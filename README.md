# webscraper

Basic webscraper example for a class, nothing really groundbreaking here.

## Usage

```shell
java -jar webscraper.jar --url https://apps.sfcollege.edu/directory/?all --selector //table
```

To save the scraped contents to a CSV file use:
```shell
java -jar webscraper.jar --url https://apps.sfcollege.edu/directory/?all --selector //table > /path/to/save/data.csv
```
