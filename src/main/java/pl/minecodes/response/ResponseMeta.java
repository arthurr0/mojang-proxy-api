package pl.minecodes.response;

public class ResponseMeta {

  private final String proxyCountry;
  private final long mojangResponseTime;

  public ResponseMeta(String proxyCountry, long mojangResponseTime) {
    this.proxyCountry = proxyCountry;
    this.mojangResponseTime = mojangResponseTime;
  }

  public String getProxyCountry() {
    return proxyCountry;
  }

  public long getMojangResponseTime() {
    return mojangResponseTime;
  }
}
