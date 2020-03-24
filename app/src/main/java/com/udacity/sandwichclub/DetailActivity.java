package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {
    public static final String LOG_TAG = DetailActivity.class.getSimpleName();

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView mIngredientsIv;
    private TextView mOriginTv;
    private TextView mDescriptionTv;
    private TextView mIngredientsTv;
    private TextView mAlsoKnownAsTv;

    private TextView mOriginLabelTv;
    private TextView mDescriptionLabelTv;
    private TextView mIngredientsLabelTv;
    private TextView mAlsoKnownAsLabelTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initUI();

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.
                getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void initUI() {
        mIngredientsIv = findViewById(R.id.image_iv);
        mOriginTv = findViewById(R.id.origin_tv);
        mDescriptionTv = findViewById(R.id.description_tv);
        mIngredientsTv = findViewById(R.id.ingredients_tv);
        mAlsoKnownAsTv = findViewById(R.id.also_known_tv);

        mOriginLabelTv = findViewById(R.id.origin_label_tv);
        mDescriptionLabelTv = findViewById(R.id.description_label_tv);
        mIngredientsLabelTv = findViewById(R.id.ingredients_label_tv);
        mAlsoKnownAsLabelTv = findViewById(R.id.also_known_label_tv);

        mOriginTv.setVisibility(View.GONE);
        mOriginLabelTv.setVisibility(View.GONE);
        mDescriptionTv.setVisibility(View.GONE);
        mDescriptionLabelTv.setVisibility(View.GONE);
        mIngredientsTv.setVisibility(View.GONE);
        mIngredientsLabelTv.setVisibility(View.GONE);
        mAlsoKnownAsTv.setVisibility(View.GONE);
        mAlsoKnownAsLabelTv.setVisibility(View.GONE);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
