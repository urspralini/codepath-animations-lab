package com.codepath.android.lollipopexercise.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.models.Contact;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_CONTACT = "EXTRA_CONTACT";
    private Contact mContact;
    private ImageView ivProfile;
    private TextView tvName;
    private TextView tvPhone;
    private View vPalette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        vPalette = findViewById(R.id.vPalette);

        // Extract contact from bundle
        mContact = (Contact)getIntent().getExtras().getSerializable(EXTRA_CONTACT);

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // TODO 1. Insert the bitmap into the profile image view
                ivProfile.setImageBitmap(bitmap);

                // TODO 2. Use generate() method from the Palette API to get the vibrant color from the bitmap
                // Set the result as the background color for `R.id.vPalette` view containing the contact's name.
                final Palette palette = Palette.from(bitmap).generate();
                final Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if(vibrantSwatch != null) {
                    vPalette.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        // Fill views with data
        Picasso.with(DetailsActivity.this).load(mContact.getThumbnailDrawable()).into(target);
        tvName.setText(mContact.getName());
        tvPhone.setText(mContact.getNumber());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
