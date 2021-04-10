package Strikeboom.HTTPHoster.filehoster;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class FileHoster {
    HttpServer server;
    int port;
    public static String ip;
    public FileHoster(int port) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://bot.whatismyipaddress.com/").openStream()));
            ip = reader.readLine().trim();
            reader.close();

            this.port = port;
            server = HttpServer.create(new InetSocketAddress(port),0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void host(File file, String filePath) {
        System.out.println("http://" + ip + ":" + port + filePath);
        server.createContext(filePath,httpExchange -> {
            byte[] bytes = Files.readAllBytes(file.toPath());
            httpExchange.sendResponseHeaders(200,bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
        });
    }
    public void start() {
        server.setExecutor(null);
        server.start();
    }
}
