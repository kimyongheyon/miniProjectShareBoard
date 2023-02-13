package com.sharBoard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sharBoard.**.mapper")
public class SharBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharBoardApplication.class, args);
	}

}
