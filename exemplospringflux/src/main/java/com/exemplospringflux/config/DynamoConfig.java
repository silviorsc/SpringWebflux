package com.exemplospringflux.config;

//Comunicação com o AWS
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

//Client DynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

//Informa pro SpringData que usaremos o dynamodb
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

//Importa a anotação utilizada para recuperar as informaçoes do arquivo de configuracao
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {
  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDBEndpoint;

  @Value("${aws_access_key_id}")
  private String amazonAWSAccessKey;

  @Value("${aws_secret_access_key}")
  private String amazonAWSSecretKey;

  /**
   *@Bean das instancias para a chamado AWS
   *
   */

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    AmazonDynamoDB amazonDynamoDB
      = new AmazonDynamoDBClient(amazonAWSCredentials());

    if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
      amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
    }

    return amazonDynamoDB;
  }

  @Bean
  public AWSCredentials amazonAWSCredentials() {
    return new BasicAWSCredentials(
      amazonAWSAccessKey, amazonAWSSecretKey);
  }

}
