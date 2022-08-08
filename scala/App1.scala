import org.apache.spark.{SparkConf, SparkContext}

//App对象是一个模板对象，它里面提供了main函数的接口
object App1 extends App {
    var log = "d:/sqoop.txt"
  //创建spark配置信息
  var sparkConf = new SparkConf().setAppName("word count").setMaster("local[*]")
  //创建spark上下文
  var sc = new SparkContext(sparkConf)

  //读数据形成一个RDD，在缓存，还是一个RDD
  var logData = sc.textFile(log).cache()

  //对每行过滤，只要这一行有‘a’，则计数， filter返回RDD，它是一个转换操作
  // count()返回值为 Long，这是一个动作操作
  var numAs = logData.filter(line => line.contains("a")).count()
  var numBs = logData.filter(line => line.contains("b")).count()
  println(s"Lines with a: $numAs, Lines with b: $numBs")
  sc.stop()
}
