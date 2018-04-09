package com.skyfaith.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.File;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class BarCodeUtils {
    public static WritableImage encode(String contents, int width, int height) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        try {
            // 原为13位Long型数字（BarcodeFormat.EAN_13），现为128位
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, codeWidth, height, null);
            BufferedImage bf = MatrixToImageWriter.toBufferedImage(bitMatrix);
            WritableImage wr = null;
            if (bf != null) {
                wr = new WritableImage(bf.getWidth(), bf.getHeight());
                PixelWriter pw = wr.getPixelWriter();
                for (int x = 0; x < bf.getWidth(); x++) {
                    for (int y = 0; y < bf.getHeight(); y++) {
                        pw.setArgb(x, y, bf.getRGB(x, y));
                    }
                }
            }

            return wr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 条形码编码
     * <p>
     * String imgPath = "target/zxing.png";
     * String contents = "6923450657713";
     * int width = 105, height = 50;
     * ZxingHelper.encode(contents, width, height, imgPath);
     *
     * @param contents 内容
     * @param width    宽度
     * @param height   高度
     * @param imgPath  生成图片路径
     */
    public static void encode(String contents, int width, int height, String imgPath) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.CODE_128, codeWidth, height, null);

            MatrixToImageWriter
                    .writeToPath(bitMatrix, "png", new File(imgPath).toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
