package com.example.skeleton.global.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

    // reference : https://bbubbush.tistory.com/46

    @Value("${jasypt.encryptor.algorithm}")
    private String algorithm;
    @Value("${jasypt.encryptor.pool-size}")
    private int poolSize;
    @Value("${jasypt.encryptor.string-output-type}")
    private String stringOutputType;

    @Bean
    public StringEncryptor jasyptStringEncryptor() {

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(poolSize);
        encryptor.setAlgorithm(algorithm);
        encryptor.setPassword(getJasyptEncryptorPropertiesInTxt()[1]);
        encryptor.setStringOutputType(stringOutputType);
        encryptor.setKeyObtentionIterations(Integer.parseInt(getJasyptEncryptorPropertiesInTxt()[0]));

        return encryptor;
    }

    private String[] getJasyptEncryptorPropertiesInTxt() {

        try {
            ClassPathResource resource = new ClassPathResource("enigma23.txt");

            return Files.readAllLines(Paths.get(resource.getURI()))
                    .stream().limit(2)
                    .toArray(String[]::new);

        } catch (IOException e) {
            throw new RuntimeException("Not found Jasypt password file.");
        }
    }
}