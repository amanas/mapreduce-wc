Mapreduce word count example
===========================================================
A minimal word count example with hadoop mapreduce java API

Before
------
Ensure [el-quijote.txt](https://github.com/gclaramunt/el-quijote-spark/blob/master/el-quijote.txt) dataset is in your hadoop user folder 

Package
-------
mvn clean package

Run (from a cluster node)
-------------------------
```bash
hadoop jar mapreduce-wc.jar amm.bis.wc.WordCount \
-libjars /opt/cloudera/parcels/CDH/jars/ \
el-quijote.txt el-quijote-wc
```
