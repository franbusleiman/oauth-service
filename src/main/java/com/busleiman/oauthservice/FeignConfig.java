package com.busleiman.oauthservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {


    @Bean
    public ErrorDecoder beanErrorDecoder(){
        return new CustomErrorDecoder();
    }


    public static class CustomErrorDecoder implements ErrorDecoder{
        @Override
        public Exception decode(String s, Response response) {
            return new RuntimeException(extractFeignMessage(response.body().toString()));
        }

        private String extractFeignMessage(String ex) {
            try {
                // Parsear el JSON del contenido de Feign (si existe)
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(ex);
                return jsonNode.has("message") ? jsonNode.get("message").asText() : ex;
            } catch (Exception e) {
                return ex;
            }
        }
    }
}
