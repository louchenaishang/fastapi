package personal.louchen.fastapi.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by louchen on 16/8/22.
 */
public class JettyServer {
    private static final Integer HTTP_PORT = 8080;

    private static final String PATH_WEBXML = "./fastapi-server/src/main/webapp/WEB-INF/web.xml";

    private static final String PATH_WEBCONTENT = "./fastapi-server/src/main/webapp";

    private static final String PATH_CONTEXT = "/";

    private static void start(){
        try {
            Server server = new Server(HTTP_PORT);
            WebAppContext context = new WebAppContext();
            context.setDescriptor(PATH_WEBXML);
            context.setResourceBase(PATH_WEBCONTENT);
            context.setContextPath(PATH_CONTEXT);
            context.setParentLoaderPriority(true);
            server.setHandler(context);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}
