package com.jel.delivery.service;

import com.jel.delivery.config.VoucherApiConfig;
import com.jel.delivery.dto.VoucherDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
public class VoucherClient {

    private final WebClient webClient;
    private final VoucherApiConfig voucherApiConfig;

    public VoucherClient(WebClient webClient, VoucherApiConfig voucherApiConfig) {
        this.webClient = webClient;
        this.voucherApiConfig = voucherApiConfig;
    }

    public VoucherDto retrieveVoucher(String voucherCode) {
        return this.webClient.get().uri(uriBuilder
                        -> uriBuilder
                            .scheme(voucherApiConfig.getScheme())
                            .host(voucherApiConfig.getHost())
                            .pathSegment(voucherApiConfig.getPath(), "{voucherCode}")
                            .queryParam("key", voucherApiConfig.getKey())
                        .build(voucherCode))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(VoucherDto.class)
                .onErrorReturn(VoucherDto.builder().discount(0).expiry(LocalDate.now()).build())
                .block();
    }

}
