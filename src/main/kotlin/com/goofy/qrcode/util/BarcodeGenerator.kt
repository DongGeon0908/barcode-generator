package com.goofy.qrcode.util

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream

class BarcodeGenerator {
    companion object {
        private val logger = mu.KotlinLogging.logger {}

        fun generateBarCode(barcode: BarcodeModel): ByteArray {
            return runCatching {
                val outputStream = ByteArrayOutputStream()

                val bitMatrix = QRCodeWriter().encode(
                    barcode.content,
                    barcode.type,
                    barcode.width,
                    barcode.height
                )
                val matrixToImageConfig = MatrixToImageConfig(barcode.onColor, barcode.offColor)

                MatrixToImageWriter.writeToStream(
                    bitMatrix,
                    barcode.fileFormat.name,
                    outputStream,
                    matrixToImageConfig
                )

                outputStream.toByteArray()
            }.getOrElse {
                logger.error { "error > ${it.message}" }
                throw BarcodeGeneratorException()
            }
        }
    }

    data class BarcodeModel(
        /** Barcode Type*/
        val type: BarcodeFormat,
        /** BarCode에 들어갈 데이터 */
        val content: String,
        /** BarCode 이미지 너비 */
        val width: Int,
        /** BarCode 이미지 높이 */
        val height: Int,
        /** BarCode 데이터 색상 */
        val onColor: Int,
        /** BarCode 데이터 제외 색상 */
        val offColor: Int,
        /** Output File Format*/
        val fileFormat: FileFormat
    )

    class BarcodeGeneratorException : RuntimeException()

    enum class FileFormat {
        JPEG, PNG
    }
}
