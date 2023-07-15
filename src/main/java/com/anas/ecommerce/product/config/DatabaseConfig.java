package com.anas.ecommerce.product.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Value("${aws.rds.secret.region}")
    private String awsRegion;

    @Value("${aws.secret.manager.secret.name}")
    private String secretName;

    @Value("${spring.datasource.database.name}")
    private String database;


    @Bean
    public DataSource dataSource() {
        // Create a Secrets Manager client
        try (SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.of(awsRegion))
                .build()) {
            GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            GetSecretValueResponse getSecretValueResponse;

            try {
                getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
                String secretString = getSecretValueResponse.secretString();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> secretMap = objectMapper.readValue(secretString, Map.class);
                String username = String.valueOf(secretMap.get("username"));
                String password = String.valueOf(secretMap.get("password"));
                String host = String.valueOf(secretMap.get("host"));
                Integer port = Integer.valueOf(secretMap.get("port").toString());

                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
                dataSource.setUsername(username);
                dataSource.setPassword(password);
                System.out.println(dataSource.getConnection());
                return dataSource;
            } catch (IOException e) {
                // Handle exception
            } catch (SQLException e) {
                // Handle exception
                e.printStackTrace();
            } catch (Exception e) {
                // For a list of exceptions thrown, see
                // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
                throw e;
            }
        }
        return null;
    }

}
