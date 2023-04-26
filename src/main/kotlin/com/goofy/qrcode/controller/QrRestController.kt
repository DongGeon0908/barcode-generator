package com.goofy.qrcode.controller

import com.goofy.qrcode.service.QrService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/qr-code")
class QrRestController(
    private val qrService: QrService
) {
    @GetMapping
    fun get(): ResponseEntity<ByteArray> {
        val resource = qrService.downloadQrCode()

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=qr-code.png")
            .body(resource)
    }
}
