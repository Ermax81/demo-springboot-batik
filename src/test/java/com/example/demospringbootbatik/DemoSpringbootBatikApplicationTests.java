package com.example.demospringbootbatik;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.*;

@SpringBootTest
class DemoSpringbootBatikApplicationTests {

	// Where to find free svg: https://publicdomainvectors.org/fr/tag/svg

	@Test
	void contextLoads() {
	}

	// Source: https://stackoverflow.com/questions/27269660/how-to-convert-svg-to-png-or-jpg#59561137
	@Test
	void testSvgToPng() throws FileNotFoundException {

		try {
			File svgFile = ResourceUtils.getFile("classpath:"+"SVG.svg");  // 171x171
			InputStream is = new FileInputStream(svgFile);

			TranscoderInput input_svg_image = new TranscoderInput(is);

			//Step-2: Define OutputStream to PNG Image and attach to TranscoderOutput
			String filePath = "src/test/resources/tmp.png";
			OutputStream png_ostream = new FileOutputStream(filePath);
			TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);

			// Step-3: Create PNGTranscoder and define hints if required
			PNGTranscoder my_converter = new PNGTranscoder();

			// Scaling
			//my_converter.addTranscodingHint(PNGTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER,0.084672F);

			// Output Size
			//my_converter.addTranscodingHint(PNGTranscoder.KEY_WIDTH, 171f);
			//my_converter.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, 171f);

			// Background
			//my_converter.addTranscodingHint(PNGTranscoder.KEY_BACKGROUND_COLOR, Color.WHITE);

			// Step-4: Convert and Write output
			System.out.println("It will print");
			my_converter.transcode(input_svg_image, output_png_image);

			System.out.println("It will not print");
			png_ostream.flush();
			png_ostream.close();

		} catch (TranscoderException | IOException e) {
			//TranscoderException <- my_converter.transcode
			//IOException <- flush/close
			e.printStackTrace();
		}

	}

}
