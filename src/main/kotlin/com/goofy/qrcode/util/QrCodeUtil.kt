package com.goofy.qrcode.util

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream

class QrCodeUtil {
    companion object {
        fun generateQrCode(model: QrCodeModel): ByteArray {
            val outputStream = ByteArrayOutputStream()

            val bitMatrix = QRCodeWriter().encode(model.content, BarcodeFormat.QR_CODE, model.width, model.height)
            val matrixToImageConfig = MatrixToImageConfig(model.onColor, model.offColor)
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, matrixToImageConfig)

            return outputStream.toByteArray()
        }
    }

    data class QrCodeModel(
        val content: String,
        val width: Int,
        val height: Int,
        val onColor: Int,
        val offColor: Int,
    )
}
