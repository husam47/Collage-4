package com.adroitstudio.photocollage;

import java.io.IOException;
import java.io.InputStream;
import android.content.res.AssetManager;
public class AssetsUtil {
	private static final String str[] = {
"x.png",
"shapes/AAA0.png",
"shapes/AAAA8.png",
"shapes/A5.png",
"shapes/AA6.png",
"shapes/AAAA6.png",
"shapes/AAAA7.png",
"shapes/AAA1.png",
"shapes/AAA4.png",
"shapes/AAA7.png",
"shapes/AAAA5.png",
"shapes/AAAAA1.png",
"shapes/AAAA3.png",
"shapes/AAAAA6.png",
"shapes/AAAA2.png",
"shapes/AA7.png",
"shapes/AA1.png",
"shapes/AAA6.png",
"shapes/A0.png",
"shapes/AAAA1.png",
"shapes/A8.png",
"shapes/AA3.png",
"shapes/A2.png",
"shapes/AA4.png",
"shapes/AAA2.png",
"shapes/A6.png",
"shapes/AAA5.png",
"shapes/AAAA9.png",
"shapes/AAA3.png",
"shapes/AA9.png",
"shapes/AA8.png",
"shapes/AAAAA0.png",
"shapes/AA5.png",
"shapes/AA2.png",
"shapes/AAAAA3.png",
"shapes/AAA9.png",
"shapes/AAAAA2.png",
"shapes/A9.png",
"shapes/A7.png",
"shapes/AAA8.png",
"shapes/AA0.png",
"shapes/A1.png",
"shapes/AAAA0.png",
"shapes/AAAAA7.png",
"shapes/A.png",
"shapes/AAAAA4.png",
"shapes/AAAAA5.png",
"shapes/A4.png",
"shapes/AAAA4.png",
"font.ttf",
"collage_bg/ae7.png",
"collage_bg/ad1.png",
"collage_bg/a1.png",
"collage_bg/ae1.png",
"collage_bg/af4.png",
"collage_bg/ab9.png",
"collage_bg/a8.png",
"collage_bg/a4.png",
"collage_bg/ad7.png",
"collage_bg/af0.png",
"collage_bg/ab0.png",
"collage_bg/ad6.png",
"collage_bg/a0.png",
"collage_bg/af5.png",
"collage_bg/ae4.png",
"collage_bg/ac5.png",
"collage_bg/ag1.png",
"collage_bg/ab6.png",
"collage_bg/a9.png",
"collage_bg/ag2.png",
"collage_bg/ae5.png",
"collage_bg/ae9.png",
"collage_bg/ab5.png",
"collage_bg/a7.png",
"collage_bg/ae0.png",
"collage_bg/ac8.png",
"collage_bg/ac6.png",
"collage_bg/ac9.png",
"collage_bg/ag4.png",
"collage_bg/ae2.png",
"collage_bg/ae6.png",
"collage_bg/ad3.png",
"collage_bg/ae8.png",
"collage_bg/af8.png",
"collage_bg/ac3.png",
"collage_bg/ab3.png",
"collage_bg/af2.png",
"collage_bg/ac4.png",
"collage_bg/ad8.png",
"collage_bg/ad4.png",
"collage_bg/ab1.png",
"collage_bg/ae3.png",
"collage_bg/ag6.png",
"collage_bg/af9.png",
"collage_bg/ac1.png",
"collage_bg/ad2.png",
"collage_bg/ac2.png",
"collage_bg/af6.png",
"collage_bg/ab4.png",
"collage_bg/ad5.png",
"collage_bg/ag0.png",
"collage_bg/af1.png",
"collage_bg/a5.png",
"collage_bg/ac0.png",
"collage_bg/ab7.png",
"collage_bg/ag5.png",
"collage_bg/ac7.png",
"collage_bg/ad9.png",
"collage_bg/af3.png",
"collage_bg/ad0.png",
"collage_bg/ab2.png",
"collage_bg/a2.png",
"collage_bg/ag3.png",
"collage_bg/a3.png",
"collage_bg/af7.png",
"collage_bg/ab8.png",
"collage_bg/a6.png",
"clip_arts/ai7.png",
"clip_arts/ae7.png",
"clip_arts/ag9.png",
"clip_arts/ak4.png",
"clip_arts/ad1.png",
"clip_arts/aj3.png",
"clip_arts/a1.png",
"clip_arts/ak0.png",
"clip_arts/ae1.png",
"clip_arts/af4.png",
"clip_arts/ab9.png",
"clip_arts/ak1.png",
"clip_arts/ah7.png",
"clip_arts/a4.png",
"clip_arts/ad7.png",
"clip_arts/ah1.png",
"clip_arts/af0.png",
"clip_arts/ag7.png",
"clip_arts/ab0.png",
"clip_arts/ah5.png",
"clip_arts/ad6.png",
"clip_arts/a0.png",
"clip_arts/ai0.png",
"clip_arts/af5.png",
"clip_arts/ae4.png",
"clip_arts/ah8.png",
"clip_arts/aj2.png",
"clip_arts/ai6.png",
"clip_arts/ac5.png",
"clip_arts/ag1.png",
"clip_arts/ab6.png",
"clip_arts/aj4.png",
"clip_arts/ai2.png",
"clip_arts/ai9.png",
"clip_arts/a9.png",
"clip_arts/ag2.png",
"clip_arts/ae5.png",
"clip_arts/ae9.png",
"clip_arts/ak2.png",
"clip_arts/ab5.png",
"clip_arts/aj7.png",
"clip_arts/a7.png",
"clip_arts/ag8.png",
"clip_arts/ae0.png",
"clip_arts/ac8.png",
"clip_arts/ac6.png",
"clip_arts/aj1.png",
"clip_arts/ac9.png",
"clip_arts/ag4.png",
"clip_arts/ae2.png",
"clip_arts/ae6.png",
"clip_arts/ad3.png",
"clip_arts/ae8.png",
"clip_arts/af8.png",
"clip_arts/ac3.png",
"clip_arts/ab3.png",
"clip_arts/ak3.png",
"clip_arts/aj8.png",
"clip_arts/af2.png",
"clip_arts/ac4.png",
"clip_arts/ad8.png",
"clip_arts/ad4.png",
"clip_arts/ai5.png",
"clip_arts/ab1.png",
"clip_arts/ae3.png",
"clip_arts/ah0.png",
"clip_arts/ah2.png",
"clip_arts/ag6.png",
"clip_arts/ai4.png",
"clip_arts/af9.png",
"clip_arts/ac1.png",
"clip_arts/ai8.png",
"clip_arts/ad2.png",
"clip_arts/ac2.png",
"clip_arts/af6.png",
"clip_arts/ah4.png",
"clip_arts/ab4.png",
"clip_arts/ad5.png",
"clip_arts/ai3.png",
"clip_arts/ag0.png",
"clip_arts/aj0.png",
"clip_arts/af1.png",
"clip_arts/a5.png",
"clip_arts/aj5.png",
"clip_arts/aj6.png",
"clip_arts/ac0.png",
"clip_arts/ab7.png",
"clip_arts/ah6.png",
"clip_arts/aj9.png",
"clip_arts/ag5.png",
"clip_arts/ah9.png",
"clip_arts/ac7.png",
"clip_arts/ah3.png",
"clip_arts/ai1.png",
"clip_arts/ad9.png",
"clip_arts/af3.png",
"clip_arts/ad0.png",
"clip_arts/ab2.png",
"clip_arts/a2.png",
"clip_arts/ag3.png",
"clip_arts/a3.png",
"clip_arts/af7.png",
"clip_arts/ab8.png",
"clip_arts/a6.png",
"shape_utils/AAA0.png",
"shape_utils/AAAA8.png",
"shape_utils/AAAA6.png",
"shape_utils/AAAA7.png",
"shape_utils/AAA1.png",
"shape_utils/AAA4.png",
"shape_utils/AAA7.png",
"shape_utils/AAAA5.png",
"shape_utils/AAAA3.png",
"shape_utils/AAAA2.png",
"shape_utils/AA7.png",
"shape_utils/AA1.png",
"shape_utils/AAA6.png",
"shape_utils/AAAA1.png",
"shape_utils/A2.png",
"shape_utils/AA4.png",
"shape_utils/AAA2.png",
"shape_utils/AAA5.png",
"shape_utils/AAA3.png",
"shape_utils/AA9.png",
"shape_utils/AAA9.png",
"shape_utils/A9.png",
"shape_utils/A3.png",
"shape_utils/AAA8.png",
"shape_utils/AA0.png",
"shape_utils/A1.png",
"shape_utils/AAAA0.png",
"shape_utils/A4.png",
"shape_utils/AAAA4.png",
"font/Champagne_Limousines.ttf",
"font/arriere_garde.ttf",
"font/AdobeArabic-Italic.otf",
"font/AdobeArabic-Bold.otf",
"font/BrushScriptStd.otf",
"font/ACaslonPro-Italic.otf",
"font/Caviar_Dreams_Bold.ttf",
"font/BLESD__.otf",
"font/ITCKRIST.TTF",
"font/CaviarDreams_BoldItalic.ttf",
"font/AGaramondPro-Regular.otf",
"font/CaviarDreams.ttf",
"font/Gabriola.ttf",
"font/CaviarDreams_Italic.ttf",
"font/BASKVILL.TTF",
"shape_thumb/AAA0.png",
"shape_thumb/A5.png",
"shape_thumb/AA6.png",
"shape_thumb/AAA1.png",
"shape_thumb/AAA4.png",
"shape_thumb/AAA7.png",
"shape_thumb/AAAA3.png",
"shape_thumb/AAAA2.png",
"shape_thumb/AA7.png",
"shape_thumb/AA1.png",
"shape_thumb/AAA6.png",
"shape_thumb/AAAA1.png",
"shape_thumb/A8.png",
"shape_thumb/AA3.png",
"shape_thumb/A2.png",
"shape_thumb/AA4.png",
"shape_thumb/AAA2.png",
"shape_thumb/A6.png",
"shape_thumb/AAA5.png",
"shape_thumb/AAA3.png",
"shape_thumb/AA9.png",
"shape_thumb/AA8.png",
"shape_thumb/AA5.png",
"shape_thumb/AA2.png",
"shape_thumb/AAA9.png",
"shape_thumb/A9.png",
"shape_thumb/A3.png",
"shape_thumb/A7.png",
"shape_thumb/AAA8.png",
"shape_thumb/AA0.png",
"shape_thumb/A1.png",
"shape_thumb/AAAA0.png",
"shape_thumb/A4.png",
"shape_thumb/AAAA4.png",
"grids/pic1/a1.png",
"grids/pic3/aa4.png",
"grids/pic3/a1.png",
"grids/pic3/a8.png",
"grids/pic3/a4.png",
"grids/pic3/aa0.png",
"grids/pic3/aa2.png",
"grids/pic3/a9.png",
"grids/pic3/a7.png",
"grids/pic3/aa3.png",
"grids/pic3/aa5.png",
"grids/pic3/a5.png",
"grids/pic3/a2.png",
"grids/pic3/aa1.png",
"grids/pic3/a3.png",
"grids/pic3/a6.png",
"grids/pic4/aa4.png",
"grids/pic4/a1.png",
"grids/pic4/a8.png",
"grids/pic4/a4.png",
"grids/pic4/aa0.png",
"grids/pic4/aa2.png",
"grids/pic4/a9.png",
"grids/pic4/a7.png",
"grids/pic4/aa3.png",
"grids/pic4/aa5.png",
"grids/pic4/a5.png",
"grids/pic4/a2.png",
"grids/pic4/aa1.png",
"grids/pic4/a3.png",
"grids/pic4/a6.png",
"grids/pic7/aa4.png",
"grids/pic7/a1.png",
"grids/pic7/a8.png",
"grids/pic7/a4.png",
"grids/pic7/aa0.png",
"grids/pic7/aa2.png",
"grids/pic7/a9.png",
"grids/pic7/a7.png",
"grids/pic7/aa3.png",
"grids/pic7/aa5.png",
"grids/pic7/a5.png",
"grids/pic7/a2.png",
"grids/pic7/aa1.png",
"grids/pic7/a3.png",
"grids/pic7/a6.png",
"grids/pic5/aa4.png",
"grids/pic5/a1.png",
"grids/pic5/a8.png",
"grids/pic5/a4.png",
"grids/pic5/aa0.png",
"grids/pic5/aa2.png",
"grids/pic5/a9.png",
"grids/pic5/a7.png",
"grids/pic5/aa3.png",
"grids/pic5/aa5.png",
"grids/pic5/a5.png",
"grids/pic5/a2.png",
"grids/pic5/aa1.png",
"grids/pic5/a3.png",
"grids/pic5/a6.png",
"grids/pic6/aa4.png",
"grids/pic6/a1.png",
"grids/pic6/a8.png",
"grids/pic6/a4.png",
"grids/pic6/aa0.png",
"grids/pic6/aa2.png",
"grids/pic6/a9.png",
"grids/pic6/a7.png",
"grids/pic6/aa3.png",
"grids/pic6/aa5.png",
"grids/pic6/a5.png",
"grids/pic6/a2.png",
"grids/pic6/aa1.png",
"grids/pic6/a3.png",
"grids/pic6/a6.png",
"grids/pic2/a1.png",
"grids/pic2/a4.png",
"grids/pic2/a5.png",
"grids/pic2/a2.png",
"grids/pic2/a3.png",
"grids/pic2/a6.png",
"grids/pic9/aa4.png",
"grids/pic9/a1.png",
"grids/pic9/a8.png",
"grids/pic9/a4.png",
"grids/pic9/aa0.png",
"grids/pic9/aa2.png",
"grids/pic9/a9.png",
"grids/pic9/a7.png",
"grids/pic9/aa3.png",
"grids/pic9/a5.png",
"grids/pic9/a2.png",
"grids/pic9/aa1.png",
"grids/pic9/a3.png",
"grids/pic9/a6.png",
"grids/pic8/aa4.png",
"grids/pic8/a1.png",
"grids/pic8/a8.png",
"grids/pic8/a4.png",
"grids/pic8/aa0.png",
"grids/pic8/aa2.png",
"grids/pic8/a9.png",
"grids/pic8/a7.png",
"grids/pic8/aa3.png",
"grids/pic8/aa5.png",
"grids/pic8/a5.png",
"grids/pic8/a2.png",
"grids/pic8/aa1.png",
"grids/pic8/a3.png",
"grids/pic8/a6.png",
"collage_ratio/A5.png",
"collage_ratio/AA1.png",
"collage_ratio/A0.png",
"collage_ratio/A8.png",
"collage_ratio/A2.png",
"collage_ratio/A6.png",
"collage_ratio/A9.png",
"collage_ratio/A3.png",
"collage_ratio/A7.png",
"collage_ratio/AA0.png",
"collage_ratio/A1.png",
"collage_ratio/A4.png"	};
public static InputStream open(AssetManager assets, String assetName) throws IOException {
		int index = getIntedx(assetName);
		return getStream(assets, index);
	}
	private static InputStream getStream(AssetManager assets, int index) throws IOException {
		switch (index) {
case 0:
return assets.open("x.png");
case 1:
return assets.open("shapes/AAA0.png");
case 2:
return assets.open("shapes/AAAA8.png");
case 3:
return assets.open("shapes/A5.png");
case 4:
return assets.open("shapes/AA6.png");
case 5:
return assets.open("shapes/AAAA6.png");
case 6:
return assets.open("shapes/AAAA7.png");
case 7:
return assets.open("shapes/AAA1.png");
case 8:
return assets.open("shapes/AAA4.png");
case 9:
return assets.open("shapes/AAA7.png");
case 10:
return assets.open("shapes/AAAA5.png");
case 11:
return assets.open("shapes/AAAAA1.png");
case 12:
return assets.open("shapes/AAAA3.png");
case 13:
return assets.open("shapes/AAAAA6.png");
case 14:
return assets.open("shapes/AAAA2.png");
case 15:
return assets.open("shapes/AA7.png");
case 16:
return assets.open("shapes/AA1.png");
case 17:
return assets.open("shapes/AAA6.png");
case 18:
return assets.open("shapes/A0.png");
case 19:
return assets.open("shapes/AAAA1.png");
case 20:
return assets.open("shapes/A8.png");
case 21:
return assets.open("shapes/AA3.png");
case 22:
return assets.open("shapes/A2.png");
case 23:
return assets.open("shapes/AA4.png");
case 24:
return assets.open("shapes/AAA2.png");
case 25:
return assets.open("shapes/A6.png");
case 26:
return assets.open("shapes/AAA5.png");
case 27:
return assets.open("shapes/AAAA9.png");
case 28:
return assets.open("shapes/AAA3.png");
case 29:
return assets.open("shapes/AA9.png");
case 30:
return assets.open("shapes/AA8.png");
case 31:
return assets.open("shapes/AAAAA0.png");
case 32:
return assets.open("shapes/AA5.png");
case 33:
return assets.open("shapes/AA2.png");
case 34:
return assets.open("shapes/AAAAA3.png");
case 35:
return assets.open("shapes/AAA9.png");
case 36:
return assets.open("shapes/AAAAA2.png");
case 37:
return assets.open("shapes/A9.png");
case 38:
return assets.open("shapes/A7.png");
case 39:
return assets.open("shapes/AAA8.png");
case 40:
return assets.open("shapes/AA0.png");
case 41:
return assets.open("shapes/A1.png");
case 42:
return assets.open("shapes/AAAA0.png");
case 43:
return assets.open("shapes/AAAAA7.png");
case 44:
return assets.open("shapes/A.png");
case 45:
return assets.open("shapes/AAAAA4.png");
case 46:
return assets.open("shapes/AAAAA5.png");
case 47:
return assets.open("shapes/A4.png");
case 48:
return assets.open("shapes/AAAA4.png");
case 49:
return assets.open("font.ttf");
case 50:
return assets.open("collage_bg/ae7.png");
case 51:
return assets.open("collage_bg/ad1.png");
case 52:
return assets.open("collage_bg/a1.png");
case 53:
return assets.open("collage_bg/ae1.png");
case 54:
return assets.open("collage_bg/af4.png");
case 55:
return assets.open("collage_bg/ab9.png");
case 56:
return assets.open("collage_bg/a8.png");
case 57:
return assets.open("collage_bg/a4.png");
case 58:
return assets.open("collage_bg/ad7.png");
case 59:
return assets.open("collage_bg/af0.png");
case 60:
return assets.open("collage_bg/ab0.png");
case 61:
return assets.open("collage_bg/ad6.png");
case 62:
return assets.open("collage_bg/a0.png");
case 63:
return assets.open("collage_bg/af5.png");
case 64:
return assets.open("collage_bg/ae4.png");
case 65:
return assets.open("collage_bg/ac5.png");
case 66:
return assets.open("collage_bg/ag1.png");
case 67:
return assets.open("collage_bg/ab6.png");
case 68:
return assets.open("collage_bg/a9.png");
case 69:
return assets.open("collage_bg/ag2.png");
case 70:
return assets.open("collage_bg/ae5.png");
case 71:
return assets.open("collage_bg/ae9.png");
case 72:
return assets.open("collage_bg/ab5.png");
case 73:
return assets.open("collage_bg/a7.png");
case 74:
return assets.open("collage_bg/ae0.png");
case 75:
return assets.open("collage_bg/ac8.png");
case 76:
return assets.open("collage_bg/ac6.png");
case 77:
return assets.open("collage_bg/ac9.png");
case 78:
return assets.open("collage_bg/ag4.png");
case 79:
return assets.open("collage_bg/ae2.png");
case 80:
return assets.open("collage_bg/ae6.png");
case 81:
return assets.open("collage_bg/ad3.png");
case 82:
return assets.open("collage_bg/ae8.png");
case 83:
return assets.open("collage_bg/af8.png");
case 84:
return assets.open("collage_bg/ac3.png");
case 85:
return assets.open("collage_bg/ab3.png");
case 86:
return assets.open("collage_bg/af2.png");
case 87:
return assets.open("collage_bg/ac4.png");
case 88:
return assets.open("collage_bg/ad8.png");
case 89:
return assets.open("collage_bg/ad4.png");
case 90:
return assets.open("collage_bg/ab1.png");
case 91:
return assets.open("collage_bg/ae3.png");
case 92:
return assets.open("collage_bg/ag6.png");
case 93:
return assets.open("collage_bg/af9.png");
case 94:
return assets.open("collage_bg/ac1.png");
case 95:
return assets.open("collage_bg/ad2.png");
case 96:
return assets.open("collage_bg/ac2.png");
case 97:
return assets.open("collage_bg/af6.png");
case 98:
return assets.open("collage_bg/ab4.png");
case 99:
return assets.open("collage_bg/ad5.png");
case 100:
return assets.open("collage_bg/ag0.png");
case 101:
return assets.open("collage_bg/af1.png");
case 102:
return assets.open("collage_bg/a5.png");
case 103:
return assets.open("collage_bg/ac0.png");
case 104:
return assets.open("collage_bg/ab7.png");
case 105:
return assets.open("collage_bg/ag5.png");
case 106:
return assets.open("collage_bg/ac7.png");
case 107:
return assets.open("collage_bg/ad9.png");
case 108:
return assets.open("collage_bg/af3.png");
case 109:
return assets.open("collage_bg/ad0.png");
case 110:
return assets.open("collage_bg/ab2.png");
case 111:
return assets.open("collage_bg/a2.png");
case 112:
return assets.open("collage_bg/ag3.png");
case 113:
return assets.open("collage_bg/a3.png");
case 114:
return assets.open("collage_bg/af7.png");
case 115:
return assets.open("collage_bg/ab8.png");
case 116:
return assets.open("collage_bg/a6.png");
case 117:
return assets.open("clip_arts/ai7.png");
case 118:
return assets.open("clip_arts/ae7.png");
case 119:
return assets.open("clip_arts/ag9.png");
case 120:
return assets.open("clip_arts/ak4.png");
case 121:
return assets.open("clip_arts/ad1.png");
case 122:
return assets.open("clip_arts/aj3.png");
case 123:
return assets.open("clip_arts/a1.png");
case 124:
return assets.open("clip_arts/ak0.png");
case 125:
return assets.open("clip_arts/ae1.png");
case 126:
return assets.open("clip_arts/af4.png");
case 127:
return assets.open("clip_arts/ab9.png");
case 128:
return assets.open("clip_arts/ak1.png");
case 129:
return assets.open("clip_arts/ah7.png");
case 130:
return assets.open("clip_arts/a4.png");
case 131:
return assets.open("clip_arts/ad7.png");
case 132:
return assets.open("clip_arts/ah1.png");
case 133:
return assets.open("clip_arts/af0.png");
case 134:
return assets.open("clip_arts/ag7.png");
case 135:
return assets.open("clip_arts/ab0.png");
case 136:
return assets.open("clip_arts/ah5.png");
case 137:
return assets.open("clip_arts/ad6.png");
case 138:
return assets.open("clip_arts/a0.png");
case 139:
return assets.open("clip_arts/ai0.png");
case 140:
return assets.open("clip_arts/af5.png");
case 141:
return assets.open("clip_arts/ae4.png");
case 142:
return assets.open("clip_arts/ah8.png");
case 143:
return assets.open("clip_arts/aj2.png");
case 144:
return assets.open("clip_arts/ai6.png");
case 145:
return assets.open("clip_arts/ac5.png");
case 146:
return assets.open("clip_arts/ag1.png");
case 147:
return assets.open("clip_arts/ab6.png");
case 148:
return assets.open("clip_arts/aj4.png");
case 149:
return assets.open("clip_arts/ai2.png");
case 150:
return assets.open("clip_arts/ai9.png");
case 151:
return assets.open("clip_arts/a9.png");
case 152:
return assets.open("clip_arts/ag2.png");
case 153:
return assets.open("clip_arts/ae5.png");
case 154:
return assets.open("clip_arts/ae9.png");
case 155:
return assets.open("clip_arts/ak2.png");
case 156:
return assets.open("clip_arts/ab5.png");
case 157:
return assets.open("clip_arts/aj7.png");
case 158:
return assets.open("clip_arts/a7.png");
case 159:
return assets.open("clip_arts/ag8.png");
case 160:
return assets.open("clip_arts/ae0.png");
case 161:
return assets.open("clip_arts/ac8.png");
case 162:
return assets.open("clip_arts/ac6.png");
case 163:
return assets.open("clip_arts/aj1.png");
case 164:
return assets.open("clip_arts/ac9.png");
case 165:
return assets.open("clip_arts/ag4.png");
case 166:
return assets.open("clip_arts/ae2.png");
case 167:
return assets.open("clip_arts/ae6.png");
case 168:
return assets.open("clip_arts/ad3.png");
case 169:
return assets.open("clip_arts/ae8.png");
case 170:
return assets.open("clip_arts/af8.png");
case 171:
return assets.open("clip_arts/ac3.png");
case 172:
return assets.open("clip_arts/ab3.png");
case 173:
return assets.open("clip_arts/ak3.png");
case 174:
return assets.open("clip_arts/aj8.png");
case 175:
return assets.open("clip_arts/af2.png");
case 176:
return assets.open("clip_arts/ac4.png");
case 177:
return assets.open("clip_arts/ad8.png");
case 178:
return assets.open("clip_arts/ad4.png");
case 179:
return assets.open("clip_arts/ai5.png");
case 180:
return assets.open("clip_arts/ab1.png");
case 181:
return assets.open("clip_arts/ae3.png");
case 182:
return assets.open("clip_arts/ah0.png");
case 183:
return assets.open("clip_arts/ah2.png");
case 184:
return assets.open("clip_arts/ag6.png");
case 185:
return assets.open("clip_arts/ai4.png");
case 186:
return assets.open("clip_arts/af9.png");
case 187:
return assets.open("clip_arts/ac1.png");
case 188:
return assets.open("clip_arts/ai8.png");
case 189:
return assets.open("clip_arts/ad2.png");
case 190:
return assets.open("clip_arts/ac2.png");
case 191:
return assets.open("clip_arts/af6.png");
case 192:
return assets.open("clip_arts/ah4.png");
case 193:
return assets.open("clip_arts/ab4.png");
case 194:
return assets.open("clip_arts/ad5.png");
case 195:
return assets.open("clip_arts/ai3.png");
case 196:
return assets.open("clip_arts/ag0.png");
case 197:
return assets.open("clip_arts/aj0.png");
case 198:
return assets.open("clip_arts/af1.png");
case 199:
return assets.open("clip_arts/a5.png");
case 200:
return assets.open("clip_arts/aj5.png");
case 201:
return assets.open("clip_arts/aj6.png");
case 202:
return assets.open("clip_arts/ac0.png");
case 203:
return assets.open("clip_arts/ab7.png");
case 204:
return assets.open("clip_arts/ah6.png");
case 205:
return assets.open("clip_arts/aj9.png");
case 206:
return assets.open("clip_arts/ag5.png");
case 207:
return assets.open("clip_arts/ah9.png");
case 208:
return assets.open("clip_arts/ac7.png");
case 209:
return assets.open("clip_arts/ah3.png");
case 210:
return assets.open("clip_arts/ai1.png");
case 211:
return assets.open("clip_arts/ad9.png");
case 212:
return assets.open("clip_arts/af3.png");
case 213:
return assets.open("clip_arts/ad0.png");
case 214:
return assets.open("clip_arts/ab2.png");
case 215:
return assets.open("clip_arts/a2.png");
case 216:
return assets.open("clip_arts/ag3.png");
case 217:
return assets.open("clip_arts/a3.png");
case 218:
return assets.open("clip_arts/af7.png");
case 219:
return assets.open("clip_arts/ab8.png");
case 220:
return assets.open("clip_arts/a6.png");
case 221:
return assets.open("shape_utils/AAA0.png");
case 222:
return assets.open("shape_utils/AAAA8.png");
case 223:
return assets.open("shape_utils/AAAA6.png");
case 224:
return assets.open("shape_utils/AAAA7.png");
case 225:
return assets.open("shape_utils/AAA1.png");
case 226:
return assets.open("shape_utils/AAA4.png");
case 227:
return assets.open("shape_utils/AAA7.png");
case 228:
return assets.open("shape_utils/AAAA5.png");
case 229:
return assets.open("shape_utils/AAAA3.png");
case 230:
return assets.open("shape_utils/AAAA2.png");
case 231:
return assets.open("shape_utils/AA7.png");
case 232:
return assets.open("shape_utils/AA1.png");
case 233:
return assets.open("shape_utils/AAA6.png");
case 234:
return assets.open("shape_utils/AAAA1.png");
case 235:
return assets.open("shape_utils/A2.png");
case 236:
return assets.open("shape_utils/AA4.png");
case 237:
return assets.open("shape_utils/AAA2.png");
case 238:
return assets.open("shape_utils/AAA5.png");
case 239:
return assets.open("shape_utils/AAA3.png");
case 240:
return assets.open("shape_utils/AA9.png");
case 241:
return assets.open("shape_utils/AAA9.png");
case 242:
return assets.open("shape_utils/A9.png");
case 243:
return assets.open("shape_utils/A3.png");
case 244:
return assets.open("shape_utils/AAA8.png");
case 245:
return assets.open("shape_utils/AA0.png");
case 246:
return assets.open("shape_utils/A1.png");
case 247:
return assets.open("shape_utils/AAAA0.png");
case 248:
return assets.open("shape_utils/A4.png");
case 249:
return assets.open("shape_utils/AAAA4.png");
case 250:
return assets.open("font/Champagne_Limousines.ttf");
case 251:
return assets.open("font/arriere_garde.ttf");
case 252:
return assets.open("font/AdobeArabic-Italic.otf");
case 253:
return assets.open("font/AdobeArabic-Bold.otf");
case 254:
return assets.open("font/BrushScriptStd.otf");
case 255:
return assets.open("font/ACaslonPro-Italic.otf");
case 256:
return assets.open("font/Caviar_Dreams_Bold.ttf");
case 257:
return assets.open("font/BLESD__.otf");
case 258:
return assets.open("font/ITCKRIST.TTF");
case 259:
return assets.open("font/CaviarDreams_BoldItalic.ttf");
case 260:
return assets.open("font/AGaramondPro-Regular.otf");
case 261:
return assets.open("font/CaviarDreams.ttf");
case 262:
return assets.open("font/Gabriola.ttf");
case 263:
return assets.open("font/CaviarDreams_Italic.ttf");
case 264:
return assets.open("font/BASKVILL.TTF");
case 265:
return assets.open("shape_thumb/AAA0.png");
case 266:
return assets.open("shape_thumb/A5.png");
case 267:
return assets.open("shape_thumb/AA6.png");
case 268:
return assets.open("shape_thumb/AAA1.png");
case 269:
return assets.open("shape_thumb/AAA4.png");
case 270:
return assets.open("shape_thumb/AAA7.png");
case 271:
return assets.open("shape_thumb/AAAA3.png");
case 272:
return assets.open("shape_thumb/AAAA2.png");
case 273:
return assets.open("shape_thumb/AA7.png");
case 274:
return assets.open("shape_thumb/AA1.png");
case 275:
return assets.open("shape_thumb/AAA6.png");
case 276:
return assets.open("shape_thumb/AAAA1.png");
case 277:
return assets.open("shape_thumb/A8.png");
case 278:
return assets.open("shape_thumb/AA3.png");
case 279:
return assets.open("shape_thumb/A2.png");
case 280:
return assets.open("shape_thumb/AA4.png");
case 281:
return assets.open("shape_thumb/AAA2.png");
case 282:
return assets.open("shape_thumb/A6.png");
case 283:
return assets.open("shape_thumb/AAA5.png");
case 284:
return assets.open("shape_thumb/AAA3.png");
case 285:
return assets.open("shape_thumb/AA9.png");
case 286:
return assets.open("shape_thumb/AA8.png");
case 287:
return assets.open("shape_thumb/AA5.png");
case 288:
return assets.open("shape_thumb/AA2.png");
case 289:
return assets.open("shape_thumb/AAA9.png");
case 290:
return assets.open("shape_thumb/A9.png");
case 291:
return assets.open("shape_thumb/A3.png");
case 292:
return assets.open("shape_thumb/A7.png");
case 293:
return assets.open("shape_thumb/AAA8.png");
case 294:
return assets.open("shape_thumb/AA0.png");
case 295:
return assets.open("shape_thumb/A1.png");
case 296:
return assets.open("shape_thumb/AAAA0.png");
case 297:
return assets.open("shape_thumb/A4.png");
case 298:
return assets.open("shape_thumb/AAAA4.png");
case 299:
return assets.open("grids/pic1/a1.png");
case 300:
return assets.open("grids/pic3/aa4.png");
case 301:
return assets.open("grids/pic3/a1.png");
case 302:
return assets.open("grids/pic3/a8.png");
case 303:
return assets.open("grids/pic3/a4.png");
case 304:
return assets.open("grids/pic3/aa0.png");
case 305:
return assets.open("grids/pic3/aa2.png");
case 306:
return assets.open("grids/pic3/a9.png");
case 307:
return assets.open("grids/pic3/a7.png");
case 308:
return assets.open("grids/pic3/aa3.png");
case 309:
return assets.open("grids/pic3/aa5.png");
case 310:
return assets.open("grids/pic3/a5.png");
case 311:
return assets.open("grids/pic3/a2.png");
case 312:
return assets.open("grids/pic3/aa1.png");
case 313:
return assets.open("grids/pic3/a3.png");
case 314:
return assets.open("grids/pic3/a6.png");
case 315:
return assets.open("grids/pic4/aa4.png");
case 316:
return assets.open("grids/pic4/a1.png");
case 317:
return assets.open("grids/pic4/a8.png");
case 318:
return assets.open("grids/pic4/a4.png");
case 319:
return assets.open("grids/pic4/aa0.png");
case 320:
return assets.open("grids/pic4/aa2.png");
case 321:
return assets.open("grids/pic4/a9.png");
case 322:
return assets.open("grids/pic4/a7.png");
case 323:
return assets.open("grids/pic4/aa3.png");
case 324:
return assets.open("grids/pic4/aa5.png");
case 325:
return assets.open("grids/pic4/a5.png");
case 326:
return assets.open("grids/pic4/a2.png");
case 327:
return assets.open("grids/pic4/aa1.png");
case 328:
return assets.open("grids/pic4/a3.png");
case 329:
return assets.open("grids/pic4/a6.png");
case 330:
return assets.open("grids/pic7/aa4.png");
case 331:
return assets.open("grids/pic7/a1.png");
case 332:
return assets.open("grids/pic7/a8.png");
case 333:
return assets.open("grids/pic7/a4.png");
case 334:
return assets.open("grids/pic7/aa0.png");
case 335:
return assets.open("grids/pic7/aa2.png");
case 336:
return assets.open("grids/pic7/a9.png");
case 337:
return assets.open("grids/pic7/a7.png");
case 338:
return assets.open("grids/pic7/aa3.png");
case 339:
return assets.open("grids/pic7/aa5.png");
case 340:
return assets.open("grids/pic7/a5.png");
case 341:
return assets.open("grids/pic7/a2.png");
case 342:
return assets.open("grids/pic7/aa1.png");
case 343:
return assets.open("grids/pic7/a3.png");
case 344:
return assets.open("grids/pic7/a6.png");
case 345:
return assets.open("grids/pic5/aa4.png");
case 346:
return assets.open("grids/pic5/a1.png");
case 347:
return assets.open("grids/pic5/a8.png");
case 348:
return assets.open("grids/pic5/a4.png");
case 349:
return assets.open("grids/pic5/aa0.png");
case 350:
return assets.open("grids/pic5/aa2.png");
case 351:
return assets.open("grids/pic5/a9.png");
case 352:
return assets.open("grids/pic5/a7.png");
case 353:
return assets.open("grids/pic5/aa3.png");
case 354:
return assets.open("grids/pic5/aa5.png");
case 355:
return assets.open("grids/pic5/a5.png");
case 356:
return assets.open("grids/pic5/a2.png");
case 357:
return assets.open("grids/pic5/aa1.png");
case 358:
return assets.open("grids/pic5/a3.png");
case 359:
return assets.open("grids/pic5/a6.png");
case 360:
return assets.open("grids/pic6/aa4.png");
case 361:
return assets.open("grids/pic6/a1.png");
case 362:
return assets.open("grids/pic6/a8.png");
case 363:
return assets.open("grids/pic6/a4.png");
case 364:
return assets.open("grids/pic6/aa0.png");
case 365:
return assets.open("grids/pic6/aa2.png");
case 366:
return assets.open("grids/pic6/a9.png");
case 367:
return assets.open("grids/pic6/a7.png");
case 368:
return assets.open("grids/pic6/aa3.png");
case 369:
return assets.open("grids/pic6/aa5.png");
case 370:
return assets.open("grids/pic6/a5.png");
case 371:
return assets.open("grids/pic6/a2.png");
case 372:
return assets.open("grids/pic6/aa1.png");
case 373:
return assets.open("grids/pic6/a3.png");
case 374:
return assets.open("grids/pic6/a6.png");
case 375:
return assets.open("grids/pic2/a1.png");
case 376:
return assets.open("grids/pic2/a4.png");
case 377:
return assets.open("grids/pic2/a5.png");
case 378:
return assets.open("grids/pic2/a2.png");
case 379:
return assets.open("grids/pic2/a3.png");
case 380:
return assets.open("grids/pic2/a6.png");
case 381:
return assets.open("grids/pic9/aa4.png");
case 382:
return assets.open("grids/pic9/a1.png");
case 383:
return assets.open("grids/pic9/a8.png");
case 384:
return assets.open("grids/pic9/a4.png");
case 385:
return assets.open("grids/pic9/aa0.png");
case 386:
return assets.open("grids/pic9/aa2.png");
case 387:
return assets.open("grids/pic9/a9.png");
case 388:
return assets.open("grids/pic9/a7.png");
case 389:
return assets.open("grids/pic9/aa3.png");
case 390:
return assets.open("grids/pic9/a5.png");
case 391:
return assets.open("grids/pic9/a2.png");
case 392:
return assets.open("grids/pic9/aa1.png");
case 393:
return assets.open("grids/pic9/a3.png");
case 394:
return assets.open("grids/pic9/a6.png");
case 395:
return assets.open("grids/pic8/aa4.png");
case 396:
return assets.open("grids/pic8/a1.png");
case 397:
return assets.open("grids/pic8/a8.png");
case 398:
return assets.open("grids/pic8/a4.png");
case 399:
return assets.open("grids/pic8/aa0.png");
case 400:
return assets.open("grids/pic8/aa2.png");
case 401:
return assets.open("grids/pic8/a9.png");
case 402:
return assets.open("grids/pic8/a7.png");
case 403:
return assets.open("grids/pic8/aa3.png");
case 404:
return assets.open("grids/pic8/aa5.png");
case 405:
return assets.open("grids/pic8/a5.png");
case 406:
return assets.open("grids/pic8/a2.png");
case 407:
return assets.open("grids/pic8/aa1.png");
case 408:
return assets.open("grids/pic8/a3.png");
case 409:
return assets.open("grids/pic8/a6.png");
case 410:
return assets.open("collage_ratio/A5.png");
case 411:
return assets.open("collage_ratio/AA1.png");
case 412:
return assets.open("collage_ratio/A0.png");
case 413:
return assets.open("collage_ratio/A8.png");
case 414:
return assets.open("collage_ratio/A2.png");
case 415:
return assets.open("collage_ratio/A6.png");
case 416:
return assets.open("collage_ratio/A9.png");
case 417:
return assets.open("collage_ratio/A3.png");
case 418:
return assets.open("collage_ratio/A7.png");
case 419:
return assets.open("collage_ratio/AA0.png");
case 420:
return assets.open("collage_ratio/A1.png");
case 421:
return assets.open("collage_ratio/A4.png");
		default:
return null;
		}
	}
	private static int getIntedx(String assetName) {
		for (int i = 0; i < str.length; i++) {
			if (assetName.equals(str[i])) {
				return i;
			}
		}
		return -1;
	}
}
