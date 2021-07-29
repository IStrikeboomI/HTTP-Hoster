package Strikeboom.HTTPHoster.filehoster.mimeheader;

import java.util.HashMap;

public class MimeHeaders {
    //First string is file extension
    //Second string is mime type
    private final static HashMap<String,String> MIME_TYPES = new HashMap<>();

    public static void init() {
        MIME_TYPES.put("aac","audio/aac");
        MIME_TYPES.put("abw","application/x-abiword");
        MIME_TYPES.put("arc","application/x-freearc");
        MIME_TYPES.put("avi","video/x-msvideo");
        MIME_TYPES.put("azw","application/vnd.amazon.ebook");
        MIME_TYPES.put("bin","application/octet-stream");
        MIME_TYPES.put("bmp","image/bmp");
        MIME_TYPES.put("bz","application/x-bzip");
        MIME_TYPES.put("bz2","application/x-bzip2");
        MIME_TYPES.put("cda","application/x-cdf");
        MIME_TYPES.put("csh","application/x-csh");
        MIME_TYPES.put("css","text/css");
        MIME_TYPES.put("csv","text/csv");
        MIME_TYPES.put("doc","application/msword");
        MIME_TYPES.put("docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        MIME_TYPES.put("eot","application/vnd.ms-fontobject");
        MIME_TYPES.put("epub","application/epub+zip");
        MIME_TYPES.put("gz","application/gzip");
        MIME_TYPES.put("gif","image/gif");
        MIME_TYPES.put("htm","text/html");
        MIME_TYPES.put("html","text/html");
        MIME_TYPES.put("ico","image/vnd.microsoft.icon");
        MIME_TYPES.put("ics","text/calendar");
        MIME_TYPES.put("jar","application/java-archive");
        MIME_TYPES.put("jpeg","image/jpeg");
        MIME_TYPES.put("jpg","image/jpeg");
        MIME_TYPES.put("js","text/javascript");
        MIME_TYPES.put("json","application/json");
        MIME_TYPES.put("jsonld","application/ld+json");
        MIME_TYPES.put("mid","audio/midi audio/x-midi");
        MIME_TYPES.put("midi","audio/midi audio/x-midi");
        MIME_TYPES.put("mjs","text/javascript");
        MIME_TYPES.put("mov","video/quicktime");
        MIME_TYPES.put("mp3","audio/mpeg");
        MIME_TYPES.put("mp4","video/mp4");
        MIME_TYPES.put("mpeg","video/mpeg");
        MIME_TYPES.put("mpkg","application/vnd.apple.installer+xml");
        MIME_TYPES.put("odp","application/vnd.oasis.opendocument.presentation");
        MIME_TYPES.put("ods","application/vnd.oasis.opendocument.spreadsheet");
        MIME_TYPES.put("odt","application/vnd.oasis.opendocument.text");
        MIME_TYPES.put("oga","audio/ogg");
        MIME_TYPES.put("ogv","video/ogg");
        MIME_TYPES.put("ogx","application/ogg");
        MIME_TYPES.put("opus","audio/opus");
        MIME_TYPES.put("otf","font/otf");
        MIME_TYPES.put("png","image/png");
        MIME_TYPES.put("pdf","application/pdf");
        MIME_TYPES.put("php","application/x-httpd-php");
        MIME_TYPES.put("ppt","application/vnd.ms-powerpoint");
        MIME_TYPES.put("pptx","application/vnd.openxmlformats-officedocument.presentationml.presentation");
        MIME_TYPES.put("rar","application/vnd.rar");
        MIME_TYPES.put("rtf","application/rtf");
        MIME_TYPES.put("sh","application/x-sh");
        MIME_TYPES.put("svg","image/svg+xml");
        MIME_TYPES.put("swf","application/x-shockwave-flash");
        MIME_TYPES.put("tar","application/x-tar");
        MIME_TYPES.put("tif","image/tiff");
        MIME_TYPES.put("tiff","image/tiff");
        MIME_TYPES.put("ts","video/mp2t");
        MIME_TYPES.put("ttf","font/ttf");
        MIME_TYPES.put("txt","text/plain");
        MIME_TYPES.put("vsd","application/vnd.visio");
        MIME_TYPES.put("wav","audio/wav");
        MIME_TYPES.put("weba","audio/webm");
        MIME_TYPES.put("webm","video/webm");
        MIME_TYPES.put("webp","image/webp");
        MIME_TYPES.put("woff","font/woff");
        MIME_TYPES.put("woff2","font/woff2");
        MIME_TYPES.put("xhtml","application/xhtml+xml");
        MIME_TYPES.put("xls","application/vnd.ms-excel");
        MIME_TYPES.put("xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        MIME_TYPES.put("xml","application/xml");
        MIME_TYPES.put("xul","application/vnd.mozilla.xul+xml");
        MIME_TYPES.put("zip","application/zip");
        MIME_TYPES.put("3gp","video/3gpp");
        MIME_TYPES.put("3g2","video/3gpp2");
        MIME_TYPES.put("7z","application/x-7z-compressed");
    }
    public static String getMimeTypeFromExtension(String extension) {
        if (MIME_TYPES.containsKey(extension)) {
            return MIME_TYPES.get(extension);
        }
        return null;
    }
    public static boolean hasMimeType(String extension) {
        return MIME_TYPES.containsKey(extension);
    }
    //substring to get only the file then check if it has a dot
    public static boolean hasExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1).contains(".");
    }
    public static String getExtension(String filePath) {
        final String FILE_NAME = filePath.substring(filePath.lastIndexOf("/") + 1);
        return FILE_NAME.substring(FILE_NAME.lastIndexOf(".") + 1);
    }
}
