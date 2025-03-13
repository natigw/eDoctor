package nfv.home.download

interface Downloader {
    fun downloadFile(url: String, titleAppendix: String): Long
}