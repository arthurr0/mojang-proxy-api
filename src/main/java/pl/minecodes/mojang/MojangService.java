package pl.minecodes.mojang;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Builder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.UUID;
import pl.minecodes.proxy.Proxy;
import pl.minecodes.response.ProfileStatus;
import pl.minecodes.util.UniqueIdUtil;

public class MojangService {

  public MojangResponse premiumCheck(String username, Proxy proxy) {
    Builder builder = HttpClient.newBuilder().proxy(ProxySelector.of(proxy.getSocketAddress()));

    if (proxy.authenticationNeeded()) {
      builder.authenticator(proxy.getAuthenticator());
    }

    HttpClient httpClient = builder.build();

    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://api.mojang.com/users/profiles/minecraft/" + username))
        .headers(
            "User-Agent",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11"
        )
        .build();

    try {
      HttpResponse<String> response = httpClient.send(
          request,
          BodyHandlers.ofString()
      );

      if (response.statusCode() == 200) {
        JsonObject mojangResponseObject = JsonParser.parseString(response.body()).getAsJsonObject();
        UUID uniqueId = UniqueIdUtil.fromString(mojangResponseObject.get("id").getAsString());

        return new MojangResponse(uniqueId, ProfileStatus.PAID);
      } else if (response.statusCode() == 204 || response.statusCode() == 404) {
        UUID uniqueId = UniqueIdUtil.generateOfflineUniqueId(username);

        return new MojangResponse(uniqueId, ProfileStatus.CRACKED);
      } else {
        throw new RuntimeException("Unexpected status code.");
      }
    } catch (Exception exception) {
      throw new RuntimeException("Failed connection.", exception);
    }
  }
}
