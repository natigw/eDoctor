package nfv.history.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.webkit.MimeTypeMap
import androidx.core.net.toUri

class AndroidDownloader(
    context: Context
) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String, titleAppendix: String): Long {
        val uri = url.toUri()

        val fileName = uri.lastPathSegment ?: "downloaded_file"
        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(url)
        val mimeType =
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.lowercase()) ?: "*/*"

        val request = DownloadManager.Request(uri)
            .setMimeType(mimeType)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("eDoctor - test result - $titleAppendix")
            .setDescription("Downloading test result...")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        return downloadManager.enqueue(request)
    }
}