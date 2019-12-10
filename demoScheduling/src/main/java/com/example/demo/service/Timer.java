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
	
	@Scheduled(fixedRateString = "${spring.task.fixedDelay}") // ���� �۾��� ���۽ð� ���� �����. �׻� ������ �ð� ����(interval)�� �����Ϸ��� ��.
//	@Scheduled(fixedDelayString = "${spring.task.fixedDelay}") // ���� �۾��� ����ð� ���� �����. ���� �۾��� ���� �ð�(latency)�� ������ �޾� ���� ����� �ð��� ������ ���ɼ� ����.
	public void tick() {
		log.info("fixedDelayString {}, {}", taskName, ++cnt);
	}

}
