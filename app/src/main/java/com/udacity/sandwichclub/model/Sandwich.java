package com.udacity.sandwichclub.model;

import java.util.List;

public class Sandwich {

    public static final String KEY_NAME = "name";
    public static final String KEY_NAME_MAIN = "mainName";
    public static final String KEY_NAME_AKA = "alsoKnownAs";
    public static final String KEY_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";

    private String mMainName;
    private List<String> mAlsoKnownAs;
    private String mPlaceOfOrigin;
    private String mDescription;
    private String mImage;
    private List<String> mIngredients;

    private Sandwich(Builder b) {
        mMainName = b.mainName;
        mAlsoKnownAs = b.alsoKnownAs;
        mPlaceOfOrigin = b.placeOfOrigin;
        mDescription = b.description;
        mImage = b.image;
        mIngredients = b.ingredients;
    }

    public static final class Builder {
        private String mainName;
        private List<String> alsoKnownAs;
        private String placeOfOrigin;
        private String description;
        private String image;
        private List<String> ingredients;

        public Builder() {
            ingredients = null;
            alsoKnownAs = null;
        }

        public Builder mainName(String mainName) {
            this.mainName = mainName;
            return this;
        }

        public Builder alsoKnownAs(List<String> alsoKnownAs) {
            this.alsoKnownAs = alsoKnownAs;
            return this;
        }

        public Builder placeOfOrigin(String placeOfOrigin) {
            this.placeOfOrigin = placeOfOrigin;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder ingredients(List<String> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Sandwich build() {
            return new Sandwich(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "mainName='" + mainName + '\'' +
                    ", alsoKnownAs=" + alsoKnownAs +
                    ", placeOfOrigin='" + placeOfOrigin + '\'' +
                    ", description='" + description + '\'' +
                    ", image='" + image + '\'' +
                    ", ingredients=" + ingredients +
                    '}';
        }
    }
    public String getMainName() {
        return mMainName;
    }

    public List<String> getAlsoKnownAs() {
        return mAlsoKnownAs;
    }
    public String getPlaceOfOrigin() {
        return mPlaceOfOrigin;
    }
    public String getDescription() {
        return mDescription;
    }
    public String getImage() { return mImage; }
    public List<String> getIngredients() { return mIngredients; }

    @Override
    public String toString() {
        return "Sandwich{" +
                "mMainName='" + mMainName + '\'' +
                ", mAlsoKnownAs=" + mAlsoKnownAs +
                ", mPlaceOfOrigin='" + mPlaceOfOrigin + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mImage='" + mImage + '\'' +
                ", mIngredients=" + mIngredients +
                '}';
    }
}