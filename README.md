# QR CODE

> Qr 생성기

### Dependency

> com.google.zxing을 사용

[zxing](https://github.com/zxing/zxing)

```groovy
implementation("com.google.zxing:core:3.5.1")
implementation("com.google.zxing:javase:3.5.1")
```

### Qr Generator

```kotlin
package com.goofy.qrcode.util

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream

class QrCodeGenerator {
    companion object {
        fun generateQrCode(model: QrCodeModel): ByteArray {
            val outputStream = ByteArrayOutputStream()

            val bitMatrix = QRCodeWriter().encode(model.content, BarcodeFormat.QR_CODE, model.width, model.height)
            val matrixToImageConfig = MatrixToImageConfig(model.onColor, model.offColor)
            MatrixToImageWriter.writeToStream(bitMatrix, model.fileFormat.name, outputStream, matrixToImageConfig)

            return outputStream.toByteArray()
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

```
