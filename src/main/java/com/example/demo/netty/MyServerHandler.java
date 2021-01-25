package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class MyServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private HttpRequest request;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            System.out.println("=================================================");
            System.out.println(msg.toString());
            System.out.println("=================================================");
        }
        if (msg instanceof HttpContent){
            System.out.println("=================================================");
            System.out.println(msg.toString());

            System.out.println("=================================================");
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            String str;
            if(buf.hasArray()) { // 处理堆缓冲区
                str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
            } else { // 处理直接缓冲区以及复合缓冲区
                byte[] bytes = new byte[buf.readableBytes()];
                buf.getBytes(buf.readerIndex(), bytes);
                str = new String(bytes, 0, buf.readableBytes());
            }
            System.out.println(str);
            System.out.println("=================================================");
        }
    }


}
