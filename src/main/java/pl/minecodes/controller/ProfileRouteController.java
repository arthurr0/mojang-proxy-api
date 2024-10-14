package pl.minecodes.controller;

import org.slf4j.Logger;
import pl.minecodes.ProxyApplication;
import pl.minecodes.mojang.MojangResponse;
import pl.minecodes.mojang.MojangService;
import pl.minecodes.proxy.Proxy;
import pl.minecodes.proxy.ProxyService;
import pl.minecodes.response.ProfileResponse;
import pl.minecodes.response.ResponseMeta;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProfileRouteController implements Route {

  private final Logger logger;
  private final ProxyService proxyService;
  private final MojangService mojangService;

  public ProfileRouteController(Logger logger, ProxyService proxyService, MojangService mojangService) {
    this.logger = logger;
    this.proxyService = proxyService;
    this.mojangService = mojangService;
  }

  @Override
  public Object handle(Request request, Response response) {
    long start = System.currentTimeMillis();

    String username = request.params("username");
    Proxy proxy = this.proxyService.pickProxy();

    MojangResponse mojangResponse = this.mojangService.premiumCheck(username, proxy);

    long responseTime = System.currentTimeMillis() - start;

    this.logger.info("Check %s with proxy %s in %s".formatted(username, proxy.getCountry(), responseTime));

    ResponseMeta meta = new ResponseMeta(proxy.getCountry(), responseTime);

    response.header("Content-Type", "application/json");
    return ProxyApplication.GSON.toJson(new ProfileResponse(username, mojangResponse, meta));
  }
}
