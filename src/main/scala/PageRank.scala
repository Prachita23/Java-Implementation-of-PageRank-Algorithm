package prachita

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf


object PageRank {
        def main(args: Array[String]) {
		//make sure 3 arguments are given as Input
                if (args.length < 3) {
                        System.err.println("usage : PageRank <sparkMaster> <dataFile> <Iteration>");
                        System.exit(1);
                }
		//extract the arguments in their respective variables
                val sparkInst = args(0)
                val dataSet = args(1)
                val iteration = args(2).toInt;

                //set up Spark configuration and set Spark Master
                val conf = new SparkConf().setMaster(sparkInst).setAppName("PageRank")
		//create Spark context
                val sc = new SparkContext(sparkInst, "prachita.PageRank" , conf)
		//load text file as RDD
                val lines = sc.textFile(dataSet,1)
		//RDD of user and neighbours
		val links = lines.map(s=>s.split("\\s+")).map(p=>(p(0), p(1))).distinct.groupByKey().cache()
		//RDD of user and rank

                var ranks = links.mapValues(v=>1.0)

                //iterate the number of times as given in input
                for (i <- 1 to iteration) {
                        var contribs = links.join(ranks).values.flatMap{
                                case (urls, rank) => val size = urls.size;
                                urls.map(url => (url, rank/size))
                        }
                        ranks = contribs.reduceByKey(_+_).mapValues(0.15 + 0.85 * _)
                }
		//save the output
                ranks.map(p => p._1 + "\t" + p._2).saveAsTextFile("result");

}
}
