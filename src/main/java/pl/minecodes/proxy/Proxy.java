package pl.minecodes.proxy;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;

public class Proxy {

  private final String address;
  private final int port;
  private final String country;

  private String user;
  private String password;

  public Proxy(String address, int port, String country) {
    this.address = address;
    this.port = port;
    this.country = country;
  }

  public Proxy(String address, int port, String country, String user, String password) {
    this.address = address;
    this.port = port;
    this.country = country;
    this.user = user;
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public int getPort() {
    return port;
  }

  public String getCountry() {
    return country;
  }

  public InetSocketAddress getSocketAddress() {
    return new InetSocketAddress(this.address, this.port);
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  public boolean authenticationNeeded() {
    return user != null && password != null;
  }

  public Authenticator getAuthenticator() {
    if (user == null || password == null) {
      return null;
    }

    return new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password.toCharArray());
      }
    };
  }

  @Override
  public String toString() {
    return "Proxy{" +
        "address='" + address + '\'' +
        ", port=" + port +
        ", country='" + country + '\'' +
        '}';
  }
}
