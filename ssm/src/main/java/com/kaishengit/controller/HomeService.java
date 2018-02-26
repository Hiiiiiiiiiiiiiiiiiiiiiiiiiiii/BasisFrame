package com.kaishengit.controller;

import com.kaishengit.entity.Product;
import com.kaishengit.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;


import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.ReentrantLock;


@Controller
public class HomeService {
    @Autowired
    ProductService productService;
    @Autowired
    JedisPool jedisPool;

    private RedisTemplate<String,Product> redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Product.class));
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @PostMapping("/get")
    public String returnHome(int number,Model model){
        Product product = productService.findById(number);
        if(product!=null){
            Jedis jedis = jedisPool.getResource();
            jedis.auth("rootroot");
            jedis.set(product.getId().toString(),product.getProductName());
            jedis.close();
        }
        model.addAttribute("product",product);
        return "index";
    }
//    @PostMapping("/get")
//    public String returnHome(int number,Model model){
//        Product product = productService.findById(number);
//        if(product!=null){
//            redisTemplate.opsForValue().set("user:1",product);
//            Product user = redisTemplate.opsForValue().get("user:1");
//            System.out.println(user);
//        }
//        model.addAttribute("product",product);
//        return "index";
//    }
}
