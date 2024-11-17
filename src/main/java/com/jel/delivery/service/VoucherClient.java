package com.jel.delivery.service;

import com.jel.delivery.config.VoucherApiConfig;
import com.jel.delivery.dto.VoucherDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Optional;

@Service
public class VoucherClient {

    private final WebClient webClient;
    private final VoucherApiConfig voucherApiConfig;

    public VoucherClient(WebClient webClient, VoucherApiConfig voucherApiConfig) {
        this.webClient = webClient;
        this.voucherApiConfig = voucherApiConfig;
    }

    public ResponseEntity<VoucherDto> retrieveVoucher(String voucherCode) {
        return webClient.get().uri(uriBuilder
                        -> uriBuilder
                            .scheme(voucherApiConfig.getScheme())
                            .host(voucherApiConfig.getHost())
                            .pathSegment(voucherApiConfig.getPath(), "{voucherCode}")
                            .queryParam("key", voucherApiConfig.getKey())
                        .build(voucherCode))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(VoucherDto.class)
                .retryWhen(Retry.backoff(1, Duration.ofSeconds(5)))
                .timeout(Duration.ofSeconds(30))
                .onErrorResume(throwable -> Mono.just(ResponseEntity
                        .of(Optional.of(VoucherDto.builder().discount(0).build()))))
                .block();
    }

}
