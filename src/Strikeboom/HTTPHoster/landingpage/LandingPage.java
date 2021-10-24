package Strikeboom.HTTPHoster.landingpage;

import java.nio.charset.StandardCharsets;

public class LandingPage {
    private final StringBuilder HTMLInformation = new StringBuilder();
    public void startWriting() {
        //html 5
        HTMLInformation.append("<!DOCTYPE HTML>");
        //html start tag
        HTMLInformation.append("<html>");
        //head tag all the metadata
        HTMLInformation.append("<head>");
        //give title of file directories
        HTMLInformation.append("<title>");
        HTMLInformation.append("File Directories");
        HTMLInformation.append("</title>");
        HTMLInformation.append("</head>");
        //visible data goes into the body
        HTMLInformation.append("<body>");
        //header 1 tag
        HTMLInformation.append("<h1>");
        HTMLInformation.append("File Directories:");
        HTMLInformation.append("</h1>");
        //unordered list of all the urls
        HTMLInformation.append("<ul>");
    }
    public void stopWriting() {
        //close all the currently opened tags
        HTMLInformation.append("</ul>");
        HTMLInformation.append("</body>");
        HTMLInformation.append("</html>");
    }
    //creates an <a> tag inside the list
    public void addATag(String url) {
        HTMLInformation.append("<li>");
        //create a tag for url
        //_blank for new tab
        HTMLInformation.append("<a href=\"").append(url).append("\" target=\"_blank\">").append(url).append("</a>");
        HTMLInformation.append("</li>");
    }
    public byte[] toByteArray() {
        return HTMLInformation.toString().getBytes(StandardCharsets.UTF_8);
    }
}
