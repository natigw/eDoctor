package nfv.home.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    context: Context
): Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String, titleAppendix: String): Long {
        val request = DownloadManager.Request(url.toUri())
//            .setMimeType("application/pdf")
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
//            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("eDoctor - test result - $titleAppendix")
            .setDescription("Downloading test result...")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "eDoctor.jpg")//"eDoctor.pdf")

        return downloadManager.enqueue(request)
    }
}