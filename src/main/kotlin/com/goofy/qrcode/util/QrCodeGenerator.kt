package com.goofy.qrcode.util

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream

class QrCodeGenerator {
    companion object {
        private val logger = mu.KotlinLogging.logger {}

        fun generateQrCode(model: QrCodeModel): ByteArray {
            return runCatching {
                val outputStream = ByteArrayOutputStream()

                val bitMatrix = QRCodeWriter().encode(model.content, BarcodeFormat.QR_CODE, model.width, model.height)
                val matrixToImageConfig = MatrixToImageConfig(model.onColor, model.offColor)

                MatrixToImageWriter.writeToStream(bitMatrix, model.fileFormat.name, outputStream, matrixToImageConfig)

                outputStream.toByteArray()
            }.getOrElse {
                logger.error { "error > ${it.message}" }
                throw RuntimeException("generate qr code fail")
            }
        }
    }

    data class QrCodeModel(
        /** Qr Code에 들어갈 데이터 */
        val content: String,
        /** Qr Code 이미지 너비 */
        val width: Int,
        /** Qr Code 이미지 높이 */
        val height: Int,
        /** Qr Code 데이터 색상 */
        val onColor: Int,
        /** Qr Code 데이터 제외 색상 */
        val offColor: Int,
        /** Output File Format*/
        val fileFormat: FileFormat
    )

    enum class FileFormat {
        JPEG, PNG
    }
}
