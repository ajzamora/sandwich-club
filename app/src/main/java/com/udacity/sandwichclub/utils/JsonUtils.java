package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String sandwichJSON) {
        if (TextUtils.isEmpty(sandwichJSON)) { return null; }
        Sandwich sandwich = null;

        try {
            JSONObject baseJSON = new JSONObject(sandwichJSON);
            String mainName;
            List<String> alsoKnownAs;
            String placeOfOrigin;
            String description;
            String image;
            List<String> ingredients;

            JSONObject name = baseJSON.getJSONObject(Sandwich.KEY_NAME);
            mainName = name.getString(Sandwich.KEY_NAME_MAIN).toString();

            JSONArray akaJSONArray = name.getJSONArray(Sandwich.KEY_NAME_AKA);
            alsoKnownAs = new ArrayList<String>();
            for (int i=0; i<akaJSONArray.length(); i++) {
                alsoKnownAs.add(akaJSONArray.getString(i));
            }

            placeOfOrigin = baseJSON.getString(Sandwich.KEY_ORIGIN);
            description = baseJSON.getString(Sandwich.KEY_DESCRIPTION);
            image = baseJSON.getString(Sandwich.KEY_IMAGE);

            JSONArray ingredientsJSONArray = baseJSON.getJSONArray(Sandwich.KEY_INGREDIENTS);
            ingredients = new ArrayList<String>();
            for (int i=0; i<ingredientsJSONArray.length(); i++) {
                ingredients.add(ingredientsJSONArray.getString(i));
            }

            sandwich = new Sandwich.Builder()
                    .mainName(mainName)
                    .alsoKnownAs(alsoKnownAs)
                    .placeOfOrigin(placeOfOrigin)
                    .description(description)
                    .image(image)
                    .ingredients(ingredients)
                    .build();
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the sandwich JSON results: " + sandwichJSON, e);
        }

        return sandwich;
    }
}
