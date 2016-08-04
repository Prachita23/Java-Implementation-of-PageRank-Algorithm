package prachita;

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object WordFind{

    def main(args: Array[String]) {
    //check the number of arguments is not less than 3 
    if (args.length < 3) {
      System.err.println("Usage: WordFind <SparkInstance> <InputFile> <Keyword>")
      System.exit(1);      
    }
    //extract the value of arguments into their respective variables
    val sparkInst= args(0);
    val inputFile= args(1);
    val keyWord= args(2);

    //setup a spark configuraion and set the master
    val sparkConf = new SparkConf().setAppName("WordFind").setMaster(sparkInst)
    
    //creat a context
    val sc= new SparkContext(sparkInst, "prachita.WordFind", sparkConf)
   // printf("=========== " + inputFile + "====================\n")
    //load the textfile as  RDD
    val dataSet= sc.textFile(inputFile);
    //separate the input by tab and map the 4th column of each RDD into a new RDD.Execute filter function on the new RDD to check if the article
    //contains the keyword. Later call the count() function to calculate how many articles contain the spcifid keyword.
    var articleCount= dataSet.map(line=>line.split("\\t")(3)).filter(line=>line.contains(keyWord)).count()
    
    //display the output
    printf("Keyword %s found in %d articles \n", keyWord, articleCount);
    }

}

