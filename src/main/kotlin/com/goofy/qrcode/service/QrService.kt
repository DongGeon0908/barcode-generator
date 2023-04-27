package com.goofy.qrcode.service

import com.goofy.qrcode.util.QrCodeGenerator
import com.goofy.qrcode.util.QrCodeGenerator.Companion.generateQrCode
import org.springframework.stereotype.Service

@Service
class QrService {
    fun downloadQrCode(): ByteArray {
        return generateQrCode(
            QrCodeGenerator.QrCodeModel(
                content = "www.naver.com",
                width = 400,
                height = 500,
                onColor = 0xFF000000.toInt(),
                offColor = 0xFFFFFFFF.toInt(),
                fileFormat = QrCodeGenerator.FileFormat.PNG
            )
        )
    }
}
