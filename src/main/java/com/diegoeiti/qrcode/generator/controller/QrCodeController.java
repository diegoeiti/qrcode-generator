package com.diegoeiti.qrcode.generator.controller;


import com.diegoeiti.qrcode.generator.dto.QrCodeGenerateRequest;
import com.diegoeiti.qrcode.generator.dto.QrCodeGenerateResponse;
import com.diegoeiti.qrcode.generator.service.QrCodeGeneratorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeService, QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;

    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request){

        try {
            QrCodeGenerateResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch(Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }
}
