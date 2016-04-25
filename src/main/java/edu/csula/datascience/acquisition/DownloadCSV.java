package edu.csula.datascience.acquisition;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class DownloadCSV {

	//Download CSV file over the source
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL(
				"https://data.montgomerycountymd.gov/api/views/4mse-ku6q/rows.csv?accessType=DOWNLOAD");
		downloadCSV(url, 10);
	}

	public static void downloadCSV(URL downloadUrl, int id) {
		String fileName = id + ".csv";
		String path = System.getProperty("/home/dipesh");
		File dir = new File(path + "/download");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir, fileName);
		try {
			FileUtils.copyURLToFile(downloadUrl, file);
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
	}
}
