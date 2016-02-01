Mapreduce word count example
============================
A minimal word count example with hadoop mapreduce java API

Before
------
Ensure [el-quijote.txt](https://github.com/gclaramunt/el-quijote-spark/blob/master/el-quijote.txt) dataset is in your hadoop user folder 

Package
-------
```sh
mvn clean package
```

Run (from a cluster node)
-------------------------
```sh
hadoop jar mapreduce-wc.jar amm.bis.wc.WordCount \
-libjars /home/amanas/mapreduce-wc.jar,/opt/cloudera/parcels/CDH/jars/ \
el-quijote.txt el-quijote-wc-java
```

Resources
---------
Packaged jar [(copy and try)](https://github.com/amanas/mapreduce-wc/blob/master/jar/mapreduce-wc.jar)