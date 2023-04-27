package com.goofy.qrcode.controller

import com.goofy.qrcode.extension.wrapFileResponse
import com.goofy.qrcode.service.QrService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/qr-code")
class QrRestController(
    private val qrService: QrService
) {
    @GetMapping
    fun download(
        @RequestParam filename: String
    ) = qrService.downloadQrCode().wrapFileResponse(filename)
}
