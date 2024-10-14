package pl.minecodes.mojang;

import java.util.UUID;
import pl.minecodes.response.ProfileStatus;

public class MojangResponse {

  private final UUID uniqueId;
  private final ProfileStatus status;

  public MojangResponse(UUID uniqueId, ProfileStatus status) {
    this.uniqueId = uniqueId;
    this.status = status;
  }

  public UUID getUniqueId() {
    return uniqueId;
  }

  public ProfileStatus getStatus() {
    return status;
  }
}
