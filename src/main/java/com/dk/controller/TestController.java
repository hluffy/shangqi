package com.dk.controller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.netty.ChannelServer;
import com.dk.result.Result;




@Controller
@RequestMapping("test")
public class TestController {
	
	@RequestMapping("write.ll")
	@ResponseBody
	public Result write(){
		Result result = new Result();
		System.out.println(ChannelServer.class);
		Map<String,SocketChannel> map = ChannelServer.getChannels();
		SocketChannel ctx = null;
		ByteBuf resp = Unpooled.copiedBuffer("ssdssdsf".getBytes());
		for(String key:map.keySet()){
			ctx = map.get(key);
			ctx.writeAndFlush(resp);
		}
		while(ChannelServer.getString()==null){
			
		}
		result.setMessage(ChannelServer.getString());
		ChannelServer.setString(null);
		
		return result;
	}

}
