package com.goomer.ps.feature.menu.details;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.goomer.data.models.Menu;
import com.goomer.ps.R;

public class MenuDetailActivity extends AppCompatActivity {
    public static String EXTRA_MENU = "extra_menu";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        TextView tvPrice = findViewById(R.id.tvPrice);

        Menu menu = (Menu) getIntent().getParcelableExtra(EXTRA_MENU);

        assert menu != null;
        tvName.setText(menu.getName());
        tvDescription.setText(menu.getDescription());
        tvPrice.setText(getString(R.string.price, menu.getPrice()));

        setTitle(menu.getName() != null ? menu.getName() : getString(R.string.app_name));
    }

}
