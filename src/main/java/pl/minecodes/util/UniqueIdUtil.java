package pl.minecodes.util;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class UniqueIdUtil {

  private UniqueIdUtil() {}

  public static UUID fromString(String string) {
    return UUID.fromString(string.replaceAll("(.{8})(.{4})(.{4})(.{4})(.+)", "$1-$2-$3-$4-$5"));
  }

  public static boolean isPremiumUniqueId(String username, UUID uniqueId) {
    return !uniqueId.equals(UniqueIdUtil.generateOfflineUniqueId(username));
  }

  public static UUID generateOfflineUniqueId(String name) {
    return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
  }
}
