# Java-Implementation-of-PageRank-Algorithm

This is an implementation of a Spark standalone program using Java to calculate page rank value of a user in the Wikipedia vote network. 

The program outputs all the users and their page rank values into a HDFS file.

To run:

> Make sure it is current directory

> Execute the command
   
    $ mvn package

> For  Problem a: 1) execute $ chmod +x ./wordfind.sh
                  2) execute $ ./wordfind.sh "spark://hdn1001.local:7077" "/CS5331_Examples/Programming_Project_Dataset.txt" "texas tech"
                 
> For  Problem b: 1) execute $ chmod +x ./pagerank.sh
                  2) execute $ ./pagerank.sh "spark://hdn1001.local:7077" "/CS5331_Examples/wiki-Vote.txt" "10"

Note: The shell script contains the necessary jar files and commands to execute the code.
