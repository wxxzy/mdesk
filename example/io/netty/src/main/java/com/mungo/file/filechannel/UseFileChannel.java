package com.mungo.file.filechannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/1/31 13:54
 */
public class UseFileChannel {
    public static void main(String[] args) {
        try {
            //写
            RandomAccessFile accessFile = new RandomAccessFile("test.txt","rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            String content = "FileChannel test\r\n";
            byteBuffer.put(content.getBytes());
            byteBuffer.flip();
            fileChannel.position(fileChannel.size());
            fileChannel.write(byteBuffer);
            fileChannel.close();

            //读
            accessFile = new RandomAccessFile("test.txt","rw");
            fileChannel = accessFile.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            fileChannel.read(buff);
            buff.flip();
            Charset cs = Charset.defaultCharset();
            System.out.println(cs.decode(buff));
            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
