package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

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

        populateUI(sandwich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void populateUI(Sandwich sandwich) {
        setTitle(sandwich.getMainName());
        mIngredientsIv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.progress_animation)
                .fit().centerCrop()
                .into(mIngredientsIv);
        setLabelAndText(mOriginLabelTv, mOriginTv, sandwich.getPlaceOfOrigin());
        setLabelAndText(mDescriptionLabelTv, mDescriptionTv, sandwich.getDescription());
        setLabelAndText(mAlsoKnownAsLabelTv, mAlsoKnownAsTv, sandwich.getAlsoKnownAs());
        setLabelAndText(mIngredientsLabelTv, mIngredientsTv, sandwich.getIngredients());
    }

    private void setLabelAndText(TextView labelTv, TextView valueTv, String value) {
        if (!TextUtils.isEmpty(value)) {
            valueTv.setText(value);
            labelTv.setVisibility(View.VISIBLE);
            valueTv.setVisibility(View.VISIBLE);
        }
    }
    private void setLabelAndText(TextView labelTv, TextView valueTv, List<String> val) {
        if (!val.isEmpty()) {
            String trimmedStrVal = trimEnds(val.toString());
            valueTv.setText(trimmedStrVal);
            labelTv.setVisibility(View.VISIBLE);
            valueTv.setVisibility(View.VISIBLE);
        }
    }

    private String trimEnds(String str) {
        int len = str.length();
        if (len<2) return str;
        return str.substring(1, len-1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
}
