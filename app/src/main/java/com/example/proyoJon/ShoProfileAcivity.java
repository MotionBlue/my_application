package com.example.proyoJon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ShoProfileAcivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawer;
    ActionBarDrawerToggle mToggle;
    NavigationView nav_view;
    FragmentManager fm;
    FrameLayout viewLayout;
    MyprofileFrag myprofileFrag;
    updateFrag updateFrag1;
    deleteFrag deleteFrag1;
    homeFrag homeFrag1;
    notificationFrag notificationFrag1;
    contactFrag contactFrag1;
    ActiveStatus activeStatus1;
    TextView t1,t2;
    String v;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sho_profile_acivity);
        mAuth = FirebaseAuth.getInstance();
        mDrawer = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        View headerView = nav_view.inflateHeaderView(R.layout.nav_layout);
        final ImageView im = headerView.findViewById(R.id.imgshowTv);
        final TextView tm = headerView.findViewById(R.id.nameShowTv);
        final ProgressBar pb = headerView.findViewById(R.id.pbt);
        FirebaseUser user = mAuth.getCurrentUser();
        final String uid = user.getUid();
        DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReference("Kormi");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   String name =dataSnapshot.child(uid).child("name").getValue(String.class);
                Picasso.with(ShoProfileAcivity.this).load(dataSnapshot.child(uid).child("imageLink").getValue(String.class)).transform(new Transform()).into(im, new Callback() {
                    @Override
                    public void onSuccess() {
                        pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
                tm.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        viewLayout = findViewById(R.id.viewLayout);
        fm = getSupportFragmentManager();
        t1 = findViewById(R.id.sagotomId);
                    t1.setText("Welcome!");
                    v = "en";
        myprofileFrag = new MyprofileFrag();
        updateFrag1 = new updateFrag();
        deleteFrag1 = new deleteFrag();
        homeFrag1 = new homeFrag();
        notificationFrag1 = new notificationFrag();
        contactFrag1 = new contactFrag();
        activeStatus1 = new ActiveStatus();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.homeId:
                fragment = homeFrag1;
                break;
            case R.id.myProile:
                fragment = myprofileFrag;
                setTitle(menuItem.getTitle());
                break;
            case R.id.updateProfile:
                fragment = updateFrag1;
                break;
            case R.id.deleteProfile:
                 fragment = deleteFrag1;
                break;
            case R.id.notify:
                fragment = notificationFrag1;
                break;
            case R.id.contact:
                fragment = contactFrag1;
                break;
            case R.id.active_status:
                fragment = activeStatus1;
        }
        fm.beginTransaction().replace(R.id.viewLayout,fragment).commit();
        mDrawer.closeDrawers();
        return true;
    }
}
