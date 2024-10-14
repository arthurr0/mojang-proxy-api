package pl.minecodes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.minecodes.configuration.Configuration;
import pl.minecodes.controller.ProfileRouteController;
import pl.minecodes.mojang.MojangService;
import pl.minecodes.proxy.ProxyService;
import spark.Spark;

public class ProxyApplication {

  public static final Gson GSON = new GsonBuilder()
      .setPrettyPrinting()
      .disableHtmlEscaping()
      .create();

  private final Logger logger = LoggerFactory.getLogger(ProxyApplication.class);

  public void run() {
    Configuration configuration = new Configuration();
    MojangService mojangService = new MojangService();

    ProxyService proxyService = new ProxyService(configuration);

    Spark.port(6969);

    Spark.get("/profile/:username", new ProfileRouteController(this.logger, proxyService, mojangService));
  }

}
