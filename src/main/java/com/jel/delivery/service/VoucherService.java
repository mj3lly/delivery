package com.jel.delivery.service;

import com.jel.delivery.dto.VoucherDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VoucherService {

    private final VoucherClient voucherClient;

    public VoucherService(VoucherClient voucherClient) {
        this.voucherClient = voucherClient;
    }

    public VoucherDto retrieveVoucher(String voucherCode) {
        return this.voucherClient.retrieveVoucher(voucherCode).getBody();
    }

}
