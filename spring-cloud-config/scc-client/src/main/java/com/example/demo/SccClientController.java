package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 실시간 반영을 위한 어노테이션
public class SccClientController {
	@Value("${server.type.name}") // Github의 설정 값
	private String serverTypeName;

	@GetMapping("/serverTypeName")
	public String getServerTypeName() {
		return serverTypeName;
	}
}