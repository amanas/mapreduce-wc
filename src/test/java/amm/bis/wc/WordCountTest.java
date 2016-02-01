package amm.bis.wc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import amm.bis.wc.WordCount.Map;
import amm.bis.wc.WordCount.Reduce;

public class WordCountTest {

    // Specification of Mapper
    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

    // Specification of Reduce
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;

    // Specification of MapReduce program
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

    @Before
    public void setUp() {
        Map mapper = new Map();
        Reduce reducer = new Reduce();
        // Setup Mapper
        mapDriver = MapDriver.newMapDriver(mapper);
        // Setup Reduce
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        // Setup MapReduce job
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() {
        // Test Mapper with this input
        mapDriver.withInput(new LongWritable(), new Text("hello hola hello hola hello"));
        // Expect this output
        mapDriver.withOutput(new Text("hello"), new IntWritable(1));
        mapDriver.withOutput(new Text("hola"), new IntWritable(1));
        mapDriver.withOutput(new Text("hello"), new IntWritable(1));
        mapDriver.withOutput(new Text("hola"), new IntWritable(1));
        mapDriver.withOutput(new Text("hello"), new IntWritable(1));

        try {
            // Run Map test with above input and ouput
            mapDriver.runTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReducer() {
        List<IntWritable> helloValues = new ArrayList<IntWritable>();
        helloValues.add(new IntWritable(1));
        helloValues.add(new IntWritable(2));
        List<IntWritable> holaValues = new ArrayList<IntWritable>();
        holaValues.add(new IntWritable(1));
        holaValues.add(new IntWritable(1));
        // Run Reduce with this input
        reduceDriver.withInput(new Text("hello"), helloValues);
        reduceDriver.withInput(new Text("hola"), holaValues);
        // Expect this output
        reduceDriver.withOutput(new Text("hello"), new IntWritable(3));
        reduceDriver.withOutput(new Text("hola"), new IntWritable(2));
        try {
            // Run Reduce test with above input and ouput
            reduceDriver.runTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}