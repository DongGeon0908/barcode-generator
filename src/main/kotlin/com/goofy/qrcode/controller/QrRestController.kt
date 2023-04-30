package com.goofy.qrcode.controller

import com.goofy.qrcode.extension.wrapFileResponse
import com.goofy.qrcode.service.QrService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/qr-codes")
class QrRestController(
    private val qrService: QrService
) {
    @PostMapping
    fun download(
        @RequestParam filename: String
    ) = qrService.downloadQrCode().wrapFileResponse(filename)
}
