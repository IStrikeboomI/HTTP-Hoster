package Strikeboom.HTTPHoster.filehoster;

import Strikeboom.HTTPHoster.Main;
import Strikeboom.HTTPHoster.filehoster.mimeheader.MimeHeaders;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileHoster {
    HttpServer server;
    int port;
    public static String ip;
    List<String> fileURLs = new ArrayList<>();
    public FileHoster(int port) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://bot.whatismyipaddress.com/").openStream()));
            ip = reader.readLine().trim();
            reader.close();

            this.port = port;
            server = HttpServer.create(new InetSocketAddress(port),0);
            server.setExecutor(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void host(File file, String filePath) {
        fileURLs.add("http://" + ip + ":" + port + filePath);
        server.createContext(filePath,httpExchange -> {
            System.out.println(MimeHeaders.hasExtension(filePath));
            if (MimeHeaders.hasExtension(filePath)) {
                System.out.println(MimeHeaders.hasMimeType(MimeHeaders.getExtension(filePath)));
                if (MimeHeaders.hasMimeType(MimeHeaders.getExtension(filePath))) {
                    System.out.println(MimeHeaders.getMimeTypeFromExtension(MimeHeaders.getExtension(filePath)));
                    httpExchange.getResponseHeaders().add("Content-Type",MimeHeaders.getMimeTypeFromExtension(MimeHeaders.getExtension(filePath)));
                }
            }
            byte[] bytes = Files.readAllBytes(file.toPath());
            httpExchange.sendResponseHeaders(200,bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
            httpExchange.close();
        });
    }
    public void start() {
        if (!Main.isRunning) {
            server.start();
            Main.isRunning = true;
        }
    }
    public List<String> getFileURLs() {
        return fileURLs;
    }

}
