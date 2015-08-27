package com.adroitstudio.photocollage.view.custom_view.collage;

import java.util.LinkedList;
import java.util.Random;

import android.text.GetChars;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.adroitstudio.photocollage.Constants;
import com.adroitstudio.photocollage.controller.ImageData;

public class LayoutChildUtils implements Constants {
	private static final float MIN_MIN = 0.975F;

	// =========================================================================================================
	// VIEW GROUP METHOD ADD VIEW IN VIEW GROUP
	// =========================================================================================================

	public static void addCollageImageItem(ViewGroup viewGroup, LinkedList<ImageData> imageItemsList, int mCollageType, int layoutType) {
		viewGroup.removeAllViews();
		if (mCollageType == COLLAGE_TYPE_SHAPE) {
			addShapeChild(viewGroup, imageItemsList, layoutType);
		} else if (mCollageType == COLLAGE_TYPE_FIXED) {
			addGridChild(viewGroup, imageItemsList, true);
		} else {
			addGridChild(viewGroup, imageItemsList, false);
		}
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			viewGroup.getChildAt(i).layout(INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT, INVALID_LAYOUT);
		}
	}

	/**
	 * Method to add Grid Child, Grid may be Fixed or Free
	 * 
	 * @param isGridFixed
	 *            TRUE if elements are fixed or FALSE if elements are free style
	 */
	private static void addGridChild(ViewGroup viewGroup, LinkedList<ImageData> imageDataList, boolean isGridFixed) {
		for (int i = 0; i < imageDataList.size(); i++) {
			if (isGridFixed) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			} else {
				viewGroup.addView(new CollageViewFree(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout children of SHAPE ViewGroup
	 * 
	 * @param collageLayoutType
	 *            Type of layouting
	 */
	private static void addShapeChild(ViewGroup viewGroup, LinkedList<ImageData> imageDataList, int mCollageLayoutType) {
		switch (mCollageLayoutType) {
		case LAYOUT_A1:
			addShapeChild0(viewGroup, imageDataList);
			break;
		case LAYOUT_A2:
			addShapeChild1(viewGroup, imageDataList);
			break;
		case LAYOUT_A3:
			addShapeChild2(viewGroup, imageDataList);
			break;
		case LAYOUT_A4:
			addShapeChild3(viewGroup, imageDataList);
			break;
		case LAYOUT_A5:
			addShapeChild4(viewGroup, imageDataList);
			break;
		case LAYOUT_A6:
			addShapeChild5(viewGroup, imageDataList);
			break;
		case LAYOUT_A7:
			addShapeChild6(viewGroup, imageDataList);
			break;
		case LAYOUT_A8:
			addShapeChild7(viewGroup, imageDataList);
			break;
		case LAYOUT_A9:
			addShapeChild8(viewGroup, imageDataList);
			break;
		case LAYOUT_AA0:
			addShapeChild9(viewGroup, imageDataList);
			break;
		case LAYOUT_AA1:
			addShapeChild10(viewGroup, imageDataList);
			break;
		case LAYOUT_AA2:
			addShapeChild11(viewGroup, imageDataList);
			break;
		case LAYOUT_AA3:
			addShapeChild12(viewGroup, imageDataList);
			break;
		case LAYOUT_AA4:
			addShapeChild13(viewGroup, imageDataList);
			break;
		case LAYOUT_AA5:
			addShapeChild14(viewGroup, imageDataList);
			break;
		case LAYOUT_AA6:
			addShapeChild15(viewGroup, imageDataList);
			break;
		case LAYOUT_AA7:
			addShapeChild16(viewGroup, imageDataList);
			break;
		case LAYOUT_AA8:
			addShapeChild17(viewGroup, imageDataList);
			break;
		case LAYOUT_AA9:
			addShapeChild18(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA0:
			addShapeChild19(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA1:
			addShapeChild20(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA2:
			addShapeChild21(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA3:
			addShapeChild22(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA4:
			addShapeChild23(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA5:
			addShapeChild24(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA6:
			addShapeChild25(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA7:
			addShapeChild26(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA8:
			addShapeChild27(viewGroup, imageDataList);
			break;
		case LAYOUT_AAA9:
			addShapeChild28(viewGroup, imageDataList);
			break;
		case LAYOUT_AAAA0:
			addShapeChild29(viewGroup, imageDataList);
			break;
		case LAYOUT_AAAA1:
			addShapeChild30(viewGroup, imageDataList);
			break;
		case LAYOUT_AAAA2:
			addShapeChild31(viewGroup, imageDataList);
			break;
		case LAYOUT_AAAA3:
			addShapeChild32(viewGroup, imageDataList);
			break;
		case LAYOUT_AAAA4:
			addShapeChild33(viewGroup, imageDataList);
			break;
		default:
			break;
		}
	}

	/**
	 * Method to layout of shape at index 0 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild0(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		if (imageDataList.size() == 5) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout of shape at index 1 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild1(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// A2
		if (imageDataList.size() == 3) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout of shape at index 2 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild2(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// A3
		if (imageDataList.size() == 5) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A3")));
			}
		}
	}

	/**
	 * Method to layout of shape at index 3 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild3(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// A4
		if (imageDataList.size() == 4) {
			viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(0)));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AA0")));
			viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(2)));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(3), getShapeImageData("AA0")));
		}
	}

	/**
	 * Method to layout of shape at index 4 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild4(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// A5
		if (imageDataList.size() == 5) {
			for (int i = 0; i < imageDataList.size() - 1; i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(4), getShapeImageData("AA4")));
		}
	}

	/**
	 * Method to layout of shape at index 5 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild5(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// A6
		if (imageDataList.size() == 4) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout of shape at index 6 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild6(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// A7
		if (imageDataList.size() == 5) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AA7")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AA7")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("A3")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(3), getShapeImageData("AA7")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(4), getShapeImageData("AA7")));
		}
	}

	/**
	 * Method to layout of shape at index 7 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild7(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// A8
		if (imageDataList.size() == 9) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A3")));
			}
		}
	}

	/**
	 * Method to layout of shape at index 8 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild8(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		if (imageDataList.size() == 4) {
			for (int i = 0; i < imageDataList.size() - 1; i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(3), getShapeImageData("A3")));
		}
	}

	/**
	 * Method to layout of shape at index 9 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild9(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA0
		if (imageDataList.size() == 7) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewFree(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout of shape at index 10 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild10(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA1
		if (imageDataList.size() == 4) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A1")));
			}
		}
	}

	/**
	 * Method to layout of shape at index 11 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild11(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA2
		if (imageDataList.size() == 3) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AA9")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AAA0")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("AAA1")));
		}

	}

	/**
	 * Method to layout of shape at index 12 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild12(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA3
		if (imageDataList.size() == 3) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AAA2")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AAA3")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("AAA4")));
		}
	}

	/**
	 * Method to layout of shape at index 13 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild13(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA4
		if (imageDataList.size() == 4) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout of shape at index 14 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild14(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA5
		if (imageDataList.size() == 2) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AAA5")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AAA6")));
		}
	}

	/**
	 * Method to layout of shape at index 15 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild15(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA6
		if (imageDataList.size() == 2) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AAA7")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AAA8")));
		}
	}

	/**
	 * Method to layout of shape at index 16 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild16(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA7
		if (imageDataList.size() == 3) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AAA9")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AAAA0")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("AAAA1")));
		}
	}

	/**
	 * Method to layout of shape at index 17 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild17(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA8
		if (imageDataList.size() == 9) {
			for (int i = 0; i < imageDataList.size() - 1; i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A2")));
			}
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(8), getShapeImageData("A3")));
		}
	}

	/**
	 * Method to layout of shape at index 18 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild18(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AA9
		if (imageDataList.size() == 5) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A9")));
			}
		}

	}

	/**
	 * Method to layout of shape at index 19 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild19(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA0
		if (imageDataList.size() == 4) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AAAA2")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AAAA3")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("AAAA4")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(3), getShapeImageData("AAAA5")));
		}
	}

	/**
	 * Method to layout of shape at index 20 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild20(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA1
		if (imageDataList.size() == 6) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("AA0")));
			}
		}
	}

	/**
	 * Method to layout of shape at index 21 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild21(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA2
		if (imageDataList.size() == 3) {
			viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(0)));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("A3")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("A3")));
		}
	}

	/**
	 * Method to layout of shape at index 22 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild22(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA3
		if (imageDataList.size() == 2) {
			viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(0)));
			viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(1)));
		}
	}

	/**
	 * Method to layout of shape at index 23 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild23(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA4
		if (imageDataList.size() == 3) {
			viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(0)));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AA0")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("AA0")));
		}
	}

	/**
	 * Method to layout of shape at index 24 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild24(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA5
		if (imageDataList.size() == 4) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("A1")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("A1")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("A1")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(3), getShapeImageData("A3")));
		}
	}

	/**
	 * Method to layout of shape at index 25 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild25(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA6
		if (imageDataList.size() == 2) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("A1")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("A3")));
		}
	}

	/**
	 * Method to layout of shape at index 26 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild26(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA7
		if (imageDataList.size() == 3) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AAAA7")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AAAA8")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(2), getShapeImageData("AAAA6")));
		}
	}

	/**
	 * Method to layout of shape at index 27 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild27(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA8
		if (imageDataList.size() == 6) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A3")));
			}
		}
	}

	/**
	 * Method to layout of shape at index 28 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild28(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAA9
		if (imageDataList.size() == 6) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout of shape at index 29 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild29(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAAA0
		if (imageDataList.size() == 3) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(i)));
			}
		}
	}

	/**
	 * Method to layout of shape at index 30 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild30(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAAA1
		if (imageDataList.size() == 5) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A3")));
			}
		}
	}

	/**
	 * Method to layout of shape at index 31 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild31(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAAA2
		if (imageDataList.size() == 5) {
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(0), getShapeImageData("AA1")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(1), getShapeImageData("AA1")));
			viewGroup.addView(new CollageViewFixed(viewGroup.getContext(), imageDataList.get(2)));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(3), getShapeImageData("AA1")));
			viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(4), getShapeImageData("AA1")));
		}
	}

	/**
	 * Method to layout of shape at index 32 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild32(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAAA3
		if (imageDataList.size() == 5) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("AA0")));
			}
		}
	}

	/**
	 * Method to layout of shape at index 33 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void addShapeChild33(ViewGroup viewGroup, LinkedList<ImageData> imageDataList) {
		// AAAA4
		if (imageDataList.size() == 9) {
			for (int i = 0; i < imageDataList.size(); i++) {
				viewGroup.addView(new CollageViewShape(viewGroup.getContext(), imageDataList.get(i), getShapeImageData("A4")));
			}
		}
	}

	/**
	 * Method to get the image data for shape
	 * 
	 * @param imageName
	 * @return
	 */
	private static ImageData getShapeImageData(String imageName) {
		return new ImageData(ASSETS_SHAPE_UTILS + "/" + imageName + ".png", -1, SOURCE_APPLICATION, IMAGE_TYPE_SIZE_56789);
	}

	// =================================================================================
	// =================================================================================
	// METHODS FOR LAYOUTING THE CHILD
	// =================================================================================
	// =================================================================================

	/**
	 * Method to layout children of ViewGroup
	 * 
	 * @param viewGroup
	 * @param collageLayoutType
	 *            Type of layouting
	 */
	public static void layoutChild(ViewGroup viewGroup, int collageType, int collageLayoutType) {
		switch (collageType) {
		case COLLAGE_TYPE_SHAPE:
			Log.e("THE-MKR", "LayoutChildUtils.layoutChild()" + "1");
			layoutShapeChild(viewGroup, collageLayoutType);
			break;
		case COLLAGE_TYPE_FREE:
			Log.e("THE-MKR", "LayoutChildUtils.layoutChild()" + "2");
			layoutChildGridFree(viewGroup, collageLayoutType);
			break;
		case COLLAGE_TYPE_FIXED:
		default:
			Log.e("THE-MKR", "LayoutChildUtils.layoutChild()" + "3");
			layoutChildGridFixed(viewGroup, collageLayoutType);
			break;
		}
	}

	/**
	 * Method to get count of children in Shape ViewGroup
	 * 
	 * @param shapeIndex
	 *            index of view group
	 */
	public static int getNumberOfChildInShape(int shapeIndex) {
		switch (shapeIndex) {
		case LAYOUT_AA5:
		case LAYOUT_AA6:
		case LAYOUT_AAA3:
		case LAYOUT_AAA6:
			return 2;
		case LAYOUT_A2:
		case LAYOUT_AA2:
		case LAYOUT_AA3:
		case LAYOUT_AAA2:
		case LAYOUT_AAA4:
		case LAYOUT_AAA7:
		case LAYOUT_AAAA0:
			return 3;
		case LAYOUT_A4:
		case LAYOUT_A6:
		case LAYOUT_A9:
		case LAYOUT_AA1:
		case LAYOUT_AA4:
		case LAYOUT_AA7:
		case LAYOUT_AAA0:
		case LAYOUT_AAA5:
			return 4;
		case LAYOUT_A1:
		case LAYOUT_A3:
		case LAYOUT_A5:
		case LAYOUT_A7:
		case LAYOUT_AA9:
		case LAYOUT_AAAA1:
		case LAYOUT_AAAA2:
		case LAYOUT_AAAA3:
			return 5;
		case LAYOUT_AAA1:
		case LAYOUT_AAA8:
		case LAYOUT_AAA9:
			return 6;
		case LAYOUT_AA0:
			return 7;
		case LAYOUT_A8:
		case LAYOUT_AA8:
		case LAYOUT_AAAA4:
			return 9;
		default:
			return -1;
		}

	}

	/**
	 * Method to get the number of child which CollageView type
	 * 
	 * @param viewGroup
	 * @return
	 */
	private static int getCollageViewCount(ViewGroup viewGroup) {
		int count = 0;
		int numOfChild = viewGroup.getChildCount();
		for (int i = 0; i < numOfChild; i++) {
			if (viewGroup.getChildAt(i) instanceof OnCollageView && !(viewGroup.getChildAt(i) instanceof CollageViewClipSmilly)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Method to get the array of View instanceOf CollageViewGroup
	 * 
	 * @param viewGroup
	 * @return
	 */
	private static View[] getCollageViews(ViewGroup viewGroup, int length) {
		View[] views = new View[length];
		int count = 0;
		int numOfChild = viewGroup.getChildCount();
		for (int i = 0; i < numOfChild; i++) {
			if (viewGroup.getChildAt(i) instanceof OnCollageView && !(viewGroup.getChildAt(i) instanceof CollageViewClipSmilly)) {
				views[count] = viewGroup.getChildAt(i);
				count++;
			}
		}
		return views;
	}

	/**
	 * Method to check weather the view group iis valid as per logic or ot
	 * 
	 * @param views
	 *            ViewGroup
	 * @return TRUE if valid, FALSE if invalid
	 */
	private static boolean isValidViewCount(View[] views) {
		if (views.length == 0) {
			return false;
		}
		for (int i = 0; i < views.length; i++) {
			if (views[i] == null) {
				return false;
			}
		}
		return true;
	}

	// ==========================================================================================================
	// LAYOUT FIXED GRID CHILD
	// ==========================================================================================================

	/**
	 * Method to layout children of ViewGroup GRID FIXED
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 *            Type of layouting
	 */
	private static void layoutChildGridFixed(ViewGroup viewGroup, int gridLayoutType) {
		switch (getCollageViewCount(viewGroup)) {
		case 1:
			layoutChild1GridFixed(viewGroup, gridLayoutType);
			break;
		case 2:
			layoutChild2GridFixed(viewGroup, gridLayoutType);
			break;
		case 3:
			layoutChild3GridFixed(viewGroup, gridLayoutType);
			break;
		case 4:
			layoutChild4GridFixed(viewGroup, gridLayoutType);
			break;
		case 5:
			layoutChild5GridFixed(viewGroup, gridLayoutType);
			break;
		case 6:
			layoutChild6GridFixed(viewGroup, gridLayoutType);
			break;
		case 7:
			layoutChild7GridFixed(viewGroup, gridLayoutType);
			break;
		case 8:
			layoutChild8GridFixed(viewGroup, gridLayoutType);
			break;
		case 9:
			layoutChild9GridFixed(viewGroup, gridLayoutType);
			break;
		default:
			break;
		}
		int childCount = viewGroup.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = viewGroup.getChildAt(i);
			child.setScaleX(0.9F);
			child.setScaleY(0.9F);
		}
	}

	/**
	 * Method to layout Child when child count is 1
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild1GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 1);
		viewArray[0].layout(0, 0, viewGroup.getWidth(), viewGroup.getHeight());
	}

	/**
	 * Method to layout Child when child count is 2
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild2GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 2);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, width, height >> 1);
			viewArray[1].layout(0, height >> 1, width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, width >> 1, height);
			viewArray[1].layout(width >> 1, 0, width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.4F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.6F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.4F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height);
			break;
		case LAYOUT_A6:
		default:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.6F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height);
		}
	}

	/**
	 * Method to layout Child when child count is 3
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild3GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 3);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(width >> 1, 0, width, height >> 1);
			viewArray[2].layout(0, height >> 1, width, height);
			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, width, height >> 1);
			viewArray[1].layout(0, height >> 1, width >> 1, height);
			viewArray[2].layout(width >> 1, height >> 1, width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(0, height >> 1, width >> 1, height);
			viewArray[2].layout(width >> 1, 0, width, height);
			break;
		case LAYOUT_A6:
			viewArray[0].layout(0, 0, width >> 1, height);
			viewArray[1].layout(width >> 1, 0, width, height >> 1);
			viewArray[2].layout(width >> 1, height >> 1, width, height);
			break;
		case LAYOUT_A7:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.2F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width, (int) ((float) height * 0.6F));
			viewArray[2].layout(0, viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_A8:
			viewArray[0].layout(0, 0, width >> 1, height);
			viewArray[1].layout(width >> 1, 0, width, (int) ((float) height * 0.7F));
			viewArray[2].layout(width >> 1, viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_A9:
			viewArray[0].layout(0, 0, width >> 1, height);
			viewArray[1].layout(width >> 1, 0, width, (int) ((float) height * 0.3F));
			viewArray[2].layout(width >> 1, viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_AA0:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.3F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 1, height);
			viewArray[2].layout(width >> 1, 0, width, height);
			break;
		case LAYOUT_AA1:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.7F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 1, height);
			viewArray[2].layout(width >> 1, 0, width, height);
			break;
		case LAYOUT_AA2:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.3F), height >> 1);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height >> 1);
			viewArray[2].layout(0, height >> 1, width, height);
			break;
		case LAYOUT_AA3:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.7F), height >> 1);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height >> 1);
			viewArray[2].layout(0, height >> 1, width, height);
			break;
		case LAYOUT_AA4:
			viewArray[0].layout(0, 0, width, height >> 1);
			viewArray[1].layout(0, height >> 1, (int) ((float) width * 0.3F), height);
			viewArray[2].layout(viewArray[1].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_AA5:
		default:
			viewArray[0].layout(0, 0, width, height >> 1);
			viewArray[1].layout(0, height >> 1, (int) ((float) width * 0.7F), height);
			viewArray[2].layout(viewArray[1].getRight(), height >> 1, width, height);
		}
	}

	/**
	 * Method to layout Child when child count is 4
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild4GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 4);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(width >> 1, 0, width, height >> 1);
			viewArray[2].layout(0, height >> 1, width >> 1, height);
			viewArray[3].layout(width >> 1, height >> 1, width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.4F), height >> 1);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height >> 1);
			viewArray[2].layout(0, height >> 1, (int) ((float) width * 0.6F), height);
			viewArray[3].layout(viewArray[2].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.4F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.67F));
			viewArray[2].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[1].getBottom());
			viewArray[3].layout(0, viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.25F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, width >> 1, height);
			viewArray[2].layout(width >> 1, 0, (int) ((float) width * 0.75F), height);
			viewArray[3].layout(viewArray[2].getRight(), 0, width, height);
			break;
		case LAYOUT_A6:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.6F), (int) ((float) height * 0.6F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), height);
			viewArray[3].layout(viewArray[2].getRight(), viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_A7:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(width >> 1, 0, width, height >> 2);
			viewArray[2].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[3].layout(0, height >> 1, width, height);
			break;
		case LAYOUT_A8:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.2F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.4F));
			viewArray[2].layout(width >> 1, 0, width, viewArray[1].getBottom());
			viewArray[3].layout(0, viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A9:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(width >> 1, 0, width, height >> 2);
			viewArray[2].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[3].layout(0, height >> 1, width, height);
			break;
		case LAYOUT_AA0:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.2F), height >> 1);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.4F), height >> 1);
			viewArray[2].layout(0, height >> 1, viewArray[1].getRight(), height);
			viewArray[3].layout(viewArray[1].getRight(), 0, width, height);
			break;
		case LAYOUT_AA1:
			viewArray[0].layout(0, 0, width >> 2, height >> 1);
			viewArray[1].layout(width >> 2, 0, width >> 1, height >> 1);
			viewArray[2].layout(0, height >> 1, width >> 1, height);
			viewArray[3].layout(width >> 1, 0, width, height);
			break;
		case LAYOUT_AA2:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.6F));
			viewArray[1].layout(width >> 1, 0, width, (int) ((float) height * 0.4F));
			viewArray[2].layout(0, viewArray[0].getBottom(), width >> 1, height);
			viewArray[3].layout(width >> 1, viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_AA3:
			viewArray[0].layout(0, 0, width >> 1, height);
			viewArray[1].layout(width >> 1, 0, width, (int) ((float) height * 0.33F));
			viewArray[2].layout(width >> 1, viewArray[1].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[3].layout(width >> 1, viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_AA4:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 1);
			viewArray[2].layout(viewArray[0].getRight(), height >> 1, viewArray[1].getRight(), height);
			viewArray[3].layout(viewArray[1].getRight(), 0, width, height);
			break;
		case LAYOUT_AA5:
		default:
			viewArray[0].layout(0, 0, width, height >> 2);
			viewArray[1].layout(0, viewArray[0].getBottom(), width, height >> 1);
			viewArray[2].layout(0, height >> 1, width, (int) ((float) height * 0.75F));
			viewArray[3].layout(0, viewArray[2].getBottom(), width, height);
		}
	}

	/**
	 * Method to layout Child when child count is 5
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild5GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 5);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height >> 1);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 1);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, height >> 1);
			viewArray[3].layout(0, height >> 1, width >> 1, height);
			viewArray[4].layout(width >> 1, height >> 1, width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.33F));
			viewArray[1].layout(width >> 1, 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[3].layout(0, viewArray[2].getBottom(), width >> 1, height);
			viewArray[4].layout(width >> 1, viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.67F));
			viewArray[2].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[1].getBottom());
			viewArray[3].layout(0, viewArray[1].getBottom(), width >> 1, height);
			viewArray[4].layout(width >> 1, viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(0, height >> 1, width >> 1, height);
			viewArray[2].layout(width >> 1, 0, width, (int) ((float) height * 0.33F));
			viewArray[3].layout(width >> 1, viewArray[2].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[4].layout(width >> 1, viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.3F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[3].layout(0, viewArray[2].getBottom(), (int) ((float) width * 0.7F), height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A6:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(width >> 1, 0, width, height >> 1);
			viewArray[2].layout(0, height >> 1, (int) ((float) width * 0.33F), height);
			viewArray[3].layout(viewArray[2].getRight(), height >> 1, (int) ((float) width * 0.67F), height);
			viewArray[4].layout(viewArray[3].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_A7:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), (int) ((float) height * 0.66F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[3].layout(viewArray[2].getRight(), viewArray[0].getBottom(), (int) ((float) width * 0.66F), height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A8:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height >> 2);
			viewArray[2].layout(viewArray[0].getRight(), height >> 2, width, height >> 1);
			viewArray[3].layout(viewArray[0].getRight(), height >> 1, width, (int) ((float) height * 0.75F));
			viewArray[4].layout(viewArray[0].getRight(), viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A9:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), (int) ((float) height * 0.66F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom() >> 1);
			viewArray[2].layout(viewArray[0].getRight(), viewArray[1].getBottom(), width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_AA0:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.4F), (int) ((float) height * 0.67F));
			viewArray[2].layout(viewArray[1].getRight(), viewArray[0].getBottom(), width, viewArray[1].getBottom());
			viewArray[3].layout(0, viewArray[1].getBottom(), (int) ((float) width * 0.6F), height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_AA1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.7F), (int) ((float) height * 0.3F));
			viewArray[1].layout((int) ((float) width * 0.7F), 0, width, (int) ((float) height * 0.7F));
			viewArray[2].layout((int) ((float) width * 0.3F), (int) ((float) height * 0.7F), width, height);
			viewArray[3].layout(0, (int) ((float) height * 0.3F), (int) ((float) width * 0.3F), height);
			viewArray[4].layout((int) ((float) width * 0.3F), (int) ((float) height * 0.3F), (int) ((float) width * 0.7F), (int) ((float) height * 0.7F));
			break;
		case LAYOUT_AA2:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.34F), (int) ((float) height * 0.66F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom() >> 1);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[1].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_AA3:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(0, height >> 1, width >> 1, height);
			viewArray[2].layout(width >> 1, 0, width, (int) ((float) height * 0.33F));
			viewArray[3].layout(width >> 1, viewArray[2].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[4].layout(width >> 1, viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_AA4:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 1);
			viewArray[2].layout(viewArray[0].getRight(), height >> 1, viewArray[1].getRight(), height);
			viewArray[3].layout(viewArray[1].getRight(), 0, width, height >> 1);
			viewArray[4].layout(viewArray[1].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_AA5:
		default:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.4F));
			viewArray[1].layout(width >> 1, 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[3].layout(viewArray[2].getRight(), viewArray[0].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), width, height);
		}
	}

	/**
	 * Method to layout Child when child count is 6
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild6GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 6);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height >> 1);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 1);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, height >> 1);
			viewArray[3].layout(0, height >> 1, viewArray[0].getRight(), height);
			viewArray[4].layout(viewArray[3].getRight(), height >> 1, (int) ((float) width * 0.67F), height);
			viewArray[5].layout(viewArray[4].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), (int) ((float) height * 0.66F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom() >> 1);
			viewArray[2].layout(viewArray[0].getRight(), viewArray[1].getBottom(), width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), viewArray[0].getRight() >> 1, height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), viewArray[0].getRight(), height);
			viewArray[5].layout(viewArray[0].getRight(), viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.34F), (int) ((float) height * 0.34F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.67F));
			viewArray[4].layout(0, viewArray[3].getBottom(), viewArray[0].getRight(), height);
			viewArray[5].layout(viewArray[0].getRight(), viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), (int) ((float) height * 0.2F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.4F));
			viewArray[2].layout(0, viewArray[1].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.6F));
			viewArray[3].layout(0, viewArray[2].getBottom(), viewArray[0].getRight(), height);
			viewArray[4].layout(viewArray[0].getRight(), 0, width, viewArray[2].getBottom());
			viewArray[5].layout(viewArray[0].getRight(), viewArray[4].getBottom(), width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), (int) ((float) height * 0.4F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.6F));
			viewArray[2].layout(0, viewArray[1].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.8F));
			viewArray[3].layout(0, viewArray[2].getBottom(), viewArray[0].getRight(), height);
			viewArray[4].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[5].layout(viewArray[0].getRight(), viewArray[4].getRight(), width, height);
			break;
		case LAYOUT_A6:
			viewArray[0].layout(0, 0, width, height >> 2);
			viewArray[1].layout(0, height >> 2, width >> 1, height >> 1);
			viewArray[2].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[3].layout(0, height >> 1, width >> 1, (int) ((float) height * 0.75F));
			viewArray[4].layout(width >> 1, height >> 1, width, viewArray[3].getBottom());
			viewArray[5].layout(0, viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A7:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.33F));
			viewArray[1].layout(width >> 1, 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.33F), (int) ((float) height * 0.67F));
			viewArray[3].layout(viewArray[2].getRight(), viewArray[0].getBottom(), (int) ((float) width * 0.67F), viewArray[2].getBottom());
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[5].layout(0, viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A8:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height >> 1);
			viewArray[1].layout(0, height >> 1, viewArray[0].getRight(), height);
			viewArray[2].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), (int) ((float) height * 0.33F));
			viewArray[3].layout(viewArray[0].getRight(), viewArray[2].getBottom(), viewArray[2].getRight(), (int) ((float) height * 0.67F));
			viewArray[4].layout(viewArray[0].getRight(), viewArray[3].getBottom(), viewArray[2].getRight(), height);
			viewArray[5].layout(viewArray[2].getRight(), 0, width, height);
			break;
		case LAYOUT_A9:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.6F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.4F), (int) ((float) height * 0.67F));
			viewArray[3].layout(viewArray[2].getRight(), viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[4].layout(0, viewArray[2].getBottom(), viewArray[0].getRight(), height);
			viewArray[5].layout(viewArray[0].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_AA0:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.4F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), height);
			viewArray[2].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), (int) ((float) height * 0.6F));
			viewArray[3].layout(viewArray[0].getRight(), viewArray[2].getBottom(), viewArray[2].getRight(), height);
			viewArray[4].layout(viewArray[2].getRight(), 0, width, (int) ((float) height * 0.4F));
			viewArray[5].layout(viewArray[2].getRight(), viewArray[4].getBottom(), width, height);
			break;
		case LAYOUT_AA1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.34F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.67F));
			viewArray[3].layout(viewArray[0].getRight(), viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[4].layout(0, viewArray[2].getBottom(), viewArray[0].getRight(), height);
			viewArray[5].layout(viewArray[0].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_AA2:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), height);
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[5].layout(viewArray[4].getRight(), viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_AA3:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.67F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 2, height);
			viewArray[2].layout(width >> 2, viewArray[0].getBottom(), width >> 1, height);
			viewArray[3].layout(width >> 1, 0, (int) ((float) width * 0.75F), (int) ((float) height * 0.33F));
			viewArray[4].layout(viewArray[3].getRight(), 0, width, viewArray[3].getBottom());
			viewArray[5].layout(width >> 1, viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_AA4:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.34F), (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), viewArray[0].getRight(), height);
			viewArray[3].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 1);
			viewArray[4].layout(viewArray[3].getRight(), 0, width, height >> 1);
			viewArray[5].layout(viewArray[0].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_AA5:
		default:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.4F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.67F));
			viewArray[3].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[4].layout(0, viewArray[2].getBottom(), (int) ((float) width * 0.6F), height);
			viewArray[5].layout(viewArray[4].getRight(), viewArray[2].getBottom(), width, height);
			break;
		}
	}

	/**
	 * Method to layout Child when child count is 7
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild7GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 7);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.66F));
			viewArray[4].layout(viewArray[0].getRight(), viewArray[0].getBottom(), viewArray[1].getRight(), viewArray[3].getBottom());
			viewArray[5].layout(viewArray[1].getRight(), viewArray[0].getBottom(), width, viewArray[3].getBottom());
			viewArray[6].layout(0, viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.34F));
			viewArray[1].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.33F), (int) ((float) height * 0.67F));
			viewArray[2].layout(viewArray[1].getRight(), viewArray[0].getBottom(), (int) ((float) width * 0.67F), viewArray[1].getBottom());
			viewArray[3].layout(viewArray[2].getRight(), viewArray[0].getBottom(), width, viewArray[1].getBottom());
			viewArray[4].layout(0, viewArray[1].getBottom(), viewArray[1].getRight(), height);
			viewArray[5].layout(viewArray[1].getRight(), viewArray[1].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[6].layout(viewArray[2].getRight(), viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.34F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), (int) ((float) height * 0.33F));
			viewArray[2].layout(viewArray[0].getRight(), viewArray[1].getBottom(), viewArray[1].getRight(), (int) ((float) height * 0.67F));
			viewArray[3].layout(viewArray[0].getRight(), viewArray[2].getBottom(), viewArray[1].getRight(), height);
			viewArray[4].layout(viewArray[1].getRight(), 0, width, viewArray[1].getBottom());
			viewArray[5].layout(viewArray[1].getRight(), viewArray[1].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[6].layout(viewArray[1].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, (int) viewArray[0].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[4].layout(0, viewArray[3].getBottom(), viewArray[0].getRight(), height);
			viewArray[5].layout(viewArray[0].getRight(), viewArray[3].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[6].layout(viewArray[1].getRight(), viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.66F));
			viewArray[3].layout(viewArray[2].getRight(), viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[4].layout(0, viewArray[2].getBottom(), viewArray[2].getRight() >> 1, height);
			viewArray[5].layout(viewArray[4].getRight(), viewArray[2].getBottom(), viewArray[2].getRight(), height);
			viewArray[6].layout(viewArray[2].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A6:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.4F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.66F));
			viewArray[3].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[4].layout(0, viewArray[2].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[5].layout(viewArray[4].getRight(), viewArray[2].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[6].layout(viewArray[5].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A7:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.4F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), height);
			viewArray[2].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.66F), height >> 1);
			viewArray[3].layout(viewArray[0].getRight(), height >> 1, viewArray[2].getRight(), height);
			viewArray[4].layout(viewArray[2].getRight(), 0, width, (int) ((float) height * 0.33F));
			viewArray[5].layout(viewArray[2].getRight(), viewArray[4].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[6].layout(viewArray[2].getRight(), viewArray[5].getBottom(), width, height);
			break;
		case LAYOUT_A8:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.6F), (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), viewArray[0].getRight(), height);
			viewArray[3].layout(viewArray[0].getRight(), 0, width, height >> 2);
			viewArray[4].layout(viewArray[0].getRight(), height >> 2, width, height >> 1);
			viewArray[5].layout(viewArray[0].getRight(), height >> 1, width, (int) ((float) height * 0.75F));
			viewArray[6].layout(viewArray[0].getRight(), viewArray[5].getBottom(), width, height);
			break;
		case LAYOUT_A9:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.33F));
			viewArray[1].layout(width >> 2, 0, width >> 1, viewArray[0].getBottom());
			viewArray[2].layout(width >> 1, 0, (int) ((float) width * 0.75F), viewArray[0].getBottom());
			viewArray[3].layout(viewArray[2].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[4].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[5].layout(viewArray[4].getRight(), viewArray[0].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[6].layout(viewArray[5].getRight(), viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_AA0:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.67F));
			viewArray[3].layout(viewArray[2].getRight(), viewArray[0].getBottom(), (int) ((float) width * 0.67F), viewArray[2].getBottom());
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[5].layout(0, viewArray[2].getBottom(), viewArray[3].getRight(), height);
			viewArray[6].layout(viewArray[3].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_AA1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), height);
			viewArray[2].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[3].layout(viewArray[0].getRight(), viewArray[2].getBottom(), viewArray[2].getRight(), (int) ((float) height * 0.67F));
			viewArray[4].layout(viewArray[0].getRight(), viewArray[3].getBottom(), viewArray[2].getRight(), height);
			viewArray[5].layout(viewArray[2].getRight(), 0, width, (int) ((float) height * 0.67F));
			viewArray[6].layout(viewArray[2].getRight(), viewArray[5].getBottom(), width, height);
			break;
		case LAYOUT_AA2:
			viewArray[0].layout(0, 0, width >> 2, height);
			viewArray[1].layout(width >> 2, 0, width >> 1, height >> 1);
			viewArray[2].layout(width >> 2, height >> 1, width >> 1, height);
			viewArray[3].layout(width >> 1, 0, (int) ((float) width * 0.75F), height >> 1);
			viewArray[4].layout(width >> 1, height >> 1, viewArray[3].getRight(), height);
			viewArray[5].layout(viewArray[3].getRight(), 0, width, height >> 1);
			viewArray[6].layout(viewArray[3].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_AA3:
			viewArray[0].layout(0, 0, width, height >> 2);
			viewArray[1].layout(0, height >> 2, width >> 1, height >> 1);
			viewArray[2].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[3].layout(0, height >> 1, width >> 1, (int) ((float) height * 0.75F));
			viewArray[4].layout(width >> 1, height >> 1, width, viewArray[3].getBottom());
			viewArray[5].layout(0, viewArray[3].getBottom(), width >> 1, height);
			viewArray[6].layout(width >> 1, viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_AA4:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), viewArray[0].getRight(), height);
			viewArray[3].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.75F), height >> 1);
			viewArray[4].layout(viewArray[3].getRight(), 0, width, height >> 1);
			viewArray[5].layout(viewArray[0].getRight(), height >> 1, (int) ((float) width * 0.58F), height);
			viewArray[6].layout(viewArray[5].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_AA5:
		default:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.58F));
			viewArray[4].layout(0, viewArray[3].getBottom(), width >> 1, height);
			viewArray[5].layout(width >> 1, viewArray[0].getBottom(), width, (int) ((float) height * 0.75F));
			viewArray[6].layout(width >> 1, viewArray[5].getBottom(), width, height);
		}
	}

	/**
	 * Method to layout Child when child count is 8
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild8GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 8);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, width >> 1, height >> 2);
			viewArray[1].layout(width >> 1, 0, width, height >> 2);
			viewArray[2].layout(0, height >> 2, width >> 1, height >> 1);
			viewArray[3].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[4].layout(0, height >> 1, width >> 1, (int) ((float) height * 0.75F));
			viewArray[5].layout(width >> 1, height >> 1, width, viewArray[4].getBottom());
			viewArray[6].layout(0, viewArray[4].getBottom(), width >> 1, height);
			viewArray[7].layout(width >> 1, viewArray[4].getBottom(), width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), (int) ((float) height * 0.375F));
			viewArray[1].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.75F));
			viewArray[2].layout(0, viewArray[1].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[3].layout(viewArray[2].getRight(), viewArray[1].getBottom(), viewArray[0].getRight(), height);
			viewArray[4].layout(viewArray[0].getRight(), 0, width, height >> 2);
			viewArray[5].layout(viewArray[0].getRight(), height >> 2, width, height >> 1);
			viewArray[6].layout(viewArray[0].getRight(), height >> 1, width, (int) ((float) height * 0.75F));
			viewArray[7].layout(viewArray[0].getRight(), viewArray[6].getBottom(), width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, width, height >> 2);
			viewArray[1].layout(0, height >> 2, width, height >> 1);
			viewArray[2].layout(0, height >> 1, (int) ((float) width * 0.33F), (int) ((float) height * 0.75F));
			viewArray[3].layout(viewArray[2].getRight(), height >> 1, (int) ((float) width * 0.67F), viewArray[2].getBottom());
			viewArray[4].layout(viewArray[3].getRight(), height >> 1, width, viewArray[2].getBottom());
			viewArray[5].layout(0, viewArray[2].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[6].layout(viewArray[5].getRight(), viewArray[2].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[7].layout(viewArray[6].getRight(), viewArray[2].getBottom(), width, height);

			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 2, (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), width >> 2, height);
			viewArray[3].layout(width >> 2, 0, width >> 1, viewArray[0].getBottom());
			viewArray[4].layout(width >> 2, viewArray[0].getBottom(), width >> 1, viewArray[1].getBottom());
			viewArray[5].layout(width >> 2, viewArray[1].getBottom(), width >> 1, height);
			viewArray[6].layout(width >> 1, 0, (int) ((float) width * 0.75F), height);
			viewArray[7].layout(viewArray[6].getRight(), 0, width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, width >> 1, height >> 1);
			viewArray[1].layout(0, height >> 1, width >> 1, height);
			viewArray[2].layout(width >> 1, 0, (int) ((float) width * 0.75F), (int) ((float) height * 0.33F));
			viewArray[3].layout(width >> 1, viewArray[2].getBottom(), viewArray[2].getRight(), (int) ((float) height * 0.67F));
			viewArray[4].layout(width >> 1, viewArray[3].getBottom(), viewArray[2].getRight(), height);
			viewArray[5].layout(viewArray[2].getRight(), 0, width, viewArray[2].getBottom());
			viewArray[6].layout(viewArray[2].getRight(), viewArray[2].getBottom(), width, viewArray[3].getBottom());
			viewArray[7].layout(viewArray[2].getRight(), viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A6:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height >> 2);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 2);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, height >> 2);
			viewArray[3].layout(0, height >> 2, viewArray[0].getRight(), height >> 1);
			viewArray[4].layout(viewArray[0].getRight(), height >> 2, viewArray[1].getRight(), height >> 1);
			viewArray[5].layout(viewArray[1].getRight(), height >> 2, width, height >> 1);
			viewArray[6].layout(0, height >> 1, width >> 1, height);
			viewArray[7].layout(width >> 1, height >> 1, width, height);
			break;
		case LAYOUT_A7:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), height >> 1);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height >> 1);
			viewArray[2].layout(0, height >> 1, (int) ((float) width * 0.33F), (int) ((float) height * 0.75F));
			viewArray[3].layout(viewArray[2].getRight(), height >> 1, (int) ((float) width * 0.67F), viewArray[2].getBottom());
			viewArray[4].layout(viewArray[3].getRight(), height >> 1, width, viewArray[2].getBottom());
			viewArray[5].layout(0, viewArray[2].getBottom(), viewArray[2].getRight(), height);
			viewArray[5].layout(viewArray[2].getRight(), viewArray[2].getBottom(), viewArray[3].getRight(), height);
			viewArray[6].layout(viewArray[3].getRight(), viewArray[2].getBottom(), width, height);
			break;
		case LAYOUT_A8:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 2, (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), width >> 2, height);
			viewArray[3].layout(width >> 2, 0, width >> 1, viewArray[0].getBottom());
			viewArray[4].layout(width >> 2, viewArray[0].getBottom(), width >> 1, viewArray[1].getBottom());
			viewArray[5].layout(width >> 2, viewArray[1].getBottom(), width >> 1, height);
			viewArray[6].layout(width >> 1, 0, width, viewArray[0].getBottom());
			viewArray[7].layout(width >> 1, viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A9:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height >> 2);
			viewArray[1].layout(viewArray[0].getRight(), 0, width, height >> 2);
			viewArray[2].layout(0, height >> 2, (int) ((float) width * 0.67F), height >> 1);
			viewArray[3].layout(viewArray[2].getRight(), height >> 2, width, height >> 1);
			viewArray[4].layout(0, height >> 1, (int) ((float) width * 0.33F), (int) ((float) height * 0.75F));
			viewArray[5].layout(viewArray[4].getRight(), height >> 1, width, viewArray[4].getBottom());
			viewArray[6].layout(0, viewArray[4].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[7].layout(viewArray[6].getRight(), viewArray[4].getBottom(), width, height);
			break;
		case LAYOUT_AA0:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 2, height);
			viewArray[2].layout(width >> 2, 0, width >> 1, (int) ((float) height * 0.67F));
			viewArray[3].layout(width >> 2, viewArray[2].getBottom(), width >> 1, height);
			viewArray[4].layout(width >> 1, 0, (int) ((float) width * 0.75F), (int) ((float) height * 0.33F));
			viewArray[5].layout(width >> 1, viewArray[4].getBottom(), viewArray[4].getRight(), height);
			viewArray[6].layout(viewArray[4].getRight(), 0, width, (int) ((float) height * 0.67F));
			viewArray[7].layout(viewArray[4].getRight(), viewArray[6].getBottom(), width, height);
			break;
		case LAYOUT_AA1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height >> 2);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 2);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, height >> 2);
			viewArray[3].layout(0, height >> 2, width, height >> 1);
			viewArray[4].layout(0, height >> 1, width, (int) ((float) height * 0.75F));
			viewArray[5].layout(0, viewArray[4].getBottom(), viewArray[0].getRight(), height);
			viewArray[6].layout(viewArray[0].getRight(), viewArray[4].getBottom(), viewArray[1].getRight(), height);
			viewArray[7].layout(viewArray[1].getRight(), viewArray[4].getBottom(), width, height);
			break;
		case LAYOUT_AA2:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 2, (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), width >> 2, height);
			viewArray[3].layout(width >> 2, 0, width >> 1, height);
			viewArray[4].layout(width >> 1, 0, (int) ((float) width * 0.75F), height);
			viewArray[5].layout(viewArray[4].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[6].layout(viewArray[4].getRight(), viewArray[0].getBottom(), width, viewArray[1].getBottom());
			viewArray[7].layout(viewArray[4].getRight(), viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_AA3:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.66F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), viewArray[1].getRight(), (int) ((float) height * 0.67F));
			viewArray[4].layout(viewArray[3].getRight(), viewArray[0].getBottom(), width, viewArray[3].getBottom());
			viewArray[5].layout(0, viewArray[3].getBottom(), viewArray[0].getRight(), height);
			viewArray[6].layout(viewArray[0].getRight(), viewArray[3].getBottom(), viewArray[1].getRight(), height);
			viewArray[7].layout(viewArray[1].getRight(), viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_AA4:
			viewArray[0].layout(0, 0, width >> 2, height >> 1);
			viewArray[1].layout(width >> 2, 0, width >> 1, height >> 1);
			viewArray[2].layout(width >> 1, 0, width, height >> 2);
			viewArray[3].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[4].layout(0, height >> 1, width >> 1, (int) ((float) height * 0.75F));
			viewArray[5].layout(0, viewArray[4].getBottom(), width >> 1, height);
			viewArray[6].layout(width >> 1, height >> 1, (int) ((float) width * 0.75F), height);
			viewArray[7].layout(viewArray[6].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_AA5:
		default:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.67F));
			viewArray[4].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[3].getBottom());
			viewArray[5].layout(0, viewArray[3].getBottom(), viewArray[0].getRight(), height);
			viewArray[6].layout(viewArray[0].getRight(), viewArray[3].getBottom(), viewArray[1].getRight(), height);
			viewArray[7].layout(viewArray[1].getRight(), viewArray[3].getBottom(), width, height);
		}
	}

	/**
	 * Method to layout Child when child count is 9
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 */
	private static void layoutChild9GridFixed(ViewGroup viewGroup, int gridLayoutType) {
		View[] viewArray = getCollageViews(viewGroup, 9);
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		switch (gridLayoutType) {
		case LAYOUT_A1:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), viewArray[0].getRight(), (int) ((float) height * 0.67F));
			viewArray[4].layout(viewArray[0].getRight(), viewArray[0].getBottom(), viewArray[1].getRight(), viewArray[3].getBottom());
			viewArray[5].layout(viewArray[1].getRight(), viewArray[0].getBottom(), width, viewArray[3].getBottom());
			viewArray[6].layout(0, viewArray[3].getBottom(), viewArray[0].getRight(), height);
			viewArray[7].layout(viewArray[0].getRight(), viewArray[3].getBottom(), viewArray[1].getRight(), height);
			viewArray[8].layout(viewArray[1].getRight(), viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A2:
			viewArray[0].layout(0, 0, width >> 2, height >> 2);
			viewArray[1].layout(width >> 2, 0, (int) ((float) width * 0.75F), height >> 2);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, height >> 2);
			viewArray[3].layout(0, height >> 2, viewArray[0].getRight(), (int) ((float) height * 0.75F));
			viewArray[4].layout(viewArray[0].getRight(), height >> 2, viewArray[1].getRight(), viewArray[3].getBottom());
			viewArray[5].layout(viewArray[1].getRight(), height >> 2, width, viewArray[3].getBottom());
			viewArray[6].layout(0, viewArray[3].getBottom(), viewArray[0].getRight(), height);
			viewArray[7].layout(viewArray[0].getRight(), viewArray[3].getBottom(), viewArray[1].getRight(), height);
			viewArray[8].layout(viewArray[1].getRight(), viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_A3:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), height >> 2);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), height >> 2);
			viewArray[2].layout(viewArray[1].getRight(), 0, width, height >> 2);
			viewArray[3].layout(0, height >> 2, width >> 1, height >> 1);
			viewArray[4].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[5].layout(0, height >> 1, width >> 1, (int) ((float) height * 0.75F));
			viewArray[6].layout(width >> 1, height >> 1, width, viewArray[5].getBottom());
			viewArray[7].layout(0, viewArray[5].getBottom(), width >> 1, height);
			viewArray[8].layout(width >> 1, viewArray[5].getBottom(), width, height);
			break;
		case LAYOUT_A4:
			viewArray[0].layout(0, 0, width >> 1, height >> 2);
			viewArray[1].layout(width >> 1, 0, width, height >> 2);
			viewArray[2].layout(0, height >> 2, width >> 1, height >> 1);
			viewArray[3].layout(width >> 1, height >> 2, width, height >> 1);
			viewArray[4].layout(0, height >> 1, width >> 1, (int) ((float) height * 0.75F));
			viewArray[5].layout(width >> 1, height >> 1, width, viewArray[4].getBottom());
			viewArray[6].layout(0, viewArray[4].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[7].layout(viewArray[6].getRight(), viewArray[4].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[8].layout(viewArray[7].getRight(), viewArray[4].getBottom(), width, height);
			break;
		case LAYOUT_A5:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.33F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 2, (int) ((float) height * 0.67F));
			viewArray[2].layout(0, viewArray[1].getBottom(), width >> 2, height);
			viewArray[3].layout(width >> 2, 0, width >> 1, height >> 1);
			viewArray[4].layout(width >> 2, height >> 1, width >> 1, height);
			viewArray[5].layout(width >> 1, 0, (int) ((float) width * 0.75F), height >> 1);
			viewArray[6].layout(width >> 1, height >> 1, viewArray[5].getRight(), height);
			viewArray[7].layout(viewArray[5].getRight(), 0, width, height >> 1);
			viewArray[8].layout(viewArray[5].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_A6:
			viewArray[0].layout(0, 0, width >> 2, height >> 1);
			viewArray[1].layout(0, height >> 1, width >> 2, height);
			viewArray[2].layout(width >> 2, 0, width >> 1, height >> 1);
			viewArray[3].layout(width >> 2, height >> 1, width >> 1, height);
			viewArray[4].layout(width >> 1, 0, (int) ((float) width * 0.75F), height >> 1);
			viewArray[5].layout(width >> 1, height >> 1, viewArray[4].getRight(), height);
			viewArray[6].layout(viewArray[4].getRight(), 0, width, (int) ((float) height * 0.33F));
			viewArray[7].layout(viewArray[4].getRight(), viewArray[6].getBottom(), width, (int) ((float) height * 0.67F));
			viewArray[8].layout(viewArray[4].getRight(), viewArray[7].getBottom(), width, height);
			break;
		case LAYOUT_A7:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.2F), height);
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.4F), height >> 1);
			viewArray[2].layout(viewArray[0].getRight(), height >> 1, viewArray[1].getRight(), height);
			viewArray[3].layout(viewArray[1].getRight(), 0, (int) ((float) width * 0.6F), height >> 1);
			viewArray[4].layout(viewArray[1].getRight(), height >> 1, viewArray[3].getRight(), height);
			viewArray[5].layout(viewArray[3].getRight(), 0, (int) ((float) width * 0.8F), height >> 1);
			viewArray[6].layout(viewArray[4].getRight(), height >> 1, viewArray[5].getRight(), height);
			viewArray[7].layout(viewArray[5].getRight(), 0, width, height >> 1);
			viewArray[8].layout(viewArray[5].getRight(), height >> 1, width, height);
			break;
		case LAYOUT_A8:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.66F));
			viewArray[1].layout(width >> 2, 0, width >> 1, (int) ((float) height * 0.33F));
			viewArray[2].layout(width >> 2, viewArray[1].getBottom(), width >> 1, viewArray[0].getBottom());
			viewArray[3].layout(width >> 1, 0, (int) ((float) width * 0.75F), viewArray[1].getBottom());
			viewArray[4].layout(width >> 1, viewArray[1].getBottom(), viewArray[3].getRight(), viewArray[0].getBottom());
			viewArray[5].layout(viewArray[3].getRight(), 0, width, viewArray[1].getBottom());
			viewArray[6].layout(viewArray[3].getRight(), viewArray[1].getBottom(), width, viewArray[0].getBottom());
			viewArray[7].layout(0, viewArray[0].getBottom(), (int) ((float) width * 0.4F), height);
			viewArray[8].layout(viewArray[7].getRight(), viewArray[0].getBottom(), width, height);
			break;
		case LAYOUT_A9:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.66F), height >> 1);
			viewArray[1].layout(0, height >> 1, viewArray[0].getRight() >> 1, (int) ((float) height * 0.75F));
			viewArray[2].layout(viewArray[1].getRight(), height >> 1, viewArray[0].getRight(), viewArray[1].getBottom());
			viewArray[3].layout(0, viewArray[1].getBottom(), viewArray[1].getRight(), height);
			viewArray[4].layout(viewArray[1].getRight(), viewArray[1].getBottom(), viewArray[2].getRight(), height);
			viewArray[5].layout(viewArray[0].getRight(), 0, width, height >> 2);
			viewArray[6].layout(viewArray[0].getRight(), height >> 2, width, height >> 1);
			viewArray[7].layout(viewArray[0].getRight(), height >> 1, width, viewArray[1].getBottom());
			viewArray[8].layout(viewArray[0].getRight(), viewArray[1].getBottom(), width, height);
			break;
		case LAYOUT_AA0:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.2F), height >> 1);
			viewArray[1].layout(0, height >> 1, viewArray[0].getRight(), height);
			viewArray[2].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.4F), height >> 1);
			viewArray[3].layout(viewArray[0].getRight(), height >> 1, viewArray[2].getRight(), height);
			viewArray[4].layout(viewArray[2].getRight(), 0, (int) ((float) width * 0.6F), height >> 1);
			viewArray[5].layout(viewArray[2].getRight(), height >> 1, viewArray[4].getRight(), height);
			viewArray[6].layout(viewArray[4].getRight(), 0, (int) ((float) width * 0.8F), height >> 1);
			viewArray[7].layout(viewArray[4].getRight(), height >> 1, viewArray[6].getRight(), height);
			viewArray[8].layout(viewArray[6].getRight(), 0, width, height);
			break;
		case LAYOUT_AA1:
			viewArray[0].layout(0, 0, width, (int) ((float) height * 0.2F));
			viewArray[1].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.4F));
			viewArray[2].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[1].getBottom());
			viewArray[3].layout(0, viewArray[1].getBottom(), width >> 1, (int) ((float) height * 0.6F));
			viewArray[4].layout(width >> 1, viewArray[1].getBottom(), width, viewArray[3].getBottom());
			viewArray[5].layout(0, viewArray[3].getBottom(), width >> 1, (int) ((float) height * 0.8F));
			viewArray[6].layout(width >> 1, viewArray[3].getBottom(), width, viewArray[5].getBottom());
			viewArray[7].layout(0, viewArray[5].getBottom(), width >> 1, height);
			viewArray[8].layout(width >> 1, viewArray[5].getBottom(), width, height);
			break;
		case LAYOUT_AA2:
			viewArray[0].layout(0, 0, width >> 1, (int) ((float) height * 0.2F));
			viewArray[1].layout(width >> 1, 0, width, viewArray[0].getBottom());
			viewArray[2].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.4F));
			viewArray[3].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[2].getBottom());
			viewArray[4].layout(0, viewArray[2].getBottom(), width >> 1, (int) ((float) height * 0.6F));
			viewArray[5].layout(width >> 1, viewArray[2].getBottom(), width, viewArray[4].getBottom());
			viewArray[6].layout(0, viewArray[4].getBottom(), width >> 1, (int) ((float) height * 0.8F));
			viewArray[7].layout(width >> 1, viewArray[4].getBottom(), width, viewArray[6].getBottom());
			viewArray[8].layout(0, viewArray[6].getBottom(), width, height);
			break;
		case LAYOUT_AA3:
			viewArray[0].layout(0, 0, (int) ((float) width * 0.33F), (int) ((float) height * 0.33F));
			viewArray[1].layout(viewArray[0].getRight(), 0, (int) ((float) width * 0.67F), viewArray[0].getBottom());
			viewArray[2].layout(viewArray[1].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[3].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.67F));
			viewArray[4].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[3].getBottom());
			viewArray[5].layout(0, viewArray[3].getBottom(), width >> 2, height);
			viewArray[6].layout(width >> 2, viewArray[3].getBottom(), width >> 1, height);
			viewArray[7].layout(width >> 1, viewArray[3].getBottom(), (int) ((float) width * 0.75F), height);
			viewArray[8].layout(viewArray[7].getRight(), viewArray[3].getBottom(), width, height);
			break;
		case LAYOUT_AA4:
		default:
			viewArray[0].layout(0, 0, width >> 2, (int) ((float) height * 0.33F));
			viewArray[1].layout(width >> 2, 0, width >> 1, viewArray[0].getBottom());
			viewArray[2].layout(width >> 1, 0, (int) ((float) width * 0.75F), viewArray[0].getBottom());
			viewArray[3].layout(viewArray[2].getRight(), 0, width, viewArray[0].getBottom());
			viewArray[4].layout(0, viewArray[0].getBottom(), width >> 1, (int) ((float) height * 0.66F));
			viewArray[5].layout(width >> 1, viewArray[0].getBottom(), width, viewArray[4].getBottom());
			viewArray[6].layout(0, viewArray[4].getBottom(), (int) ((float) width * 0.33F), height);
			viewArray[7].layout(viewArray[6].getRight(), viewArray[4].getBottom(), (int) ((float) width * 0.67F), height);
			viewArray[8].layout(viewArray[7].getRight(), viewArray[4].getBottom(), width, height);
			break;
		}
	}

	// ==========================================================================================================
	// LAYOUT FREE GRID CHILD
	// ==========================================================================================================

	/**
	 * Method to layout children of ViewGroup GRID FREE
	 * 
	 * @param viewGroup
	 * @param gridLayoutType
	 *            Type of layouting
	 */
	private static void layoutChildGridFree(ViewGroup viewGroup, int gridLayoutType) {
		View[] collageViews = getCollageViews(viewGroup, getCollageViewCount(viewGroup));
		int maxDimension = 0;
		if (collageViews.length < 2) {
			maxDimension = (int) ((float) viewGroup.getWidth() * 0.4F);
		} else if (collageViews.length < 5) {
			maxDimension = (int) ((float) viewGroup.getWidth() * 0.3F);
		} else {
			maxDimension = (int) ((float) viewGroup.getWidth() * 0.25F);
		}
		Random random = new Random();
		Log.e("THE-MKR", "LayoutChildUtils.layoutChildGridFree()" + ">>SIZE>>>"+collageViews.length);
		int diff = viewGroup.getWidth() - maxDimension;
		for (int i = 0; i < collageViews.length; i++) {
			int x = random.nextInt(diff);
			int y = random.nextInt(diff);
			collageViews[i].layout(x, y, x + maxDimension, y + maxDimension);
			collageViews[i].setRotation(random.nextInt(359));
			Log.e("THE-MKR", "LayoutChildUtils.layoutChildGridFree()" + ">>>>>>>>>>>>>>>>>>>>>>>>>");
			Log.e("THE-MKR", "LayoutChildUtils.layoutChildGridFree()" + "      " + i + "   " + collageViews[i].getLeft() + "   " + INVALID_LAYOUT);
			Log.e("THE-MKR", "LayoutChildUtils.layoutChildGridFree()" + "      " + i + "   " + collageViews[i].getTop() + "   " + INVALID_LAYOUT);
			Log.e("THE-MKR", "LayoutChildUtils.layoutChildGridFree()" + "      " + i + "   " + collageViews[i].getRight() + "   " + INVALID_LAYOUT);
			Log.e("THE-MKR", "LayoutChildUtils.layoutChildGridFree()" + "      " + i + "   " + collageViews[i].getBottom() + "   " + INVALID_LAYOUT);

		}
	}

	// ==========================================================================================================
	// LAYOUT SHAPE CHILD
	// ==========================================================================================================

	/**
	 * Method to layout children of SHAPE ViewGroup
	 * 
	 * @param collageLayoutType
	 *            Type of layouting
	 */
	private static void layoutShapeChild(ViewGroup viewGroup, int collageIndex) {
		switch (collageIndex) {
		case LAYOUT_A1:
			layoutShapeChild0(viewGroup, getNumberOfChildInShape(LAYOUT_A1));
			break;
		case LAYOUT_A2:
			layoutShapeChild1(viewGroup, getNumberOfChildInShape(LAYOUT_A2));
			break;
		case LAYOUT_A3:
			layoutShapeChild2(viewGroup, getNumberOfChildInShape(LAYOUT_A3));
			break;
		case LAYOUT_A4:
			layoutShapeChild3(viewGroup, getNumberOfChildInShape(LAYOUT_A4));
			break;
		case LAYOUT_A5:
			layoutShapeChild4(viewGroup, getNumberOfChildInShape(LAYOUT_A5));
			break;
		case LAYOUT_A6:
			layoutShapeChild5(viewGroup, getNumberOfChildInShape(LAYOUT_A6));
			break;
		case LAYOUT_A7:
			layoutShapeChild6(viewGroup, getNumberOfChildInShape(LAYOUT_A7));
			break;
		case LAYOUT_A8:
			layoutShapeChild7(viewGroup, getNumberOfChildInShape(LAYOUT_A8));
			break;
		case LAYOUT_A9:
			layoutShapeChild8(viewGroup, getNumberOfChildInShape(LAYOUT_A9));
			break;
		case LAYOUT_AA0:
			layoutShapeChild9(viewGroup, getNumberOfChildInShape(LAYOUT_AA0));
			break;
		case LAYOUT_AA1:
			layoutShapeChild10(viewGroup, getNumberOfChildInShape(LAYOUT_AA1));
			break;
		case LAYOUT_AA2:
			layoutShapeChild11(viewGroup, getNumberOfChildInShape(LAYOUT_AA2));
			break;
		case LAYOUT_AA3:
			layoutShapeChild12(viewGroup, getNumberOfChildInShape(LAYOUT_AA3));
			break;
		case LAYOUT_AA4:
			layoutShapeChild13(viewGroup, getNumberOfChildInShape(LAYOUT_AA4));
			break;
		case LAYOUT_AA5:
			layoutShapeChild14(viewGroup, getNumberOfChildInShape(LAYOUT_AA5));
			break;
		case LAYOUT_AA6:
			layoutShapeChild15(viewGroup, getNumberOfChildInShape(LAYOUT_AA6));
			break;
		case LAYOUT_AA7:
			layoutShapeChild16(viewGroup, getNumberOfChildInShape(LAYOUT_AA7));
			break;
		case LAYOUT_AA8:
			layoutShapeChild17(viewGroup, getNumberOfChildInShape(LAYOUT_AA8));
			break;
		case LAYOUT_AA9:
			layoutShapeChild18(viewGroup, getNumberOfChildInShape(LAYOUT_AA9));
			break;
		case LAYOUT_AAA0:
			layoutShapeChild19(viewGroup, getNumberOfChildInShape(LAYOUT_AAA0));
			break;
		case LAYOUT_AAA1:
			layoutShapeChild20(viewGroup, getNumberOfChildInShape(LAYOUT_AAA1));
			break;
		case LAYOUT_AAA2:
			layoutShapeChild21(viewGroup, getNumberOfChildInShape(LAYOUT_AAA2));
			break;
		case LAYOUT_AAA3:
			layoutShapeChild22(viewGroup, getNumberOfChildInShape(LAYOUT_AAA3));
			break;
		case LAYOUT_AAA4:
			layoutShapeChild23(viewGroup, getNumberOfChildInShape(LAYOUT_AAA4));
			break;
		case LAYOUT_AAA5:
			layoutShapeChild24(viewGroup, getNumberOfChildInShape(LAYOUT_AAA5));
			break;
		case LAYOUT_AAA6:
			layoutShapeChild25(viewGroup, getNumberOfChildInShape(LAYOUT_AAA6));
			break;
		case LAYOUT_AAA7:
			layoutShapeChild26(viewGroup, getNumberOfChildInShape(LAYOUT_AAA7));
			break;
		case LAYOUT_AAA8:
			layoutShapeChild27(viewGroup, getNumberOfChildInShape(LAYOUT_AAA8));
			break;
		case LAYOUT_AAA9:
			layoutShapeChild28(viewGroup, getNumberOfChildInShape(LAYOUT_AAA9));
			break;
		case LAYOUT_AAAA0:
			layoutShapeChild29(viewGroup, getNumberOfChildInShape(LAYOUT_AAAA0));
			break;
		case LAYOUT_AAAA1:
			layoutShapeChild30(viewGroup, getNumberOfChildInShape(LAYOUT_AAAA1));
			break;
		case LAYOUT_AAAA2:
			layoutShapeChild31(viewGroup, getNumberOfChildInShape(LAYOUT_AAAA2));
			break;
		case LAYOUT_AAAA3:
			layoutShapeChild32(viewGroup, getNumberOfChildInShape(LAYOUT_AAAA3));
			break;
		case LAYOUT_AAAA4:
			layoutShapeChild33(viewGroup, getNumberOfChildInShape(LAYOUT_AAAA4));
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * Method to get length in integer
	 * 
	 * @param baseLength
	 *            base length
	 * @param percent
	 *            percent point in respect of baseLength
	 * @return percent length of base length in integer
	 */
	private static int getInt(int baseLength, float percent) {
		return (int) ((float) baseLength * percent);
	}

	/**
	 * Method to layout of shape at index 0 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild0(ViewGroup viewGroup, int childCount) {
		// A1
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.33F), getInt(height, 0.33F));
		views[1].layout(getInt(width, 0.67F), 0, width, views[0].getBottom());
		views[2].layout(views[0].getRight(), views[0].getBottom(), views[1].getLeft(), getInt(height, 0.67F));
		views[3].layout(0, views[2].getBottom(), views[2].getLeft(), height);
		views[4].layout(views[2].getRight(), views[2].getBottom(), width, height);
		for (int i = 0; i < views.length; i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 1 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild1(ViewGroup viewGroup, int childCount) {
		// A2
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.4F), getInt(height, 0.30F));
		views[1].layout(getInt(width, 0.3F), getInt(height, 0.35F), getInt(width, 0.7F), getInt(height, 0.65F));
		views[2].layout(getInt(width, 0.6F), getInt(height, 0.7F), width, height);
		for (int i = 0; i < views.length; i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 2 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild2(ViewGroup viewGroup, int childCount) {
		// A3
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.4F), getInt(height, 0.4F));
		views[1].layout(getInt(width, 0.6F), 0, width, views[0].getBottom());
		views[2].layout(getInt(width, 0.3F), getInt(height, 0.3F), getInt(width, 0.7F), getInt(height, 0.7F));
		views[3].layout(0, getInt(height, 0.6F), views[0].getRight(), height);
		views[4].layout(views[1].getLeft(), views[3].getTop(), width, height);
		for (int i = 0; i < views.length; i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 3 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild3(ViewGroup viewGroup, int childCount) {
		// A4
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width >> 1, height >> 1);
		views[1].layout(width >> 1, 0, width, height >> 1);
		views[2].layout(0, height >> 1, width >> 1, height);
		views[3].layout(width >> 1, height >> 1, width, height);
		for (int i = 0; i < views.length; i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 4 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild4(ViewGroup viewGroup, int childCount) {
		// A5
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(getInt(width, 0.2F), 0, getInt(width, 0.8F), getInt(height, 0.35F));
		views[1].layout(0, views[0].getBottom(), width >> 1, getInt(height, 0.65F));
		views[2].layout(width >> 1, views[0].getBottom(), width, views[1].getBottom());
		views[3].layout(getInt(width, 0.2F), views[1].getBottom(), getInt(width, 0.8F), height);
		views[4].layout(getInt(width, 0.35F), getInt(height, 0.3F), getInt(width, 0.65F), getInt(height, 0.7F));
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

	/**
	 * Method to layout of shape at index 5 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild5(ViewGroup viewGroup, int childCount) {
		// A6
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width >> 2, getInt(height, 0.8F));
		views[1].layout(width >> 2, getInt(height, 0.2F), width >> 1, height);
		views[2].layout(width >> 1, 0, getInt(width, 0.75F), views[0].getBottom());
		views[3].layout(views[2].getRight(), views[1].getTop(), width, height);
		for (int i = 0; i < views.length; i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 6 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild6(ViewGroup viewGroup, int childCount) {
		// A7
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.33F), getInt(height, 0.33F));
		views[1].layout(getInt(width, 0.67F), 0, width, views[0].getBottom());
		views[2].layout(views[0].getRight(), views[0].getBottom(), views[1].getLeft(), getInt(height, 0.67F));
		views[3].layout(0, views[2].getBottom(), views[0].getRight(), height);
		views[4].layout(views[1].getLeft(), views[2].getBottom(), width, height);
		views[1].setRotation(90);
		views[3].setRotation(270);
		views[4].setRotation(180);
	}

	/**
	 * Method to layout of shape at index 7 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild7(ViewGroup viewGroup, int childCount) {
		// A8
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(getInt(width, 0.35F), 0, getInt(width, 0.65F), getInt(height, 0.3F));
		views[1].layout(getInt(width, 0.1F), getInt(height, 0.1F), getInt(width, 0.4F), getInt(height, 0.4F));
		views[2].layout(getInt(width, 0.6F), getInt(height, 0.1F), getInt(width, 0.9F), getInt(height, 0.4F));
		views[3].layout(0, getInt(height, 0.35F), getInt(width, 0.3F), getInt(height, 0.65F));
		views[4].layout(getInt(width, 0.7F), getInt(height, 0.35F), width, getInt(height, 0.65F));
		views[5].layout(getInt(width, 0.1F), getInt(height, 0.6F), getInt(width, 0.4F), getInt(height, 0.9F));
		views[6].layout(getInt(width, 0.6F), getInt(height, 0.6F), getInt(width, 0.9F), getInt(height, 0.9F));
		views[7].layout(getInt(width, 0.35F), getInt(height, 0.7F), getInt(width, 0.65F), height);
		views[8].layout(getInt(width, 0.35F), getInt(height, 0.35F), getInt(width, 0.65F), getInt(height, 0.65F));
		for (int i = 0; i < views.length; i++) {
			views[i].setScaleX(0.85F);
			views[i].setScaleY(0.85F);
		}
	}

	/**
	 * Method to layout of shape at index 8 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild8(ViewGroup viewGroup, int childCount) {
		// A9
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width >> 1, getInt(height, 0.6F));
		views[1].layout(width >> 1, 0, width, views[0].getBottom());
		views[2].layout(0, views[0].getBottom(), width, height);
		views[3].layout(getInt(width, 0.4F), getInt(height, 0.4F), getInt(width, 0.8F), getInt(height, 0.8F));
	}

	/**
	 * Method to layout of shape at index 9 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild9(ViewGroup viewGroup, int childCount) {
		// AA0
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, getInt(height, 0.2F), getInt(width, 0.33F), height >> 1);
		views[1].layout(0, height >> 1, views[0].getRight(), getInt(height, 0.8F));
		views[2].layout(views[0].getRight(), 0, getInt(width, 0.67F), getInt(height, 0.3F));
		views[3].layout(views[0].getRight(), views[2].getBottom(), views[2].getRight(), getInt(height, 0.7F));
		views[4].layout(views[0].getRight(), views[3].getBottom(), views[2].getRight(), height);
		views[5].layout(views[2].getRight(), views[0].getTop(), width, views[0].getBottom());
		views[6].layout(views[2].getRight(), views[1].getTop(), width, views[1].getBottom());
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

	/**
	 * Method to layout of shape at index 10 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild10(ViewGroup viewGroup, int childCount) {
		// AA1
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.45F), getInt(height, 0.45F));
		views[1].layout(getInt(width, 0.55F), 0, width, getInt(height, 0.45F));
		views[2].layout(0, getInt(height, 0.55F), getInt(width, 0.45F), height);
		views[3].layout(getInt(width, 0.55F), getInt(height, 0.55F), width, height);
	}

	/**
	 * Method to layout of shape at index 11 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild11(ViewGroup viewGroup, int childCount) {
		// AA2
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width, getInt(height, 0.33F));
		views[1].layout(0, views[0].getBottom(), width, getInt(height, 0.67F));
		views[2].layout(0, views[1].getBottom(), width, height);
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

	/**
	 * Method to layout of shape at index 12 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild12(ViewGroup viewGroup, int childCount) {
		// AA3
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.33F), height);
		views[1].layout(views[0].getRight(), 0, getInt(width, 0.67F), height);
		views[2].layout(views[1].getRight(), 0, width, height);
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

	/**
	 * Method to layout of shape at index 13 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild13(ViewGroup viewGroup, int childCount) {
		// AA4
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(getInt(width, 0.35F), 0, getInt(width, 0.65F), getInt(height, 0.3F));
		views[1].layout(0, getInt(height, 0.35F), getInt(width, 0.3F), getInt(height, 0.65F));
		views[2].layout(getInt(width, 0.7F), views[1].getTop(), width, views[1].getBottom());
		views[3].layout(views[0].getLeft(), getInt(height, 0.7F), views[0].getRight(), height);
	}

	/**
	 * Method to layout of shape at index 14 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild14(ViewGroup viewGroup, int childCount) {
		// AA5
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width >> 1, height);
		views[1].layout(width >> 1, 0, width, height);
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

	/**
	 * Method to layout of shape at index 15 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild15(ViewGroup viewGroup, int childCount) {
		// AA6
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width, height >> 1);
		views[1].layout(0, height >> 1, width, height);
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

	/**
	 * Method to layout of shape at index 16 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild16(ViewGroup viewGroup, int childCount) {
		// AA7
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width, height);
		views[1].layout(0, 0, width, height);
		views[2].layout(0, 0, width, height);
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

	/**
	 * Method to layout of shape at index 17 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild17(ViewGroup viewGroup, int childCount) {
		// AA8
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(getInt(width, 0.35F), 0, getInt(width, 0.65F), getInt(height, 0.3F));
		views[1].layout(getInt(width, 0.1F), getInt(height, 0.1F), getInt(width, 0.4F), getInt(height, 0.4F));
		views[2].layout(getInt(width, 0.6F), getInt(height, 0.1F), getInt(width, 0.9F), getInt(height, 0.4F));
		views[3].layout(0, getInt(height, 0.35F), getInt(width, 0.3F), getInt(height, 0.65F));
		views[4].layout(getInt(width, 0.7F), getInt(height, 0.35F), width, getInt(height, 0.65F));
		views[5].layout(getInt(width, 0.1F), getInt(height, 0.6F), getInt(width, 0.4F), getInt(height, 0.9F));
		views[6].layout(getInt(width, 0.6F), getInt(height, 0.6F), getInt(width, 0.9F), getInt(height, 0.9F));
		views[7].layout(getInt(width, 0.35F), getInt(height, 0.7F), getInt(width, 0.65F), height);
		views[8].layout(getInt(width, 0.35F), getInt(height, 0.35F), getInt(width, 0.65F), getInt(height, 0.65F));
		// ROTATE
		views[0].setRotation(180);
		views[1].setRotation(135);
		views[2].setRotation(225);
		views[3].setRotation(90);
		views[4].setRotation(270);
		views[5].setRotation(45);
		views[6].setRotation(315);
		viewGroup.setScaleX(0.975F);
		viewGroup.setScaleY(0.975F);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 18 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild18(ViewGroup viewGroup, int childCount) {
		// AA9
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, getInt(height, 0.1F), getInt(width, 0.45F), getInt(height, 0.5F));
		views[1].layout(getInt(width, 0.55F), views[0].getTop(), width, views[0].getBottom());
		views[2].layout(0, getInt(height, 0.8F), getInt(width, 0.2F), height);
		views[3].layout(getInt(width, 0.4F), getInt(height, 0.7F), getInt(width, 0.6F), getInt(height, 0.9F));
		views[4].layout(getInt(width, 0.8F), views[2].getTop(), width, height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 19 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild19(ViewGroup viewGroup, int childCount) {
		// AAA0
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width >> 1, height >> 1);
		views[1].layout(width >> 1, 0, width, height >> 1);
		views[2].layout(0, height >> 1, width >> 1, height);
		views[3].layout(width >> 1, height >> 1, width, height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 20 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild20(ViewGroup viewGroup, int childCount) {
		// AAA1
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.3F), getInt(height, 0.3F));
		views[1].layout(getInt(width, 0.7F), views[0].getTop(), width, views[0].getBottom());
		views[2].layout(getInt(width, 0.35F), views[0].getBottom(), getInt(width, 0.65F), getInt(height, 0.6F));
		views[3].layout(0, views[2].getBottom(), getInt(width, 0.3F), getInt(height, 0.9F));
		views[4].layout(getInt(width, 0.7F), views[2].getBottom(), width, views[3].getBottom());
		views[5].layout(getInt(width, 0.35F), getInt(height, 0.7F), getInt(width, 0.65F), height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 21 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild21(ViewGroup viewGroup, int childCount) {
		// AAA2
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width >> 1, height);
		views[1].layout(width >> 1, getInt(height, 0.1F), width, getInt(height, 0.4F));
		views[2].layout(width >> 1, getInt(height, 0.6F), width, getInt(height, 0.9F));
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 22 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild22(ViewGroup viewGroup, int childCount) {
		// AAA3
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width, height);
		views[1].layout(getInt(width, 0.3F), getInt(height, 0.3F), getInt(width, 0.7F), getInt(height, 0.7F));
	}

	/**
	 * Method to layout of shape at index 23 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild23(ViewGroup viewGroup, int childCount) {
		// AAA4
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width, height >> 1);
		views[1].layout(0, height >> 1, width >> 1, height);
		views[2].layout(width >> 1, height >> 1, width, height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 24 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild24(ViewGroup viewGroup, int childCount) {
		// AAA5
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, getInt(height, 0.2F), getInt(width, 0.3F), getInt(height, 0.5F));
		views[1].layout(getInt(width, 0.35F), 0, getInt(width, 0.65F), getInt(height, 0.3F));
		views[2].layout(getInt(width, 0.7F), getInt(height, 0.2F), width, getInt(height, 0.5F));
		views[3].layout(getInt(width, 0.3F), getInt(height, 0.6F), getInt(width, 0.7F), height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 25 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild25(ViewGroup viewGroup, int childCount) {
		// AAA6
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(getInt(width, 0.6F), 0, width, getInt(height, 0.4F));
		views[1].layout(0, getInt(height, 0.7F), getInt(width, 0.3F), height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 26 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild26(ViewGroup viewGroup, int childCount) {
		// AAA7
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, width >> 1, height >> 1);
		views[1].layout(0, height >> 1, width >> 1, height);
		views[2].layout(width >> 1, 0, width, height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 27 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild27(ViewGroup viewGroup, int childCount) {
		// AAA8
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.2F), getInt(height, 0.2F));
		views[1].layout(getInt(width, 0.3F), getInt(height, 0.1F), getInt(width, 0.7F), getInt(height, 0.5F));
		views[2].layout(getInt(width, 0.7F), getInt(height, 0.1F), width, getInt(height, 0.4F));
		views[3].layout(0, getInt(height, 0.7F), getInt(width, 0.3F), height);
		views[4].layout(getInt(width, 0.4F), getInt(height, 0.6F), getInt(width, 0.6F), getInt(height, 0.8F));
		views[5].layout(getInt(width, 0.6F), getInt(height, 0.7F), getInt(width, 0.9F), height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 28 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild28(ViewGroup viewGroup, int childCount) {
		// AAA9
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.4F), getInt(height, 0.4F));
		views[1].layout(getInt(width, 0.6F), getInt(height, 0.1F), getInt(width, 0.8F), getInt(height, 0.3F));
		views[2].layout(getInt(width, 0.4F), getInt(height, 0.4F), getInt(width, 0.6F), getInt(height, 0.6F));
		views[3].layout(getInt(width, 0.6F), getInt(height, 0.4F), width, getInt(height, 0.8F));
		views[4].layout(getInt(width, 0.1F), getInt(height, 0.6F), getInt(width, 0.5F), height);
		views[5].layout(getInt(width, 0.6F), getInt(height, 0.8F), getInt(width, 0.8F), height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 29 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild29(ViewGroup viewGroup, int childCount) {
		// AAAA0
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.8F), getInt(height, 0.3F));
		views[1].layout(getInt(width, 0.2F), getInt(height, 0.35F), width, getInt(height, 0.65F));
		views[2].layout(0, getInt(height, 0.7F), getInt(width, 0.8F), height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 30 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild30(ViewGroup viewGroup, int childCount) {
		// AAAA1
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(getInt(width, 0.35F), 0, getInt(width, 0.65F), getInt(height, 0.3F));
		views[1].layout(0, getInt(height, 0.35F), getInt(width, 0.3F), getInt(height, 0.65F));
		views[2].layout(getInt(width, 0.35F), getInt(height, 0.35F), getInt(width, 0.65F), getInt(height, 0.65F));
		views[3].layout(getInt(width, 0.7F), views[1].getTop(), width, views[1].getBottom());
		views[4].layout(views[0].getLeft(), getInt(height, 0.7F), views[0].getRight(), height);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 31 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild31(ViewGroup viewGroup, int childCount) {
		// AAAA2
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(getInt(width, 0.35F), 0, getInt(width, 0.65F), getInt(height, 0.3F));
		views[1].layout(0, getInt(height, 0.35F), getInt(width, 0.3F), getInt(height, 0.65F));
		views[2].layout(getInt(width, 0.35F), getInt(height, 0.35F), getInt(width, 0.65F), getInt(height, 0.65F));
		views[3].layout(getInt(width, 0.7F), views[1].getTop(), width, views[1].getBottom());
		views[4].layout(views[0].getLeft(), getInt(height, 0.7F), views[0].getRight(), height);
		views[1].setRotation(270);
		views[3].setRotation(90);
		views[4].setRotation(180);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 32 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild32(ViewGroup viewGroup, int childCount) {
		// AAAA3
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.3F), getInt(height, 0.3F));
		views[1].layout(getInt(width, 0.7F), 0, width, views[0].getBottom());
		views[2].layout(getInt(width, 0.3F), getInt(height, 0.3F), getInt(width, 0.7F), getInt(height, 0.7F));
		views[3].layout(0, getInt(height, 0.7F), views[0].getRight(), height);
		views[4].layout(views[1].getLeft(), views[3].getTop(), width, height);
		views[0].setRotation(135);
		views[1].setRotation(225);
		views[3].setRotation(45);
		views[4].setRotation(315);
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			views[i].setScaleX(MIN_MIN);
			views[i].setScaleY(MIN_MIN);
		}
	}

	/**
	 * Method to layout of shape at index 33 in Assets/Shape
	 * 
	 * @param viewGroup
	 */
	private static void layoutShapeChild33(ViewGroup viewGroup, int childCount) {
		// AAAA4
		View views[] = getCollageViews(viewGroup, childCount);
		if (!isValidViewCount(views)) {
			return;
		}
		int width = viewGroup.getWidth();
		int height = viewGroup.getHeight();
		views[0].layout(0, 0, getInt(width, 0.33F), getInt(height, 0.33F));
		views[1].layout(views[0].getRight(), 0, getInt(width, 0.67F), views[0].getBottom());
		views[2].layout(views[1].getRight(), 0, width, views[0].getBottom());
		views[3].layout(0, views[0].getBottom(), views[0].getRight(), getInt(height, 0.33F));
		views[4].layout(views[0].getRight(), views[0].getBottom(), views[1].getRight(), views[3].getBottom());
		views[5].layout(views[1].getRight(), views[0].getBottom(), width, views[3].getBottom());
		views[6].layout(0, views[3].getBottom(), views[0].getRight(), height);
		views[7].layout(views[0].getRight(), views[3].getBottom(), views[1].getRight(), height);
		views[8].layout(views[1].getRight(), views[3].getBottom(), width, height);
		viewGroup.setScaleX(MIN_MIN);
		viewGroup.setScaleY(MIN_MIN);
	}

}
