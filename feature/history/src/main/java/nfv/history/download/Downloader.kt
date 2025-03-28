package nfv.history.download

interface Downloader {
    fun downloadFile(url: String, titleAppendix: String): Long
}