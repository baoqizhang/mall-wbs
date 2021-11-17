package com.thoughtworks.mall.infrastructure.constants;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Objects;

public class SnowflakeIdGenerator {

   // 64BIT
   // ID结构及算法
   //  0  00000000000000000000000000000000000000000   0000000000   000000000000
   // [1][              Timestamp(41)              ][WorkerId(10)][Sequence(12)]
   //
   // 算法实现：
   // 1、在该算法中 第一位不使用，设定为1
   // 2、将当前时间戳减去给定的起始时间戳，然后移位到[Timestamp]处
   // 3、计算WorkerId，然后将其移位到[WorkerId]处。WorkerId一般用MAC地址计算，或是在集群中手动分配编号。
   // 4、计算Sequence，然后将其移位到[Sequence]处。Sequence为毫秒内的序号，以此递增，如果溢出就阻塞等到下一秒从0开始计数。

   // 给定的起始时间
   private static final long BEGIN_TIMESTAMP = Instant.parse("2021-11-17T00:00:00.00Z").toEpochMilli();

   // WorkerId长度
   private static final int WORKER_ID_BITS = 10;

   // Sequence长度
   private static final int SEQUENCE_BITS = 12;

   private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);
   private static final long MAX_WORKER_ID_MASK = ~(-1L << WORKER_ID_BITS);

   private static final int TIMESTAMP_LEFT_SHIFT = WORKER_ID_BITS + SEQUENCE_BITS;
   private static final int WORKER_ID_LEFT_SHIFT = SEQUENCE_BITS;

   private static final long DEFAULT_WORKER_ID = createWorkerId();
   private volatile long sequence = 0L;
   private volatile long lastTimeMillis = -1L;

   private SnowflakeIdGenerator() {
   }

   private synchronized long generate() {
      // 获取当前时间
      var currentTimeMillis = System.currentTimeMillis();
      if (currentTimeMillis < lastTimeMillis) {
         throw new IllegalStateException("Clock moved backwards.");
      }

      // 如果在同一时间内，则计算序号
      // 如果计算出来的需要已经超出范围了，则阻塞到下一个时间片执行。
      if (currentTimeMillis != lastTimeMillis || ++sequence > MAX_SEQUENCE) {
         if (currentTimeMillis == lastTimeMillis) {

            // 获取下一个时间片
            var tailTimeMillis = System.currentTimeMillis();
            while (tailTimeMillis <= currentTimeMillis) {
               tailTimeMillis = System.currentTimeMillis();
            }
            currentTimeMillis = tailTimeMillis;
         }

         // 重置
         sequence = 0L;
      }

      lastTimeMillis = currentTimeMillis;
      var delta = currentTimeMillis - BEGIN_TIMESTAMP;
      return delta << TIMESTAMP_LEFT_SHIFT | DEFAULT_WORKER_ID << WORKER_ID_LEFT_SHIFT | sequence;
   }


   private static long createWorkerId() {
      long workerId;
      try {
         var sb = new StringBuilder();
         var networkInterfaces = NetworkInterface.getNetworkInterfaces();
         while (networkInterfaces.hasMoreElements()) {
            var networkInterface = networkInterfaces.nextElement();
            var mac = networkInterface.getHardwareAddress();
            if (Objects.isNull(mac)) {
               continue;
            }
            for (byte temp : mac) {
               sb.append(String.format("%02X", temp));
            }
         }
         workerId = sb.toString().hashCode();
         workerId = workerId & MAX_WORKER_ID_MASK;
      } catch (Exception e) {
         workerId = new SecureRandom().nextInt();
      }
      return workerId;
   }

   public static long next() {
      return Creator.INSTANCE.generate();
   }

   private static class Creator {
      private static final SnowflakeIdGenerator INSTANCE = new SnowflakeIdGenerator();
   }
}
