package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Timer {
	private static int cnt;
	
	@Value("${spring.task.name}")
	private String taskName;
	
	@Scheduled(fixedRateString = "${spring.task.fixedDelay}") // 이전 작업의 시작시각 이후 재시작. 항상 정해진 시간 간격(interval)을 유지하려고 함.
//	@Scheduled(fixedDelayString = "${spring.task.fixedDelay}") // 이전 작업의 종료시각 이후 재시작. 이전 작업의 지연 시간(latency)에 영향을 받아 점점 재실행 시간이 느려질 가능성 있음.
	public void tick() {
		log.info("fixedDelayString {}, {}", taskName, ++cnt);
	}

}
