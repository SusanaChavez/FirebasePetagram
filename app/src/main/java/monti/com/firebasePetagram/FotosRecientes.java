package monti.com.firebasePetagram;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import monti.com.firebasePetagram.adapter.PageAdapter;
import monti.com.firebasePetagram.vistas.PerfilFragment;
import monti.com.firebasePetagram.vistas.RecyclerViewFragment;

public class FotosRecientes extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_recientes);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Fotos Recientes");
        }
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));


    }
    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        return fragments;
    }
}
