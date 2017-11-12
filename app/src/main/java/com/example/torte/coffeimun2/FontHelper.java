package com.example.torte.coffeimun2;

import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by torte on 12.11.2017.
 */

public class FontHelper {

    public static Typeface GetFont(AssetManager assets){
        return Typeface.createFromAsset(assets, "fonts/peace_sans_webfont.ttf");
    }

}
