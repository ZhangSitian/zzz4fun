package com.zzz.fun.kafka;

import cn.hutool.json.JSONUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UserProducer {

    private final Properties props = new Properties();

    private final static String SERVERS = "127.0.0.1:9092";

    private final static String TOPIC = "mykafka";

    private UserProducer() {
//        推荐使用这个配置ProducerConfig
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS);//xxx服务器ip
        props.put(ProducerConfig.ACKS_CONFIG, "all");//所有follower都响应了才认为消息提交成功，即"committed"
        props.put(ProducerConfig.RETRIES_CONFIG, 0);//retries = MAX 无限重试，直到你意识到出现了问题:)
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);//producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        //batch.size当批量的数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);//延迟1ms发送，这项设置将通过增加小的延迟来完成--即，不是立即发送一条记录，producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);//producer可以用来缓存数据的内存大小。
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    private void sendAsync(int key, String msg) {
        // 线程安全的
        KafkaProducer producer = new KafkaProducer(props);
        producer.send(new ProducerRecord<>(TOPIC, String.valueOf(key), msg));
        producer.close();
    }

    private Future<RecordMetadata> sendSync(int key, String msg) {
        // 线程安全的
        try(KafkaProducer producer = new KafkaProducer(props)) {
            return producer.send(new ProducerRecord<>(TOPIC, String.valueOf(key), msg));
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UserProducer producerDemo = new UserProducer();
        for (int i = 0; i < 5; i++) {
            String asyncMsg = String.format("async message %s", i);
            producerDemo.sendAsync(i, asyncMsg);
            String syncMsg = String.format("sync message %s", i);
            Future<RecordMetadata> recordMetadataFuture = producerDemo.sendSync(i, syncMsg);
            RecordMetadata recordMetadata = recordMetadataFuture.get();
            System.out.println(JSONUtil.toJsonStr(recordMetadata));
        }
    }
}
