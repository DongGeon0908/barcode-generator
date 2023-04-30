package com.goofy.qrcode.service

import com.goofy.qrcode.util.BarcodeGenerator
import com.goofy.qrcode.util.BarcodeGenerator.Companion.generateBarCode
import com.google.zxing.BarcodeFormat
import org.springframework.stereotype.Service

@Service
class QrService {
    fun downloadQrCode(): ByteArray {
        return generateBarCode(
            BarcodeGenerator.BarcodeModel(
                type = BarcodeFormat.QR_CODE,
                content = "www.naver.com",
                width = 400,
                height = 500,
                onColor = 0xFF000000.toInt(),
                offColor = 0xFFFFFFFF.toInt(),
                fileFormat = BarcodeGenerator.FileFormat.PNG
            )
        )
    }
}
