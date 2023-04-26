package com.goofy.qrcode.service

import com.goofy.qrcode.util.QrCodeUtil
import com.goofy.qrcode.util.QrCodeUtil.Companion.generateQrCode
import org.springframework.stereotype.Service

@Service
class QrService {
    fun downloadQrCode(): ByteArray {
        return generateQrCode(
            QrCodeUtil.QrCodeModel(
                content = "www.naver.com",
                width = 400,
                height = 500,
                onColor = 0xFF000000.toInt(),
                offColor = 0xFFFFFFFF.toInt()
            )
        )
    }
}
