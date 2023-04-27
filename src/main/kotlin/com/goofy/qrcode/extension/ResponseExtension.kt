package com.goofy.qrcode.extension

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

fun ByteArray.wrapFileResponse(filename: String): ResponseEntity<ByteArray> {
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$filename")
        .body(this)
}
