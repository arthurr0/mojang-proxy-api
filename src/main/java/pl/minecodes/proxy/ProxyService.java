package pl.minecodes.proxy;

import java.util.Random;
import pl.minecodes.configuration.Configuration;

public class ProxyService {

  private final Random random = new Random();
  private final Configuration configuration;

  public ProxyService(Configuration configuration) {
    this.configuration = configuration;
  }

  public Proxy pickProxy() {
    return this.configuration.proxies.get(random.nextInt(this.configuration.proxies.size()));
  }
}
