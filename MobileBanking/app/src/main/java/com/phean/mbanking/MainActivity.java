package com.phean.mbanking;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.phean.mbanking.ui.card.fragment.CardFragment;
import com.phean.mbanking.ui.history.fragment.HistoryFragment;
import com.phean.mbanking.ui.home.fragment.HomeFragment;
import com.phean.mbanking.ui.profile.fragment.ProfileFragment;
import com.phean.mbanking.ui.qr.fragment.QRFragment;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout homeNavigation, historyNavigation, qrNavigation, cardNavigation, profileNavigation;
    private final static String TAG = "MainActivity";
    private LinearLayout bottomNavigationView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        bottomNavigationView = findViewById(R.id.bottomNavigationLayout);

//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.home_navigation, R.id.history_navigation, R.id.qr_navigation, R.id.card_navigation, R.id.profile_navigation)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        homeNavigation = findViewById(R.id.home_navigation);
        historyNavigation = findViewById(R.id.history_navigation);
        qrNavigation = findViewById(R.id.qr_navigation);
        cardNavigation = findViewById(R.id.card_navigation);
        profileNavigation = findViewById(R.id.profile_navigation);

        homeNavigation.setOnClickListener(this);
        historyNavigation.setOnClickListener(this);
        qrNavigation.setOnClickListener(this);
        cardNavigation.setOnClickListener(this);
        profileNavigation.setOnClickListener(this);

        initFragment(new HomeFragment());
        setChildView(homeNavigation);

    }


    // This function use for
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_navigation:
                initFragment(new HomeFragment());
                setChildView(homeNavigation);
                break;
            case R.id.history_navigation:
                initFragment(new HistoryFragment());
                setChildView(historyNavigation);
                break;
            case R.id.qr_navigation:
                initFragment(new QRFragment());
                setChildView(qrNavigation);
                break;
            case R.id.card_navigation:
                initFragment(new CardFragment());
                setChildView(cardNavigation);
                break;
            case R.id.profile_navigation:
                initFragment(new ProfileFragment());
                setChildView(profileNavigation);
                break;
        }
    }

    // This function use for set the child view of Linear Layout
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setChildView(LinearLayout linearLayout){
        String id = getResources().getResourceEntryName(linearLayout.getId());
        for (int i = 0; i < bottomNavigationView.getChildCount(); i++) {
            LinearLayout linearLayout1 = (LinearLayout) bottomNavigationView.getChildAt(i);
            String id1 = getResources().getResourceEntryName(bottomNavigationView.getChildAt(i).getId());
            if (id.equals(id1)){
                ((ImageView) linearLayout1.getChildAt(0)).setImageTintList(ColorStateList.valueOf(Color.BLUE));
                ((TextView) linearLayout1.getChildAt(1)).setTextColor(Color.BLUE);
            }else{
                ((ImageView) linearLayout1.getChildAt(0)).setImageTintList(ColorStateList.valueOf(Color.BLACK));
                ((TextView) linearLayout1.getChildAt(1)).setTextColor(Color.BLACK);
            }
        }
    }

    // This use for initial Fragment View
    private void initFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment,fragment);
        ft.commit();
    }
}
