package com.ts.photoapp.accountmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppApiAccountMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppApiAccountMgmtApplication.class, args);
	}

}
