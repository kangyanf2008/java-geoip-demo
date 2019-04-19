package demo;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class Main {
	public static void main(String[] args) throws GeoIp2Exception {
		DatabaseReader dr;
		try {
			dr = new DatabaseReader.Builder(new File("src" + File.separator
					+ "demo" + File.separator + "GeoIP2-City.mmdb")).build();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						dr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			InetAddress ipAddress = InetAddress.getByName("124.127.205.82");
			CityResponse cityResponse = dr.city(ipAddress);
			System.out.println(cityResponse.getCity().getName());
			System.out.println(cityResponse.getCity().getNames().get("zh-CN"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
