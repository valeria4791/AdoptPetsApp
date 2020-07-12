package group.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConvertPhoto {

	public static byte[] convertPhotoToDB(String urlPhoto) throws IOException {

		FileInputStream fis = null;

		try {
			// Create File from URL
			File file = new File(urlPhoto);

			// Get file data
			fis = new FileInputStream(file);
			int fileLength = (int) file.length();

			// Allocate byte array of right size
			byte[] fileData = new byte[fileLength];

			// read into byte array
			fis.read(fileData, 0, fileLength);

			return fileData;
		} catch (IOException e) {
			throw new IOException("Unable to convert photo to byte array");
		} finally {
			if (fis != null)
				fis.close();
		}
	}

}
