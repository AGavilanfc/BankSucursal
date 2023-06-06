package com.optimissa.training.currencyservice.conversion;

import com.optimissa.training.currencyservice.dto.ConversionResponse;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@Transactional
public class ConversionService {

    private RestTemplate restTemplate;

    public ConversionResponse convertCurrency(String from, String to, Double amount) {
        String url = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to + "&amount=" + amount;

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, getTrustManager(), new SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            restTemplate = new RestTemplate();
            String conversion = restTemplate.getForObject(url, String.class, from, to, amount);
            ConversionResponse conversionResponse = restTemplate.getForObject(url, ConversionResponse.class, from, to, amount);

            return conversionResponse;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            // Manejar la excepción
            return null;
        }
    }

    private TrustManager[] getTrustManager() {
        return new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};
    }
}
