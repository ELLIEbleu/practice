package com;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;


/**
 * @Description
 * @Author ellie
 * @Date 2022/2/11 3:07 下午
 **/
public class ByteBufferServiceTest {

    @Test
    public void byteBufferTest(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("buffer = "+buffer);
        System.out.println("after allocate:"+ Runtime.getRuntime().freeMemory());


        ByteBuffer directBuffer = ByteBuffer.allocate(1024);
        System.out.println("directBuffer = "+ directBuffer);
        System.out.println("after direct allocate:"+ Runtime.getRuntime().freeMemory());

        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println("buffer = "+buffer);


        buffer = ByteBuffer.wrap(bytes,10,5);
        System.out.println("buffer2 = "+ buffer);

        //
        String str = "hello world";
        bytes = str.getBytes();
        buffer = ByteBuffer.wrap(bytes);
        System.out.println("buffer3 = "+buffer);
        int n = ByteBuffer.wrap(bytes).getInt(3);
        System.out.println("n =" + n);


        buffer = ByteBuffer.wrap(bytes,1,5); // limit = length + position
        System.out.println("buffer4 = "+ buffer);

        String s = byteBufferToString(buffer);
        System.out.println("s:"+ s);



        //
        System.out.println("------- Test reset ---------");
        buffer.clear();
        buffer.position(5);
        buffer.mark();
        buffer.position(10);
        System.out.println("before reset:"+ buffer);
        buffer.reset();
        System.out.println("after reset:"+ buffer);

        //
        System.out.println("------ Test rewind ----------");
        buffer.clear();
        buffer.position(10);
        buffer.limit(1);
        System.out.println("before rewind:"+ buffer);
        buffer.rewind();
        System.out.println("after rewind:"+buffer);

        Buffer buffer1 = buffer.flip();
        System.out.println("buffer1: "+ buffer1);

    }

    private String byteBufferToString(ByteBuffer buffer) {
        Charset charset = Charset.forName("utf-8");
        CharBuffer charBuffer = charset.decode(buffer);
        String str = charBuffer.toString();
        return str;
    }

    @Test
    public void byteBufTest(){
        //堆缓存区
        // 两个索引：readerIndex  writerIndex
        ByteBuf heapBuf = Unpooled.buffer(1024);
        if( heapBuf.hasArray()){
            heapBuf.writeBytes("hello,heapBuf".getBytes());
            System.out.println("数组第一个字节在缓冲区中的偏移量："+ heapBuf.arrayOffset());
            System.out.println("缓冲区中的readerIndex:"+ heapBuf.readerIndex());
            System.out.println("缓冲区中的writerIndex:"+ heapBuf.writerIndex());
            System.out.println("缓冲区中的可读字节数:"+heapBuf.readableBytes());

            byte[] array = heapBuf.array();
            System.out.println("array:"+ new String(array));
            for(int i = 0;i < heapBuf.readableBytes();i++){
                System.out.print((char) array[i]);
                if(i==5){
                    array[i] = (int)'.';
                }
            }
            //不会修改readerIndex位置
            System.out.println("\n读取数据后的readerIndex:"+heapBuf.readerIndex());
            //读取缓冲区的数据,查看是否将逗号改成了句号
            while (heapBuf.isReadable()){
                System.out.print((char) heapBuf.readByte());
            }
            System.out.println("\n");

        }


//        //直接缓冲区
//        ByteBuf directBuf = Unpooled.buffer(1024);
//        if( !directBuf.hasArray()){
//            int length = directBuf.readableBytes();
//            byte[] array = new byte[length];
//            directBuf.getBytes(directBuf.readerIndex(),array);
//        }

    }

    @Test
    public void byteBufCopyTest(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf copy = buf.copy(0,15);
        System.out.println("copy: "+copy.toString(utf8));
        buf.setByte(0,'J');
        assert  buf.getByte(0) != copy.getByte(0);
    }

    @Test
    public void byteBufSliceTest(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!",utf8);
        ByteBuf sliced = buf.slice(0,15);
        System.out.println("sliced:"+ sliced.toString(utf8));
        buf.setByte(0,(byte)'K');
        assert buf.getByte(0) == sliced.getByte(0);
    }

    @Test
    public void scatteringReadTest() throws IOException {
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

//        ByteBuffer[] bufferArray = {header,body};
        FileInputStream fis = new FileInputStream("/Users/ELLIE/test.txt");
        ScatteringByteChannel channel = (ScatteringByteChannel) Channels.newChannel(fis);
        channel.read(body);
        System.out.println("header: "+ byteBufferToString(header));
        System.out.println("byteBuffer body:"+ body);
        System.out.println("body: "+ byteBufferToString(body));
    }

    @Test
    public void gatheringWriterTest() throws IOException {
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        String str = "hello world";
        body = ByteBuffer.wrap(str.getBytes());


        ByteBuffer[] bufferArray = {header,body};

        FileOutputStream fos = new FileOutputStream("/Users/ELLIE/test.txt");
        GatheringByteChannel channel = (GatheringByteChannel) Channels.newChannel(fos);
        channel.write(body);
    }

    @Test
    public void fileChannelTest() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/ELLIE/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
//            buf.clear();
            buf.compact();
            bytesRead = inChannel.read(buf);
        }
        System.out.println("\n");
        aFile.close();


    }
}
