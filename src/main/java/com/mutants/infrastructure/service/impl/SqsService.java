package com.mutants.infrastructure.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.mutants.infrastructure.service.ISqsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SqsService implements ISqsService {

	@Value("${cloud.aws.region.static}")
	public String region;

	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String awsSecretKey;

	@Value("${cloud.aws.end-point.uri}")
	private String sqsEndPoint;

	@Value("${cloud.aws.sqsname}")
	private String sqsName;

	public QueueMessagingTemplate queueMessagingTemplate() {
		return new QueueMessagingTemplate(amazonSQSAsync());
	}
	
	public AmazonSQSAsync amazonSQSAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}
	
	@Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsync());
        factory.setAutoStartup(true);
        factory.setMaxNumberOfMessages(10);
        factory.setWaitTimeOut(4);
        return factory;
    }

	@Override
	public void sendNotificationSQS(String notification) {
		QueueMessagingTemplate queueMessagingTemplate = queueMessagingTemplate();
		Map<String, Object> headers = new HashMap<>();
		try {
			queueMessagingTemplate.convertAndSend(sqsName, notification,
					headers);
		} catch (Exception e) {
			log.error("Error Notification. ");
		}
	}
}
