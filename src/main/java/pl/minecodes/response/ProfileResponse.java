package pl.minecodes.response;

import pl.minecodes.mojang.MojangResponse;

public class ProfileResponse {

  private final String username;
  private final MojangResponse response;
  private final ResponseMeta meta;

  public ProfileResponse(String username, MojangResponse response, ResponseMeta meta) {
    this.username = username;
    this.response = response;
    this.meta = meta;
  }

  public String getUsername() {
    return username;
  }

  public MojangResponse getResponse() {
    return response;
  }

  public ResponseMeta getMeta() {
    return meta;
  }
}
