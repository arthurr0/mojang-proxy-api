package pl.minecodes.configuration;

import java.util.List;
import pl.minecodes.proxy.Proxy;

public class Configuration {

  public List<Proxy> proxies = List.of(
      new Proxy("161.123.152.115", 6360, "Portugal"),
      new Proxy("154.36.110.199", 6853, "Germany")
  );
}
