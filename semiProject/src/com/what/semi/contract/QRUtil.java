package com.what.semi.contract;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRUtil {

	public static void makeQR(String url, String file_path, String file_name) {
		try {
			File file = null;

			file_path+="images\\QRcodes\\";
			file = new File(file_path);
			if (!file.exists()) {
				file.mkdirs();
			}
			QRCodeWriter writer = new QRCodeWriter();
			url = new String(url.getBytes("UTF-8"), "ISO-8859-1");
			BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, 200, 200);
			// QR코드 색상
			int qrColor = 0xff404040;
			MatrixToImageConfig config = new MatrixToImageConfig(qrColor, 0xFFFFFFFF);
			BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix, config);
			boolean result = ImageIO.write(qrImage, "png", new File(file_path+file_name));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}