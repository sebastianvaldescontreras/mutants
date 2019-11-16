package com.mutants.infrastructure.service;

public interface ISqsService {
	void sendNotificationSQS(String message);
}
