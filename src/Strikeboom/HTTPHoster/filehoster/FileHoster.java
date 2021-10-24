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
    //the user's ip address
    public static String ip;
    //all the urls of the files
    List<String> fileURLs = new ArrayList<>();
    public FileHoster(int port) {
        try {
            //these 3 lines are to get the users ip through the site
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
        //add file with path
        fileURLs.add("http://" + ip + ":" + port + filePath);
        //create a new context for the filepath
        server.createContext(filePath,httpExchange -> {
            //give the headers only if there is a header
            if (MimeHeaders.hasExtension(filePath)) {
                if (MimeHeaders.hasMimeType(MimeHeaders.getExtension(filePath))) {
                    httpExchange.getResponseHeaders().add("Content-Type",MimeHeaders.getMimeTypeFromExtension(MimeHeaders.getExtension(filePath)));
                }
            }
            //convert file to byte array
            byte[] bytes = Files.readAllBytes(file.toPath());
            //send over bytes
            httpExchange.sendResponseHeaders(200,bytes.length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(bytes);
            os.close();
            httpExchange.close();
        });
    }
    public void start() {
        //only start if not started yet
        if (!Main.isRunning) {
            server.start();
            Main.isRunning = true;
        }
    }
    public List<String> getFileURLs() {
        return fileURLs;
    }

}
